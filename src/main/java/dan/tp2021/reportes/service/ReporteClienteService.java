package dan.tp2021.reportes.service;

import java.time.LocalDate;
import java.util.List;

import dan.tp2021.reportes.domain.exceptions.ReporteNotFoundException;
import dan.tp2021.reportes.domain.reportes.ReporteCliente;

public interface ReporteClienteService {

    ReporteCliente generarReporte(LocalDate fechaInicio, LocalDate fechaFin) throws Exception;

    ReporteCliente getReporteById(Integer id) throws ReporteNotFoundException;

    List<ReporteCliente> getAllReportes();
}
