package cl.SalmonesAustral.MonitoreoAmbiental.service;


import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import cl.SalmonesAustral.MonitoreoAmbiental.repository.MonitoreoRepository;
import cl.SalmonesAustral.MonitoreoAmbiental.modelo.MonitoreoA;
import java.util.List;

@Service

public class MonitoreoService {
    
    private final MonitoreoRepository monitoreoRepository;
    private final WebClient webClient;


    public MonitoreoService(MonitoreoRepository monitoreoRepository, WebClient webClient) {
        this.monitoreoRepository = monitoreoRepository;
        this.webClient = webClient;
    }
    // GET obtener todos los registros
    public List<MonitoreoA>getAllMonitoreo() {
        return monitoreoRepository.findAll();
    }
    //POST guardar
    public MonitoreoA saveMonitoreo(MonitoreoA monitoreo) {
        MonitoreoA guardado = monitoreoRepository.save(monitoreo);
        if(guardado.getOxigenoDisuelto()<4.0 || guardado.getBloomAlgas()) {
            System.out.println("===¡EMERGENCIA! Conectando a ALERTAS===");
            try{
                String respuesta = webClient.post()
                    .uri("http://localhost:8083/api/vi/alertas")
                    .bodyValue(guardado)
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();
                System.out.println("¡Alerta enviada con exito!. Respuesta: " + respuesta);
            }catch (Exception e) {
                System.out.println("Alerta NO fue enviada (Servidor 8083 apagado) ");
                System.out.println("Detalle del error: " + e.getMessage());
            }
        }
        return guardado;
    }
    //GET por id
    public MonitoreoA getMonitoreoId(Integer id) {
        return monitoreoRepository.findById(id).orElse(null);
    }
    //PUT actualizar
    public MonitoreoA updateMonitoreo(MonitoreoA monitoreo) {
        return monitoreoRepository.save(monitoreo);
    }
    //DELETE
    public String deleteMonitoreo(Integer id) {
        monitoreoRepository.deleteById(id);
        return "monitoreo eliminado";
    }

    //La accion la hace el service
    public int totalMonitoreos() {
        return(int) monitoreoRepository.count();
    }
    //obtener por jaula
    public List<MonitoreoA>obtenerJaula(Integer jaulaId) {
        return monitoreoRepository.selectPorJaula(jaulaId);
        //.orElse(null);

    }

}
