package cl.SalmonesAustral.MonitoreoAmbiental.mapper;

import cl.SalmonesAustral.MonitoreoAmbiental.dto.CreateRequestMonitoreo;
import cl.SalmonesAustral.MonitoreoAmbiental.modelo.MonitoreoA;

public class MonitoreoMapper {
    public static MonitoreoA toMonitoreo (CreateRequestMonitoreo request ) {
        MonitoreoA monitoreo = new MonitoreoA();
        monitoreo.getId();
        monitoreo.getJaulaId();
        monitoreo.getTemperatura();
        monitoreo.getOxigenoDisuelto();
        monitoreo.getSalinidad();
        monitoreo.getFechaRegistro();
        monitoreo.getUsuarioId();
        return monitoreo;
           
        
    }

}
