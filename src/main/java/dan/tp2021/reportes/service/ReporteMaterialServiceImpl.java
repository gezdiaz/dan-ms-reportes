package dan.tp2021.reportes.service;

import org.springframework.stereotype.Service;

import java.time.LocalDate;

import dan.tp2021.reportes.domain.reportes.ReporteMateriales;

@Service
public class ReporteMaterialServiceImpl implements ReporteMaterialService{

    @Override
    public ReporteMateriales generarReporte(LocalDate fechaInicio, LocalDate fechaFin) {
        return null;
    }
}
