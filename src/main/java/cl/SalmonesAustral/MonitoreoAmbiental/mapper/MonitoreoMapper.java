package cl.SalmonesAustral.MonitoreoAmbiental.mapper;

import cl.SalmonesAustral.MonitoreoAmbiental.dto.CreateMonitoreoRequest;
import cl.SalmonesAustral.MonitoreoAmbiental.dto.UpdateMonitoreoRequest;
import cl.SalmonesAustral.MonitoreoAmbiental.modelo.MonitoreoA;

public class MonitoreoMapper {
    //Post
    public static MonitoreoA toMonitoreo (CreateMonitoreoRequest request ) {
        return new MonitoreoA(
        null,
        request.jaulaId(),
        request.usuarioId(),
        request.temperatura(),
        request.oxigenoDisuelto(),
        request.salinidad(),
        request.bloomAlgas(),
        request.fechaRegistro()
        
        ); 
        
    }
    //Put
    public static MonitoreoA toMonitoreoActualizado(Integer id, UpdateMonitoreoRequest request) {
        return new MonitoreoA(
            id,
            request.jaulaId(),
            request.usuarioId(),
            request.temperatura(),
            request.oxigenoDisuelto(),
            request.salinidad(),
            request.bloomAlgas(),
            request.fechaRegistro()
            
        );
    }

}
