package dan.tp2021.reportes.service;

import java.time.Instant;
import java.util.List;

import dan.tp2021.reportes.domain.exceptions.ReporteNotFoundException;
import dan.tp2021.reportes.domain.reportes.ReporteCliente;

public interface ReporteClienteService {

    ReporteCliente generarReporte(Instant fechaInicio, Instant fechaFin) throws Exception;

    ReporteCliente getReporteById(Integer id) throws ReporteNotFoundException;

    List<ReporteCliente> getAllReportes();
}
