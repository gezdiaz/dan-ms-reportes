package dan.tp2021.reportes.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import dan.tp2021.reportes.dao.ItemClienteRepository;
import dan.tp2021.reportes.dao.ReporteClienteRepository;
import dan.tp2021.reportes.dao.external.PedidoRepository;
import dan.tp2021.reportes.domain.exceptions.ReporteNotFoundException;
import dan.tp2021.reportes.domain.external.cliente.Cliente;
import dan.tp2021.reportes.domain.external.cliente.Obra;
import dan.tp2021.reportes.domain.external.pedido.Pedido;
import dan.tp2021.reportes.domain.items.ItemCliente;
import dan.tp2021.reportes.domain.reportes.ReporteCliente;

@Service
public class ReporteClienteServiceImpl implements ReporteClienteService {

    private static final Logger logger = LoggerFactory.getLogger(ReporteClienteServiceImpl.class);

    private final ReporteClienteRepository reporteClienteRepository;

    private final ItemClienteRepository itemClienteRepository;

    private final PedidoRepository pedidoRepository;

    public ReporteClienteServiceImpl(ReporteClienteRepository reporteClienteRepository, ItemClienteRepository itemClienteRepository, PedidoRepository pedidoRepository) {
        this.reporteClienteRepository = reporteClienteRepository;
        this.itemClienteRepository = itemClienteRepository;
        this.pedidoRepository = pedidoRepository;
    }

    @Override
    public ReporteCliente generarReporte(Instant fechaInicio, Instant fechaFin) throws Exception{

        logger.debug("generarReporte: Entro a generar reporte de cliente con fechaInicio " + fechaInicio + " y fechaFin " + fechaFin);


        List<ItemCliente> itemsCliente = this.getItemsCliente(fechaInicio, fechaFin);

        ReporteCliente reporteCliente = new ReporteCliente();
//        reporteCliente.setFechaInicio(LocalDate.of(2021, 8, 1));
//        reporteCliente.setFechaFin(LocalDate.of(2021, 8, 10));
//
//        List<ItemCliente> items = new ArrayList<>();
//        Double ingresos = 0.0;
//        for (int i = 1; i < 10; i++) {
//            ItemCliente item = new ItemCliente();
//            item.setIdCliente(i);
//            item.setCantidadObras(i * 2);
//            item.setMail("cliente" + i + "@email.com");
//            item.setGananciasGeneradas(150.50 * i);
//            item.setCuit(((i * 165) / 24) * 354 + "");
//            item.setRazonSocial("Razón social cliente " + i);
//            items.add(item);
//            ingresos += item.getGananciasGeneradas();
//        }
//        reporteCliente.setItems(items);
//        reporteCliente.setCantidadClientes(items.size());
//        reporteCliente.setIngresosTotales(ingresos);
//
//        logger.debug("generarReporte: reporte generado a para guardar: " + reporteCliente);
//
//        reporteCliente = reporteClienteRepository.save(reporteCliente);
//
//        logger.debug("generarReporte: reporte después de guardado: " + reporteCliente);

        return reporteCliente;
    }

    private List<ItemCliente> getItemsCliente(Instant fechaInicio, Instant fechaFin) {

        logger.debug("getItemsCliente: Inicio.");

        List<Pedido> pedidos = pedidoRepository.findByFechaPedidoBetween(fechaInicio, fechaFin);

        logger.debug("getItemsCliente: Todos los pedidos entre las fechas: " + pedidos);

        List<ItemCliente> itemsClientes = new ArrayList<>();
        HashSet<Integer> clientesAgregados = new HashSet<>();

        for (Pedido p : pedidos) {
            Obra obra = p.getObra();
            Cliente cliente = obra.getCliente();
            if (!clientesAgregados.contains(cliente.getId())) {
                //agregar el cliente solo si no está agregado ya
                ItemCliente itemCliente = new ItemCliente();
                itemCliente.setIdCliente(cliente.getId());
                itemCliente.setCuit(cliente.getCuit());
                itemCliente.setMail(cliente.getMail());
                itemCliente.setCantidadObras(cliente.getObras().size());
                itemsClientes.add(itemCliente);
                clientesAgregados.add(cliente.getId());
            }
        }

        logger.debug("getItemsCliente: Items clientes creados: " + itemsClientes);

        for (ItemCliente itemCliente : itemsClientes){
            List<Pedido> pedidosCliente = pedidoRepository.findByFechaPedidoBetweenAndClienteId(fechaInicio, fechaFin, itemCliente.getIdCliente());
            logger.debug("getItemsCliente: pedidos para el cliente con id " + itemCliente.getIdCliente() + ": " + pedidosCliente);
        }

        return itemsClientes;
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
