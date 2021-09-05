package dan.tp2021.reportes.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import dan.tp2021.reportes.dao.ItemClienteRepository;
import dan.tp2021.reportes.dao.ReporteClienteRepository;
import dan.tp2021.reportes.dao.external.ClienteRepository;
import dan.tp2021.reportes.dao.external.PedidoRepository;
import dan.tp2021.reportes.exceptions.ReporteNotFoundException;
import dan.tp2021.reportes.domain.external.cliente.Cliente;
import dan.tp2021.reportes.domain.external.cliente.Obra;
import dan.tp2021.reportes.domain.external.pedido.Pedido;
import dan.tp2021.reportes.domain.items.ItemCliente;
import dan.tp2021.reportes.domain.reportes.ReporteCliente;
import dan.tp2021.reportes.exceptions.SinPedidosException;

@Service
public class ReporteClienteServiceImpl implements ReporteClienteService {

    private static final Logger logger = LoggerFactory.getLogger(ReporteClienteServiceImpl.class);

    private final ReporteClienteRepository reporteClienteRepository;

    private final PedidoRepository pedidoRepository;

    private final ClienteRepository clienteRepository;

    public ReporteClienteServiceImpl(ReporteClienteRepository reporteClienteRepository, ItemClienteRepository itemClienteRepository, PedidoRepository pedidoRepository, ClienteRepository clienteRepository) {
        this.reporteClienteRepository = reporteClienteRepository;
        this.pedidoRepository = pedidoRepository;
        this.clienteRepository = clienteRepository;
    }

    @Override
    public ReporteCliente generarReporte(Instant fechaInicio, Instant fechaFin) throws SinPedidosException{

        logger.debug("generarReporte: Entro a generar reporte de cliente con fechaInicio " + fechaInicio + " y fechaFin " + fechaFin);


        List<ItemCliente> itemsCliente = this.getItemsCliente(fechaInicio, fechaFin);

        ReporteCliente reporteCliente = new ReporteCliente();
        reporteCliente.setGenerado(Instant.now());
        reporteCliente.setFechaInicio(fechaInicio);
        reporteCliente.setFechaFin(fechaFin);

        reporteCliente.setItems(itemsCliente);
        reporteCliente.setCantidadClientes(itemsCliente.size());

        Double ingresosTotales = 0.0;
        for (ItemCliente cliente: itemsCliente){
            ingresosTotales += cliente.getGananciasGeneradas();
        }
        reporteCliente.setIngresosTotales(ingresosTotales);

        logger.debug("generarReporte: reporte generado a para guardar: " + reporteCliente);

        reporteCliente = reporteClienteRepository.save(reporteCliente);

        logger.debug("generarReporte: reporte después de guardado: " + reporteCliente);

        return reporteCliente;
    }

    private List<ItemCliente> getItemsCliente(Instant fechaInicio, Instant fechaFin) throws SinPedidosException {

        logger.debug("getItemsCliente: Inicio.");

        List<Pedido> pedidos = pedidoRepository.findByFechaPedidoBetween(fechaInicio, fechaFin);

        if(pedidos.isEmpty()){
            //no hay pedidos, no se puede generar el reporte.
            throw new SinPedidosException("No se encontraron pedidos entre las fechas " + fechaInicio + " y " + fechaFin + " al generar items para un reporte de clientes.");
        }

        logger.debug("getItemsCliente: Todos los pedidos entre las fechas: " + pedidos);

        List<ItemCliente> itemsClientes = new ArrayList<>();
        HashSet<Integer> clientesAgregados = new HashSet<>();

        for (Pedido p : pedidos) {
            Obra obra = p.getObra();
            Integer clienteId = obra.getCliente().getId();
            if (!clientesAgregados.contains(clienteId)) {
                //agregar el cliente solo si no está agregado ya
                Cliente cliente = clienteRepository.findById(clienteId).get();
                logger.debug("getItemsCliente: Procesando cliente: " + cliente);
                ItemCliente itemCliente = new ItemCliente();
                itemCliente.setIdCliente(cliente.getId());
                itemCliente.setCuit(cliente.getCuit());
                itemCliente.setMail(cliente.getMail());
                itemCliente.setRazonSocial(cliente.getRazonSocial());
                itemCliente.setCantidadObras(cliente.getObras().size());
                itemsClientes.add(itemCliente);
                clientesAgregados.add(cliente.getId());
            }
        }

        logger.debug("getItemsCliente: items después del primer for: " + itemsClientes);

        for (ItemCliente itemCliente : itemsClientes){
            List<Pedido> pedidosCliente = pedidoRepository.findByFechaPedidoBetweenAndClienteId(fechaInicio, fechaFin, itemCliente.getIdCliente());
            logger.debug("getItemsCliente: pedidos para el cliente con id " + itemCliente.getIdCliente() + ": " + pedidosCliente);
            Double gananciasGeneradas = 0.0;
            for (Pedido pedido: pedidosCliente){
                gananciasGeneradas += pedido.getIngresos();
            }
            itemCliente.setGananciasGeneradas(gananciasGeneradas);
            itemCliente.setCantidadPedidos(pedidosCliente.size());
        }

        logger.debug("getItemsCliente: Items clientes creados: " + itemsClientes);

        return itemsClientes;
    }

    @Override
    public ReporteCliente getReporteById(Integer id) throws ReporteNotFoundException {
        Optional<ReporteCliente> optional = reporteClienteRepository.findById(id);

        if(optional.isPresent()){
            return optional.get();
        }

        //tirar una excepción porque no se encontró el reporte con ese id (404)
        throw new ReporteNotFoundException("No se encontró el reporte de clientes con id " + id);
    }

    @Override
    public List<ReporteCliente> getAllReportes() {
        return reporteClienteRepository.findAll();
    }
}
