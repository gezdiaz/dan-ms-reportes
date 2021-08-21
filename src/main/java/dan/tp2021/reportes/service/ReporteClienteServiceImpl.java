package dan.tp2021.reportes.service;

import org.springframework.stereotype.Service;

import java.time.LocalDate;

import dan.tp2021.reportes.domain.reportes.ReporteClientes;

@Service
public class ReporteClienteServiceImpl implements ReporteClienteService {


    @Override
    public ReporteClientes generarReporte(LocalDate fechaInicio, LocalDate fechaFin) throws Exception{
        throw new Exception("Mensaje de la excepción en ReporteClientes");
        //return null;
    }
}
