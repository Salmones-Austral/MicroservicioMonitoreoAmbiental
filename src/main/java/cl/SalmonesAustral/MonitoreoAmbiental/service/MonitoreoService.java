package cl.SalmonesAustral.MonitoreoAmbiental.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cl.SalmonesAustral.MonitoreoAmbiental.repository.MonitoreoRepository;
import cl.SalmonesAustral.MonitoreoAmbiental.modelo.MonitoreoA;
import java.util.List;

@Service

public class MonitoreoService {
    @Autowired
    private MonitoreoRepository monitoreoRepository;
    // GET obtener todos los registros
    public List<MonitoreoA>getAllMonitoreo() {
        return monitoreoRepository.findAll();
    }
    //POST guardar
    public MonitoreoA saveMonitoreo(MonitoreoA monitoreo) {
        return monitoreoRepository.save(monitoreo);
    }
    //GET por id
    public MonitoreoA getMonitoreoId(int id) {
        return monitoreoRepository.findById(id).orElse(null);
    }
    //PUT actualizar
    public MonitoreoA updateMonitoreo(MonitoreoA monitoreo) {
        return monitoreoRepository.save(monitoreo);
    }
    //DELETE
    public String deleteMonitoreo(int id) {
        monitoreoRepository.deleteById(id);
        return "monitoreo eliminado";
    }

    //La accion la hace el service
    public int totalMonitoreos() {
        return(int) monitoreoRepository.count();
    }
    //obtener por jaula
    public List<MonitoreoA>obtenerJaula(int jaulaId) {
        return monitoreoRepository.selectPorJaula(jaulaId);
        //.orElse(null);

    }

}
