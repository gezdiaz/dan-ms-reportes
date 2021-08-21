package dan.tp2021.reportes.service;

import java.time.LocalDate;

import dan.tp2021.reportes.domain.reportes.ReporteClientes;

public interface ReporteClienteService {

    ReporteClientes generarReporte(LocalDate fechaInicio, LocalDate fechaFin) throws Exception;

}
