package dan.tp2021.reportes.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import dan.tp2021.reportes.dao.ItemClienteRepository;
import dan.tp2021.reportes.dao.ReporteClienteRepository;
import dan.tp2021.reportes.domain.exceptions.ReporteNotFoundException;
import dan.tp2021.reportes.domain.items.ItemCliente;
import dan.tp2021.reportes.domain.reportes.ReporteCliente;

@Service
public class ReporteClienteServiceImpl implements ReporteClienteService {

    private static final Logger logger = LoggerFactory.getLogger(ReporteClienteServiceImpl.class);

    private final ReporteClienteRepository reporteClienteRepository;

    private final ItemClienteRepository itemClienteRepository;

    public ReporteClienteServiceImpl(ReporteClienteRepository reporteClienteRepository, ItemClienteRepository itemClienteRepository) {
        this.reporteClienteRepository = reporteClienteRepository;
        this.itemClienteRepository = itemClienteRepository;
    }

    @Override
    public ReporteCliente generarReporte(LocalDate fechaInicio, LocalDate fechaFin) throws Exception{

        logger.debug("generarReporte: Entro a generar reporte de cliente");

        ReporteCliente reporteCliente = new ReporteCliente();
        reporteCliente.setFechaInicio(LocalDate.of(2021, 8, 1));
        reporteCliente.setFechaFin(LocalDate.of(2021, 8, 10));

        List<ItemCliente> items = new ArrayList<>();
        Double ingresos = 0.0;
        for (int i = 1; i < 10; i++) {
            ItemCliente item = new ItemCliente();
            item.setIdCliente(i);
            item.setCantidadObras(i * 2);
            item.setMail("cliente" + i + "@email.com");
            item.setGananciasGeneradas(150.50 * i);
            item.setCuit(((i * 165) / 24) * 354 + "");
            item.setRazonSocial("Razón social cliente " + i);
            items.add(item);
            ingresos += item.getGananciasGeneradas();
        }
        reporteCliente.setItems(items);
        reporteCliente.setCantidadClientes(items.size());
        reporteCliente.setIngresosTotales(ingresos);

        logger.debug("generarReporte: reporte generado a para guardar: " + reporteCliente);

        reporteCliente = reporteClienteRepository.save(reporteCliente);

        logger.debug("generarReporte: reporte después de guardado: " + reporteCliente);

        return reporteCliente;
    }

    @Override
    public ReporteCliente getReporteById(Integer id) throws ReporteNotFoundException {
        Optional<ReporteCliente> optional = reporteClienteRepository.findById(id);

        if(optional.isPresent()){
            return optional.get();
        }

        //tirar una excepción porque no se encontró el reporte con ese id (404)
        throw new ReporteNotFoundException("No se encontró el cliente con id " + id);
    }

    @Override
    public List<ReporteCliente> getAllReportes() {
        return reporteClienteRepository.findAll();
    }
}
