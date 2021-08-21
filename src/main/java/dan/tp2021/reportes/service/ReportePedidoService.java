package dan.tp2021.reportes.service;

import java.time.LocalDate;

import dan.tp2021.reportes.domain.reportes.ReporteClientes;
import dan.tp2021.reportes.domain.reportes.ReportePedidos;

public interface ReportePedidoService {

    ReportePedidos generarReporte(LocalDate fechaInicio, LocalDate fechaFin);
}
