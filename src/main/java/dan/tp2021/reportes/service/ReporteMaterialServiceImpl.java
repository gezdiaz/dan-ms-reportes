package dan.tp2021.reportes.service;

import org.springframework.stereotype.Service;

import java.time.LocalDate;

import dan.tp2021.reportes.domain.reportes.ReporteMaterial;

@Service
public class ReporteMaterialServiceImpl implements ReporteMaterialService{

    @Override
    public ReporteMaterial generarReporte(LocalDate fechaInicio, LocalDate fechaFin) {
        return null;
    }
}
