package dan.tp2021.reportes.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dan.tp2021.reportes.domain.reportes.ReportePedido;

@Repository
public interface ReportePedidoRepository extends JpaRepository<ReportePedido, Integer> {
}
