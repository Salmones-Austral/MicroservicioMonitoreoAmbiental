package cl.SalmonesAustral.MonitoreoAmbiental.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import cl.SalmonesAustral.MonitoreoAmbiental.modelo.MonitoreoA;
import cl.SalmonesAustral.MonitoreoAmbiental.service.MonitoreoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
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
import org.springframework.web.bind.annotation.*;

import cl.SalmonesAustral.MonitoreoAmbiental.dto.AlertasRequest;
import cl.SalmonesAustral.MonitoreoAmbiental.dto.CreateMonitoreoRequest;
import cl.SalmonesAustral.MonitoreoAmbiental.dto.UpdateMonitoreoRequest;
import cl.SalmonesAustral.MonitoreoAmbiental.exception.ResourceNotFoundException;
import cl.SalmonesAustral.MonitoreoAmbiental.mapper.MonitoreoMapper;



@RestController
@RequestMapping("/api/v1/monitoreo")
//define la descipcion global en la interfaz ui de swagger
@Tag(name = "Monitoreo Ambiental", description = "Operaciones relacionadas con el registro de calidad del agua x un tecnico")
public class MonitoreoController {
    private final MonitoreoService monitoreoService;

    public MonitoreoController(MonitoreoService monitoreoService) {
        this.monitoreoService = monitoreoService;
    }


    //post, registrar un nuevo monitoreo    
       
    @PostMapping
    @Operation(summary = "Registrar Monitoreo diario", description = "Permite al tecnico registar temperatura, oxigeno, salinidad y genera alertas si es necesario")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Monitoreo registrado con exito en la BD"),
        @ApiResponse(responseCode = "400", description = "Error: la jaula no existe o parametros incorrectos"),
        @ApiResponse(responseCode = "500", description = "Error de comunicacion: fallo en la red")
    })
    public ResponseEntity<?> agregarMonitoreo(@Valid @RequestBody CreateMonitoreoRequest request, BindingResult result) {
        //manejo de errores del dto
        if(result.hasErrors()) {
            Map<String, String> errores=new HashMap<>();
            result.getFieldErrors().forEach(error->errores.put(error.getField(),error.getDefaultMessage()));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errores);
        }
        try{
            MonitoreoA monitoreo = MonitoreoMapper.toMonitoreo(request);

            //llama al servicio(el cual va a render x jaulas y alertas)
            MonitoreoA nuevMonitoreo = monitoreoService.saveMonitoreo(monitoreo);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(nuevMonitoreo);
        }catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    //CRUD BASICO Y CONEXIONES CON MICROSERVICIOS
    //GET:listar todos los monitoreos, Consulta la BD y trae historial completo de mediciones
    @GetMapping
    @Operation(summary = "Obtener todos los monitoreos", description = "Retorna el histiral de los registros ambientales")
        public ResponseEntity<List<MonitoreoA>> getAllMonitoreo() {
            return ResponseEntity.ok(monitoreoService.getAllMonitoreo());
        }
    //Buscar un registro especifico, si no existe el id, arroja la excepcion
    
    @GetMapping("/{id}")
    @Operation(summary = "Obtener monitoreos por ID", description = "Busca un registro especifico en la BD")
        public ResponseEntity<MonitoreoA> getMonitoreoId(@PathVariable Integer id) {
            MonitoreoA monitoreo = monitoreoService.getMonitoreoId(id);
            if (monitoreo == null) {
                throw new ResourceNotFoundException("Monitoreo no encontrado para id : "+ id);
            }
            return ResponseEntity.ok(monitoreo);
        }

    //GET filtrar por jaula, jaulaId
    @GetMapping("/jaula/{jaulaId}")
    @Operation(summary = "Filtrar monitoreos por jaula", description = "Retorna el historial de una jaula especifica")
        public ResponseEntity<List<MonitoreoA>> obtenerJaula(@PathVariable Integer jaulaId) {
            return ResponseEntity.ok(monitoreoService.obtenerJaula(jaulaId));
        }

    //PUT, actualizar un registro existente
    @PutMapping ("/{id}")
    @Operation(summary = "Actualizar un registro existente de monitoreo", description = "Permite modificar los datos de un registro buscando por su ID")
    public ResponseEntity<?>actualizarMonitoreo(@PathVariable Integer id, @Valid @RequestBody UpdateMonitoreoRequest request,
        BindingResult result) {
            //manejo de error de validacion del dto
            if(result.hasErrors()) {
                Map<String, String> errores = new HashMap<>();
                result.getFieldErrors().forEach(error->errores.put(error.getField(), error.getDefaultMessage()));
                return new ResponseEntity<>(errores, HttpStatus.BAD_REQUEST);
            }
            try {
                //convierte el dto al modelo de bd
                MonitoreoA monitoreoActualizado = MonitoreoMapper.toMonitoreoActualizado(id, request);
                //guardar en bd
                MonitoreoA guardado = monitoreoService.saveMonitoreo(monitoreoActualizado);
                    return ResponseEntity.ok(guardado);


            }catch (RuntimeException e) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());

            }
    }
        //eliminar un registro(devuelve 204)
        @DeleteMapping("/{id}")
        @Operation(summary = "Eliminar un monitoreo", description = "Elimina un registro de M.Ambiental de la BD")
        public ResponseEntity<Void> deleteMonitoreo(@PathVariable Integer id) {
            monitoreoService.deleteMonitoreo(id);
            return ResponseEntity.noContent().build();
        }
        
}