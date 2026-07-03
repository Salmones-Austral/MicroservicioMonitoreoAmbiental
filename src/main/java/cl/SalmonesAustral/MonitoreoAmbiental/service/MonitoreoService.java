package cl.SalmonesAustral.MonitoreoAmbiental.service;


import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import cl.SalmonesAustral.MonitoreoAmbiental.repository.MonitoreoRepository;
import cl.SalmonesAustral.MonitoreoAmbiental.dto.AlertasRequest;
import cl.SalmonesAustral.MonitoreoAmbiental.modelo.MonitoreoA;

import java.time.LocalDateTime;
import java.util.List;


@Service

public class MonitoreoService {
    
    private final MonitoreoRepository monitoreoRepository;
    private final WebClient jaulasWebClient;
    private final WebClient alertasWebClient;


    public MonitoreoService(MonitoreoRepository monitoreoRepository, 
        @Qualifier("jaulasWebClient") WebClient jaulasWebClient,
        @Qualifier("alertasWebClient") WebClient alertasWebClient) {
            this.monitoreoRepository = monitoreoRepository;
            this.jaulasWebClient = jaulasWebClient;
            this.alertasWebClient = alertasWebClient;
        
    }
    // GET listar todos los registros
    public List<MonitoreoA>getAllMonitoreo() {
        return monitoreoRepository.findAll();
    }
    //POST guardar
    public MonitoreoA saveMonitoreo(MonitoreoA monitoreo) {
        System.out.println("INICIANDO FLUJO DE MONITOREO AMBIENTAL....");

        try {
            System.out.println("Validando si existe la jaula....");
            jaulasWebClient.get()
            .uri("/" + monitoreo.getJaulaId())
            .retrieve()
            .bodyToMono(Object.class)
            .block();
            System.out.println("Validacion exitosa: la jaula con ID" + monitoreo.getJaulaId() + "existe");
        }catch (Exception e) {
            System.out.println("Validación fallida" + e.getMessage());
            throw new RuntimeException("Error de conexion: La jaula con ID " + monitoreo.getJaulaId() + "no existe o no responde");
        }
        //si la jaula existe: guarda monitoreo en la bd de neon
        MonitoreoA guardado = monitoreoRepository.save(monitoreo);
        System.out.println("Guardado con exito");
        

        //2da conexion, alertas por si hay anomalias en el agua
        if(guardado.getOxigenoDisuelto()<4.0 || guardado.getBloomAlgas()) {
            System.out.println("===¡EMERGENCIA! Conectando a ALERTAS===");
            
            try{ 
            AlertasRequest alertas = new AlertasRequest(
                null,
                null,
                guardado.getJaulaId(),
                "Alerta Preventiva Ambiental: Oxigeno bajo (" + guardado.getOxigenoDisuelto() + "mg/L) o presencia de Bloom de Algas",
                guardado.getBloomAlgas() ? "CRITICO" : "MEDIO",
                LocalDateTime.now(),
                "ACTIVA",
                0.0
                );
        
            //post para que el tecnico resiva su mensaje devuelta
            String respuestaAlerta = alertasWebClient.post()
                .uri(" ")
                .bodyValue(alertas)
                .retrieve()
                .bodyToMono(String.class)
                .block();
            System.out.println("Alerta ambiental enviada. Respuesta: " + respuestaAlerta);           
        }catch (Exception e) {
            System.out.println("Fallo en la comunicacion");
            System.out.println("Detalle del error: " + e.getMessage());
        }

    }
    return guardado;
    }

    //GET por id
    public MonitoreoA getMonitoreoId(Integer id) {
        return monitoreoRepository.findById(id).orElse(null);
    }

    //DELETE
    public String deleteMonitoreo(Integer id) {
        monitoreoRepository.deleteById(id);
        return "monitoreo eliminado";
    }

    //obtener por jaula
    public List<MonitoreoA>obtenerJaula(Integer jaulaId) {
        return monitoreoRepository.selectPorJaula(jaulaId);

    }



}