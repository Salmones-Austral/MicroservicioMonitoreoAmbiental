package cl.SalmonesAustral.MonitoreoAmbiental.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import cl.SalmonesAustral.MonitoreoAmbiental.modelo.MonitoreoA;
import cl.SalmonesAustral.MonitoreoAmbiental.service.MonitoreoService;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.List;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import cl.SalmonesAustral.MonitoreoAmbiental.dto.CreateMonitoreoRequest;
import cl.SalmonesAustral.MonitoreoAmbiental.mapper.MonitoreoMapper;



@RestController
@RequestMapping("/api/v1/monitoreo")
public class MonitoreoController {

        private final MonitoreoService monitoreoService;
       

        // Constructor injection (mejor práctica 2026)
        public MonitoreoController(MonitoreoService monitoreoService) {
                this.monitoreoService = monitoreoService;
                
        }

        @GetMapping
        public List<MonitoreoA> getAllMonitoreo() {
            return this.monitoreoService.getAllMonitoreo();
        }

        @PostMapping
        public MonitoreoA setMonitoreo (@RequestBody CreateMonitoreoRequest requestMonitoreo) {
            MonitoreoA monitoreo = MonitoreoMapper.toMonitoreo(requestMonitoreo);
            System.err.println(monitoreo.getId());
            System.err.println(monitoreo.getJaulaId());
            System.err.println(monitoreo.getTemperatura());
            System.err.println(monitoreo.getOxigenoDisuelto());
            System.err.println(monitoreo.getSalinidad());
            System.err.println(monitoreo.getFechaRegistro());
            System.err.println(monitoreo.getUsuarioId());
           
            return  this.monitoreoService.saveMonitoreo(monitoreo);
        }   
        
        
}