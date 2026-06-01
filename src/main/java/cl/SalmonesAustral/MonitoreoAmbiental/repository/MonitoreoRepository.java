package cl.SalmonesAustral.MonitoreoAmbiental.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import cl.SalmonesAustral.MonitoreoAmbiental.modelo.MonitoreoA;



@Repository

public interface MonitoreoRepository extends JpaRepository<MonitoreoA, Integer> {
         //buscar por jaula

    List<MonitoreoA> findByJaulaId(int jaulaId);

    //custom total de registros
    @Query("SELECT m COUNT(m) FROM MonitoreoA m")
    int totalMonitoreos();
    @Query ("SELECT m FROM MonitoreoA m WHERE m.jaulaId = :jaulaId")
    List<MonitoreoA>selectPorJaula(int jaulaId);

}
