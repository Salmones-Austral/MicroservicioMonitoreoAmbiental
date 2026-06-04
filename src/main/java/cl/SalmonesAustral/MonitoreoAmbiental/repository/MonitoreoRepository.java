package cl.SalmonesAustral.MonitoreoAmbiental.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import cl.SalmonesAustral.MonitoreoAmbiental.modelo.MonitoreoA;


@Repository

public interface MonitoreoRepository extends JpaRepository<MonitoreoA, Integer> {
    //Query METHODS
    List<MonitoreoA>findByJaulaId(int jaulaId);
    List<MonitoreoA>findByUsuarioId(int usuarioId);

    //Busca registros donde haya marea roja activa
    List<MonitoreoA>findByBloomAlgasTrue();

    //CUSTOM QUERIES
         //buscar por jaula
    @Query(value="SELECT * FROM tabla_monitoreo WHERE jaula_id= :jaulaId", nativeQuery=true)
    List<MonitoreoA>selectPorJaula(@Param("jaulaId") int jaulaId);
    //buscar por usuario
    @Query(value="SELECT * FROM tabla_monitoreo WHERE usuario_id= :usuarioId", nativeQuery = true)
    List<MonitoreoA>selectPorUsuario(@Param("usuarioId") int usuarioId);

    //
    default int totalMonitoreos() {
        return(int)this.count();
    }
}
