package dan.tp2021.reportes.service;

import java.time.Instant;
import java.util.List;

import dan.tp2021.reportes.domain.exceptions.ReporteNotFoundException;
import dan.tp2021.reportes.domain.reportes.ReportePedido;

public interface ReportePedidoService {

    ReportePedido generarReporte(Instant fechaInicio, Instant fechaFin);

    ReportePedido getReporteById(Integer id) throws ReporteNotFoundException;

    List<ReportePedido> getAllReportes();
}
