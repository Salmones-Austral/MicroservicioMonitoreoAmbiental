package cl.SalmonesAustral.MonitoreoAmbiental.modelo;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
//import lombok.Data;

//@Data
@Entity //decorador
@Table(name = "tabla_monitoreo")
public class MonitoreoA {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;

    @Column(name="jaula_id", nullable=false)
    private Integer jaulaId;

    @Column (name="usuario_id", nullable=false)
    private Integer usuarioId;

    @Column(name="temperatura", nullable=false)
    private Double temperatura;

    @Column(name="oxigeno_disuelto", nullable=false)
    private Double oxigenoDisuelto;

    @Column(name="salinidad", nullable=false)
    private Double salinidad;
    @Column(name="bloom_algas", nullable=false)
    private Boolean bloomAlgas;

    @Column(name="fecha_registro", nullable=false)
    private LocalDateTime fechaRegistro;

    

    //Constructor sin argumentos
    public MonitoreoA(){}
    //Constructor completo
    public MonitoreoA(Integer id, Integer jaulaId, Integer usuarioId, Double temperatura, Double oxigenoDisuelto,
        Double salinidad, Boolean bloomAlgas, LocalDateTime fechaRegistro) {
            this.id=id;
            this.jaulaId=jaulaId;
            this.usuarioId=usuarioId;
            this.temperatura=temperatura;
            this.oxigenoDisuelto=oxigenoDisuelto;
            this.salinidad=salinidad;
            this.bloomAlgas=bloomAlgas;
            this.fechaRegistro=fechaRegistro;
            
        }
    //GETTER AND SETTER
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id=id;
    }
    public Integer getJaulaId() {
        return jaulaId;
    }
    public void setJaulaId(Integer jaulaId) {
        this.jaulaId=jaulaId;
    }
     public Integer getUsuarioId() {
        return usuarioId;
    }
    public void setUsuarioId(Integer usuarioId) {
        this.usuarioId=usuarioId;
    }
    public Double getTemperatura() {
        return temperatura;
    }
    public void setTemperatura(Double temperatura) {
        this.temperatura=temperatura;
    }
    public Double getOxigenoDisuelto() {
        return oxigenoDisuelto;
    }
    public void setOxigenoDisuelto(Double oxigenoDisuelto) {
        this.oxigenoDisuelto=oxigenoDisuelto;
    }
    public Double getSalinidad() {
        return salinidad;
    }
    public void setSalinidad(Double salinidad) {
        this.salinidad=salinidad;
    }
    public Boolean getBloomAlgas() {
        return bloomAlgas;
    }
    public void setBloomAlgas(Boolean bloomAlgas) {
        this.bloomAlgas=bloomAlgas;
    }
    public LocalDateTime getFechaRegistro() {
        return fechaRegistro;
    }
    public void setFechaRegistro(LocalDateTime fechaRegistro) {
        this.fechaRegistro=fechaRegistro;
    }
   


}
