package cl.SalmonesAustral.MonitoreoAmbiental.modelo;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;


@Entity //decorador
@Table(name = "tabla_monitoreo")
@Data
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
    private int fechaRegistro;

    @Column (name="usuario_id", nullable=false)
    private int usuarioId;


}
