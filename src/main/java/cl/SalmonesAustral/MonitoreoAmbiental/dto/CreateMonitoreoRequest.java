package cl.SalmonesAustral.MonitoreoAmbiental.dto;

import java.time.LocalDateTime;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

public record CreateMonitoreoRequest (
    @NotNull(message ="El id de jaula no puede estar vacio")
    Integer jaulaId,

    @NotNull(message="Debe registrar la temperatura")
    @PositiveOrZero(message="La temperatura no puede ser un valor negativo")
    Double temperatura,

    @NotNull(message = "Debe regitrar el oxigeno disuelto")
    @PositiveOrZero(message="El oxigeno disuelto no puede ser negativo")
    Double oxigenoDisuelto,

    @NotNull(message = "Debe registar la salinidad")
    @PositiveOrZero(message="La salinidad no puede ser negativa")
    Double salinidad,
    
    @NotNull(message="Debe registrar si hay un bloom de algas")
    Boolean bloomAlgas,

    @NotNull(message = "Debe ingresar la fecha de registro")
    LocalDateTime fechaRegistro,

    @NotNull(message = "El id de usuario no puede estar vacio")
    Integer usuarioId

) {

}
