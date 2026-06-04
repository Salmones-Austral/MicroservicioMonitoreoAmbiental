package cl.SalmonesAustral.MonitoreoAmbiental.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import cl.SalmonesAustral.MonitoreoAmbiental.modelo.MonitoreoA;
import cl.SalmonesAustral.MonitoreoAmbiental.service.MonitoreoService;
import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.List;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import cl.SalmonesAustral.MonitoreoAmbiental.dto.CreateMonitoreoRequest;
import cl.SalmonesAustral.MonitoreoAmbiental.exception.ResourceNotFoundException;
import cl.SalmonesAustral.MonitoreoAmbiental.mapper.MonitoreoMapper;



@RestController
@RequestMapping("/api/v1/monitoreo")
public class MonitoreoController {

        private final MonitoreoService monitoreoService;
       

        // Constructor injection (mejor práctica 2026)
        public MonitoreoController(MonitoreoService monitoreoService) {
                this.monitoreoService = monitoreoService;
                
        }
        //GET:listar todos los monitoreos
        @GetMapping
        public ResponseEntity<List<MonitoreoA>> getAllMonitoreo() {
            List<MonitoreoA> monitoreos=monitoreoService.getAllMonitoreo();
            return ResponseEntity.ok(monitoreos);
        }
        //POST: agregar un monitoreo
        @PostMapping
        public ResponseEntity<MonitoreoA> agregarMonitoreo(@Valid @RequestBody CreateMonitoreoRequest requestMonitoreo) {
            MonitoreoA nuevoMonitoreo = monitoreoService.saveMonitoreo(MonitoreoMapper.toMonitoreo(requestMonitoreo));
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
            int total = monitoreoService.totalMonitoreos();
            return ResponseEntity.ok(total);
        }
        //GET:filtrar por una jaula especifica
        @GetMapping("/jaula/{jaulaId}")
        public ResponseEntity<List<MonitoreoA>> obtenerJaula(@PathVariable Integer jaulaId) {
            List<MonitoreoA>lista=monitoreoService.obtenerJaula(jaulaId);
            return ResponseEntity.ok(lista);
        }

    
        
        
}