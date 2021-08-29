package dan.tp2021.reportes.service;

import java.time.LocalDate;

import dan.tp2021.reportes.domain.reportes.ReporteMaterial;

public interface ReporteMaterialService {

    ReporteMaterial generarReporte(LocalDate fechaInicio, LocalDate fechaFin);

}
