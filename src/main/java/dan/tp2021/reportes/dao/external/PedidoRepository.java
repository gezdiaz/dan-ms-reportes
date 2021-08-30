package dan.tp2021.reportes.dao.external;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;

import dan.tp2021.reportes.domain.external.pedido.Pedido;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Integer> {

    List<Pedido> findByFechaPedidoBetween(Instant fechaInicio, Instant fechaFin);


    @Query("SELECT p FROM Pedido p JOIN Obra o ON p.obra = o JOIN Cliente c ON o.cliente = c WHERE c.id = ?3 AND p.fechaPedido BETWEEN ?1 AND ?2")
    List<Pedido> findByFechaPedidoBetweenAndClienteId(Instant fechaInicio, Instant fechaFin, Integer clienteId);

}
