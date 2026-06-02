package cl.SalmonesAustral.MonitoreoAmbiental.modelo;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
//import lombok.Data;


@Entity //decorador
@Table(name = "tabla_monitoreo")
public class MonitoreoA {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="jaula_id", nullable=false)
    private int jaulaId;

    @Column(name="temperatura", nullable=false)
    private double temperatura;

    @Column(name="oxigeno_disuelto", nullable=false)
    private double oxigenoDisuelto;

    @Column(name="salinidad", nullable=false)
    private double salinidad;

    @Column(name="fecha_registro", nullable=false)
    private LocalDateTime fechaRegistro;

    @Column (name="usuario_id", nullable=false)
    private int usuarioId;

    //Constructor sin argumentos
    public MonitoreoA(){}
    //Constructor completo
    public MonitoreoA(int id, int jaulaId, double temperatura, double oxigenoDisuelto,
        double salinidad, LocalDateTime fechaRegistro, int usuarioId) {
            this.id=id;
            this.jaulaId=jaulaId;
            this.temperatura=temperatura;
            this.oxigenoDisuelto=oxigenoDisuelto;
            this.salinidad=salinidad;
            this.fechaRegistro=fechaRegistro;
            this.usuarioId=usuarioId;
        }
    //GETTER AND SETTER
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id=id;
    }
    public int getJaulaId() {
        return jaulaId;
    }
    public void setJaulaId(int jaulaId) {
        this.jaulaId=jaulaId;
    }
    public double getTemperatura() {
        return temperatura;
    }
    public void setTemperatura(double temperatura) {
        this.temperatura=temperatura;
    }
    public double getOxigenoDisuelto() {
        return oxigenoDisuelto;
    }
    public void setOxigenoDisuelto(double oxigenoDisuelto) {
        this.oxigenoDisuelto=oxigenoDisuelto;
    }
    public double getSalinidad() {
        return salinidad;
    }
    public void setSalinidad(double salinidad) {
        this.salinidad=salinidad;
    }
    public LocalDateTime getFechaRegistro() {
        return fechaRegistro;
    }
    public void setFechaRegistro(LocalDateTime fechaRegistro) {
        this.fechaRegistro=fechaRegistro;
    }
    public int getUsuarioId() {
        return usuarioId;
    }
    public void setUsuarioId(int usuarioId) {
        this.usuarioId=usuarioId;
    }


}
