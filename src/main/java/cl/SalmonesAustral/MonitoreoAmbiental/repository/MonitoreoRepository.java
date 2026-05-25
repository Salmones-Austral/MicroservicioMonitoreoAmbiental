package cl.SalmonesAustral.MonitoreoAmbiental.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import cl.SalmonesAustral.MonitoreoAmbiental.modelo.MonitoreoA;



@Repository

public interface MonitoreoRepository extends JpaRepository<MonitoreoA, Integer> {
         //???

    List<MonitoreoA> getByJaulaId(int jaulaId);

}
