package dan.tp2021.reportes.service;

import java.time.Instant;

import dan.tp2021.reportes.domain.reportes.ReportePedido;

public interface ReportePedidoService {

    ReportePedido generarReporte(Instant fechaInicio, Instant fechaFin);
}
