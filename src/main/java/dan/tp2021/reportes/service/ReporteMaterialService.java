package dan.tp2021.reportes.service;

import java.time.Instant;
import java.util.List;

import dan.tp2021.reportes.exceptions.ReporteNotFoundException;
import dan.tp2021.reportes.domain.reportes.ReporteMaterial;

public interface ReporteMaterialService {

    ReporteMaterial generarReporte(Instant fechaInicio, Instant fechaFin);

    ReporteMaterial getReporteById(Integer id) throws ReporteNotFoundException;

    List<ReporteMaterial> getAllReportes();
}
