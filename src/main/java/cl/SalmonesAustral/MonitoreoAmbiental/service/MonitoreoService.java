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
    //obtener todos los registros
    public List<MonitoreoA>getAllMonitoreo() {
        return monitoreoRepository.findAll();
    }
    //guardar
    public MonitoreoA saveMonitoreo(MonitoreoA monitoreo) {
        return monitoreoRepository.save(monitoreo);
    }
    //actualizar
    public MonitoreoA updateMonitoreo(MonitoreoA monitoreo) {
        return monitoreoRepository.save(monitoreo);
    }
    //borrar
    public String deleteMonitoreo(int id) {
        monitoreoRepository.deleteById(id);
        return "monitoreo eliminado";
    }

    //obtener por jaula
    public List<MonitoreoA>ObtenerJaula(int jaulaId) {
        return monitoreoRepository.getByJaulaId(jaulaId);
        //.orElse(null);

    }
    public void setById (MonitoreoA monitoreo) {
        monitoreoRepository.save(monitoreo);
    }

}
