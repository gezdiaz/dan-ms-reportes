package dan.tp2021.reportes.service;

import java.time.Instant;

import dan.tp2021.reportes.domain.reportes.ReporteMaterial;

public interface ReporteMaterialService {

    ReporteMaterial generarReporte(Instant fechaInicio, Instant fechaFin);

}
