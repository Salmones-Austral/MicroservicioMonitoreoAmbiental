package cl.SalmonesAustral.MonitoreoAmbiental.dto;

public record AlertasRequest (
    Long mortalidadId,
    int jaulaId,
    String mensaje,
    String nivel,
    double porcentaje) {
        
    }


