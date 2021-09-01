package dan.tp2021.reportes.service;

import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

import dan.tp2021.reportes.domain.reportes.ReporteMaterial;

@Service
public class ReporteMaterialServiceImpl implements ReporteMaterialService{

    @Override
    public ReporteMaterial generarReporte(Instant fechaInicio, Instant fechaFin) {
        return null;
    }

    @Override
    public ReporteMaterial getReporteById(Integer id) {
        return null;
    }

    @Override
    public List<ReporteMaterial> getAllReportes() {
        return null;
    }
}
