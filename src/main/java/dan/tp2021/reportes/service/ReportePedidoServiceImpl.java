package dan.tp2021.reportes.service;

import org.springframework.stereotype.Service;

import java.time.LocalDate;

import dan.tp2021.reportes.domain.reportes.ReportePedidos;

@Service
public class ReportePedidoServiceImpl implements ReportePedidoService{

    @Override
    public ReportePedidos generarReporte(LocalDate fechaInicio, LocalDate fechaFin) {
        return null;
    }
}
