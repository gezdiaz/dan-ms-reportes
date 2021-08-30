package dan.tp2021.reportes.service;

import org.springframework.stereotype.Service;

import java.time.Instant;

import dan.tp2021.reportes.domain.reportes.ReportePedido;

@Service
public class ReportePedidoServiceImpl implements ReportePedidoService{

    @Override
    public ReportePedido generarReporte(Instant fechaInicio, Instant fechaFin) {
        return null;
    }
}
