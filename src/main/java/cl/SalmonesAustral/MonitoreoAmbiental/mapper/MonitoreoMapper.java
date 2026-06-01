package cl.SalmonesAustral.MonitoreoAmbiental.mapper;

import cl.SalmonesAustral.MonitoreoAmbiental.dto.CreateMonitoreoRequest;
import cl.SalmonesAustral.MonitoreoAmbiental.modelo.MonitoreoA;

public class MonitoreoMapper {
    public static MonitoreoA toMonitoreo (CreateMonitoreoRequest request ) {
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
