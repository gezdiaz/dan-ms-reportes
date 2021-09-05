package dan.tp2021.reportes.dao.external;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.Instant;
import java.util.List;

import dan.tp2021.reportes.domain.external.material.MovimientosStock;

public interface MovimientosStockRepository extends JpaRepository<MovimientosStock, Integer> {

    List<MovimientosStock> findMovimientosStockByFechaBetween(Instant fechaInicio, Instant fechaFin);

}
