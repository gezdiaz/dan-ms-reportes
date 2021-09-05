package dan.tp2021.reportes.service;

import java.time.Instant;
import java.util.List;

import dan.tp2021.reportes.exceptions.ReporteNotFoundException;
import dan.tp2021.reportes.domain.reportes.ReporteMaterial;
import dan.tp2021.reportes.exceptions.SinMovimientosException;
import dan.tp2021.reportes.exceptions.SinPedidosException;

public interface ReporteMaterialService {

    ReporteMaterial generarReporte(Instant fechaInicio, Instant fechaFin) throws SinMovimientosException;

    ReporteMaterial getReporteById(Integer id) throws ReporteNotFoundException;

    List<ReporteMaterial> getAllReportes();
}
