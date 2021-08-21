package dan.tp2021.reportes.service;

import java.time.LocalDate;

import dan.tp2021.reportes.domain.reportes.ReporteMateriales;

public interface ReporteMaterialService {

    ReporteMateriales generarReporte(LocalDate fechaInicio, LocalDate fechaFin);

}
