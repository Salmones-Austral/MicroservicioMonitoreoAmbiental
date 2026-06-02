package cl.SalmonesAustral.MonitoreoAmbiental.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import cl.SalmonesAustral.MonitoreoAmbiental.modelo.MonitoreoA;



@Repository

public interface MonitoreoRepository extends JpaRepository<MonitoreoA, Integer> {
         //buscar por jaula
    @Query(value="SELECT * FROM tabla_monitoreo WHERE jaula_id= :jaulaId", nativeQuery=true)
    List<MonitoreoA>selectPorJaula(@Param("jaulaId") int jaulaId);
    //buscar por usuario
    @Query(value="SELECT * FROM tabla_monitoreo WHERE usuario_id= :usuarioId", nativeQuery = true)
    List<MonitoreoA>selectPorUsuario(@Param("usuarioId") int usuarioId);

    //la accion la hace el modelo
    default int totalMonitoreos() {
        return(int)this.count();
    }
}
