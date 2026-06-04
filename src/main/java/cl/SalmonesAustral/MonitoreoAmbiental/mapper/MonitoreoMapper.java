package cl.SalmonesAustral.MonitoreoAmbiental.mapper;

import cl.SalmonesAustral.MonitoreoAmbiental.dto.CreateMonitoreoRequest;
import cl.SalmonesAustral.MonitoreoAmbiental.modelo.MonitoreoA;

public class MonitoreoMapper {
    //Post
    public static MonitoreoA toMonitoreo (CreateMonitoreoRequest request ) {
        return new MonitoreoA(
        0,
        request.jaulaId(),
        request.temperatura(),
        request.oxigenoDisuelto(),
        request.salinidad(),
        request.bloomAlgas(),
        request.fechaRegistro(),
        request.usuarioId()
    );

           
        
    }

}
