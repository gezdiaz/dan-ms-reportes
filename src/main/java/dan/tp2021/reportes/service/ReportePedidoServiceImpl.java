package dan.tp2021.reportes.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import dan.tp2021.reportes.dao.ReportePedidoRepository;
import dan.tp2021.reportes.dao.external.PedidoRepository;
import dan.tp2021.reportes.domain.external.cliente.Cliente;
import dan.tp2021.reportes.domain.external.cliente.Obra;
import dan.tp2021.reportes.domain.external.pedido.DetallePedido;
import dan.tp2021.reportes.domain.external.pedido.Pedido;
import dan.tp2021.reportes.domain.items.ItemPedido;
import dan.tp2021.reportes.domain.reportes.ReporteCliente;
import dan.tp2021.reportes.domain.reportes.ReportePedido;
import dan.tp2021.reportes.exceptions.ReporteNotFoundException;
import dan.tp2021.reportes.exceptions.SinPedidosException;

@Service
public class ReportePedidoServiceImpl implements ReportePedidoService{

    private static final Logger logger = LoggerFactory.getLogger(ReportePedidoServiceImpl.class);

    private final PedidoRepository pedidoRepository;
    private final ReportePedidoRepository reportePedidoRepository;

    public ReportePedidoServiceImpl(PedidoRepository pedidoRepository, ReportePedidoRepository reportePedidoRepository) {
        this.pedidoRepository = pedidoRepository;
        this.reportePedidoRepository = reportePedidoRepository;
    }

    @Override
    public ReportePedido generarReporte(Instant fechaInicio, Instant fechaFin) throws SinPedidosException {

        logger.debug("generarReporte: Inicia con fechaInicio " + fechaInicio + " y fechaFin: " + fechaFin);

        List<ItemPedido> itemsPedido = this.getItemsPedido(fechaInicio, fechaFin);

        ReportePedido reporte = new ReportePedido();

        reporte.setCantidadPedidos(itemsPedido.size());
        reporte.setItems(itemsPedido);
        Double ingresos = 0.0;
        for (ItemPedido item: itemsPedido){
            ingresos += item.getIngresosGenerados();
        }
        reporte.setIngresosGenerados(ingresos);

        reporte.setGenerado(Instant.now());

        logger.debug("generarReporte: Reporte generado: " + reporte);

        reporte = reportePedidoRepository.save(reporte);

        logger.debug("generarReporte: Reporte después de guardar: " + reporte);

        return reporte;
    }

    private List<ItemPedido> getItemsPedido(Instant fechaInicio, Instant fechaFin) throws SinPedidosException {

        logger.debug("getItemsPedido: Inicio.");

        List<Pedido> pedidos = pedidoRepository.findByFechaPedidoBetween(fechaInicio, fechaFin);

        if(pedidos.isEmpty()){
            //no hay pedidos no tiene sentido generar el reporte;
            throw new SinPedidosException("No se encontraron pedidos entre las fechas " + fechaInicio + " y " + fechaFin + " al generar items para un reporte de pedidos.");
        }

        logger.debug("getItemsPedido: Todos los pedidos entre las fechas: " + pedidos);

        List<ItemPedido> itemsPedido = new ArrayList<>();

        for (Pedido p: pedidos) {
            Obra obra = p.getObra();
            Cliente cliente = obra.getCliente();
            ItemPedido item = new ItemPedido();

            item.setFechaPedido(p.getFechaPedido());
            item.setEstado(p.getEstado().getEstado());
            item.setIdPedido(p.getId());

            item.setDireccionObra(obra.getDireccion());
            item.setTipoObra(obra.getTipo().getDescripcion());

            item.setCuitCliente(cliente.getCuit());
            item.setRazonSocialCliente(cliente.getRazonSocial());

            Double ingresos = 0.0;

            for (DetallePedido dp: p.getDetalle()){
                ingresos += dp.getPrecio();
            }
            item.setIngresosGenerados(ingresos);
            item.setCantidadMateriales(p.getDetalle().size());

            itemsPedido.add(item);
        }

        logger.debug("getItemsPedido: items generados: " + itemsPedido);

        return itemsPedido;
    }

    @Override
    public ReportePedido getReporteById(Integer id) throws ReporteNotFoundException {
        Optional<ReportePedido> optional = reportePedidoRepository.findById(id);

        if(optional.isPresent()){
            return optional.get();
        }

        //tirar una excepción porque no se encontró el reporte con ese id (404)
        throw new ReporteNotFoundException("No se encontró el reporte de pedidos con id " + id);
    }

    @Override
    public List<ReportePedido> getAllReportes() {
        return reportePedidoRepository.findAll();
    }
}
