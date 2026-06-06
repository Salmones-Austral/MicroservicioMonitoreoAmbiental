package cl.SalmonesAustral.MonitoreoAmbiental.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import cl.SalmonesAustral.MonitoreoAmbiental.modelo.MonitoreoA;
import cl.SalmonesAustral.MonitoreoAmbiental.service.MonitoreoService;
import jakarta.validation.Valid;

//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import cl.SalmonesAustral.MonitoreoAmbiental.dto.AlertasRequest;
import cl.SalmonesAustral.MonitoreoAmbiental.dto.CreateMonitoreoRequest;
import cl.SalmonesAustral.MonitoreoAmbiental.dto.UpdateMonitoreoRequest;
import cl.SalmonesAustral.MonitoreoAmbiental.exception.ResourceNotFoundException;
import cl.SalmonesAustral.MonitoreoAmbiental.mapper.MonitoreoMapper;



@RestController
@RequestMapping("/api/v1/monitoreo")
public class MonitoreoController {

        private final MonitoreoService monitoreoService;
        private final WebClient webClient;
       

        //@Autowired
        public MonitoreoController(MonitoreoService monitoreoService, WebClient.Builder webClientBuilder) {
            this.monitoreoService=monitoreoService;
            this.webClient=webClientBuilder.build();
                
        }
        //CRUD BASICO Y CONEXIONES CON MICROSERVICIOS
        //GET:listar todos los monitoreos
        @GetMapping
        public ResponseEntity<List<MonitoreoA>> getAllMonitoreo() {
            return ResponseEntity.ok(monitoreoService.getAllMonitoreo());
        }
    
        @PostMapping
        public ResponseEntity<?> agregarMonitoreo(@Valid @RequestBody CreateMonitoreoRequest request, BindingResult result) {
            //manejo de errores del dto
            if(result.hasErrors()) {
                Map<String, String> errores=new HashMap<>();
                result.getFieldErrors().forEach(error->errores.put(error.getField(),error.getDefaultMessage()));
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errores);
            }

            //CONEXION 1. validar que la jaula exista(GET)
            try{
                webClient.get()
                    .uri("http://localhost:8081/api/v1/jaulas/{id}", request.jaulaId())
                    .retrieve()
                    .bodyToMono(Object.class)
                    .block();
            }catch (Exception e) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(" Error de conexion: La jaula con ID " + request.jaulaId()
            + " no existe o no responde. ");
            }
        

            MonitoreoA monitoreo = MonitoreoMapper.toMonitoreo(request);
            MonitoreoA nuevoMonitoreo = monitoreoService.saveMonitoreo(monitoreo);
        

        //CONEXION 2. (POST) logica de alertas
        if (nuevoMonitoreo.getOxigenoDisuelto()<4.0 || nuevoMonitoreo.getBloomAlgas()) {
            try{
                AlertasRequest alertaPreventiva = new AlertasRequest(
                    0L,
                    request.jaulaId(),
                    "Alerta Preventiva de Monitoreo Ambiental: Oxigeno bajo o marea roja detectada", 
                    "CRITICO",
                    0.0
                );
                webClient.post()
                .uri("http://localhost:8083/api/v1/alertas/generar")
                .bodyValue(alertaPreventiva)
                .retrieve().bodyToMono(String.class)
                .block();
                System.out.println(" Alerta Ambiental enviada con exito a la Jaula: " + request.jaulaId());
            }catch (Exception e) {
                System.out.println(" El Monitoreo se guardo, pero fallo la comunicacion con el MS de Alertas: " + e.getMessage());
            }
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoMonitoreo);
    }
        //GEt por id: busca un registro y si no existe lanza la exception de GlobalHandler
        @GetMapping("/{id}")
        public ResponseEntity<MonitoreoA> getMonitoreoId(@PathVariable Integer id) {
            MonitoreoA monitoreo = monitoreoService.getMonitoreoId(id);
            if (monitoreo == null) {
                throw new ResourceNotFoundException("Monitoreo no encontrado para id : "+ id);
            }
            return ResponseEntity.ok(monitoreo);
        }
        //eliminar un registro(devuelve 204)
        @DeleteMapping("/{id}")
        public ResponseEntity<Void> deleteMonitoreo(@PathVariable Integer id) {
            monitoreoService.deleteMonitoreo(id);
            return ResponseEntity.noContent().build();
        }
        //GET: tener la cantidad total de monitoreos en el sistema

        @GetMapping("/total")
        public ResponseEntity<Integer> totalMonitoreos() {
            return ResponseEntity.ok(monitoreoService.totalMonitoreos());
        }
        //GET:filtrar por una jaula especifica
        @GetMapping("/jaula/{jaulaId}")
        public ResponseEntity<List<MonitoreoA>> obtenerJaula(@PathVariable Integer jaulaId) {
            return ResponseEntity.ok(monitoreoService.obtenerJaula(jaulaId));
        }
        //put

        @PutMapping("/{id}")
        public ResponseEntity<?>actualizarMonitoreo(@PathVariable Integer id, @Valid @RequestBody UpdateMonitoreoRequest request,
            BindingResult result) {
                //manejo de error de validacion del dto
                if(result.hasErrors()) {
                    Map<String, String> errores = new HashMap<>();
                    result.getFieldErrors().forEach(error->errores.put(error.getField(), error.getDefaultMessage()));
                    return new ResponseEntity<>(errores, HttpStatus.BAD_REQUEST);
                }

        //convierte el dto al modelo de bd
                MonitoreoA monitoreoActualizado = MonitoreoMapper.toMonitoreoActualizado(id, request);
        //guardar en bd
                MonitoreoA guardado = monitoreoService.saveMonitoreo(monitoreoActualizado);
                return ResponseEntity.ok(guardado);

            }
        
    
        
        
}