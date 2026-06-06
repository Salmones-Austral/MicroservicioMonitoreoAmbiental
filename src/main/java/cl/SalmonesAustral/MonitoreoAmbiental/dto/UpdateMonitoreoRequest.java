package cl.SalmonesAustral.MonitoreoAmbiental.dto;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

public record UpdateMonitoreoRequest (
    @NotNull(message = "El id de jaula no puede estar vacio")
    Integer jaulaId,

    @NotNull(message = "El id de usuario no puede estar vacio")
    Integer usuarioId,
    
    @NotNull(message = "Debe registrar la temperatura")
    @PositiveOrZero(message = "La temperatura no puede ser un valor negativo")
    Double temperatura,

    @NotNull(message = "Debe registrar el oxigeno disuelto")
    @PositiveOrZero(message = "El oxigeno disuelto no puede ser negativo")
    Double oxigenoDisuelto,

    @NotNull(message = "Debe registrar la salinidad")
    @PositiveOrZero(message = "La salinidad no puede ser negativa")
    Double salinidad,

    @NotNull(message = "Debe registar si hay un bloom de algas")
    Boolean bloomAlgas,

    @NotNull(message = "Debe ingresar la fecha de registro")
    LocalDateTime fechaRegistro

    ){




}
