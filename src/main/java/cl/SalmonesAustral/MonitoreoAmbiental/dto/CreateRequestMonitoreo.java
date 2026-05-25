package cl.SalmonesAustral.MonitoreoAmbiental.dto;

public record CreateRequestMonitoreo (int jaulaId, double temperatura, double oxigenoDisuelto, double salinidad, int usuarioId) {

}
