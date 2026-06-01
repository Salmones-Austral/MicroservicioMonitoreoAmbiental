package cl.SalmonesAustral.MonitoreoAmbiental.dto;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotNull;

public record CreateMonitoreoRequest (
    @NotNull(message ="El id de jaula no puede estar vacio")
    int jaulaId,
    @NotNull(message = "Debe regitrar el oxigeno disuelto")
    double oxigenoDisuelto,
    @NotNull(message = "Debe registar la salinidad")
    double salinidad,
    @NotNull(message = "Debe ingresar la fecha de registro")
    LocalDateTime fechaRegistro,
    @NotNull(message = "El id de usuario no puede estar vacio")
    int usuarioId

) {

}
