package dan.tp2021.reportes.service;

import java.time.Instant;
import java.util.List;

import dan.tp2021.reportes.exceptions.ReporteNotFoundException;
import dan.tp2021.reportes.domain.reportes.ReporteCliente;
import dan.tp2021.reportes.exceptions.SinPedidosException;

public interface ReporteClienteService {

    ReporteCliente generarReporte(Instant fechaInicio, Instant fechaFin) throws SinPedidosException, Exception;

    ReporteCliente getReporteById(Integer id) throws ReporteNotFoundException;

    List<ReporteCliente> getAllReportes();
}
