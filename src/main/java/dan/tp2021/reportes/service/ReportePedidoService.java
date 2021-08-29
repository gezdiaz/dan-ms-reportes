package dan.tp2021.reportes.service;

import java.time.LocalDate;

import dan.tp2021.reportes.domain.reportes.ReportePedido;

public interface ReportePedidoService {

    ReportePedido generarReporte(LocalDate fechaInicio, LocalDate fechaFin);
}
