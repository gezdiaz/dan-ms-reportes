package dan.tp2021.reportes.service;

import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

import dan.tp2021.reportes.domain.reportes.ReportePedido;

@Service
public class ReportePedidoServiceImpl implements ReportePedidoService{

    @Override
    public ReportePedido generarReporte(Instant fechaInicio, Instant fechaFin) {
        return null;
    }

    @Override
    public ReportePedido getReporteById(Integer id) {
        return null;
    }

    @Override
    public List<ReportePedido> getAllReportes() {
        return null;
    }
}
