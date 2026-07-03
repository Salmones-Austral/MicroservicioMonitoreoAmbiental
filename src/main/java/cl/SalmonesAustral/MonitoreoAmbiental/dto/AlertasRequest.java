package cl.SalmonesAustral.MonitoreoAmbiental.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class AlertasRequest {

    private Long id;
    private Long mortalidadId;
    private int jaulaId;
    private String mensaje;
    private String nivel;
    private LocalDateTime fecha;
    private String estado;
    private double porcentaje; 
        
    //public AlertasRequest() {}

    // GETTERS Y SETTERS
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getMortalidadId() { return mortalidadId; }
    public void setMortalidadId(Long mortalidadId) { this.mortalidadId = mortalidadId; }
    public int getJaulaId() { return jaulaId; }
    public void setJaulaId(int jaulaId) { this.jaulaId = jaulaId; }
    public String getMensaje() { return mensaje; }
    public void setMensaje(String mensaje) { this.mensaje = mensaje; }
    public String getNivel() { return nivel; }
    public void setNivel(String nivel) { this.nivel = nivel; }
    public LocalDateTime getFecha() { return fecha; }
    public void setFecha(LocalDateTime fecha) { this.fecha = fecha; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
    public double getPorcentaje() { return porcentaje; }
    public void setPorcentaje(double porcentaje) { this.porcentaje = porcentaje; }
}


