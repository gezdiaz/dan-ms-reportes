package dan.tp2021.reportes.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import dan.tp2021.reportes.dao.ReporteMaterialRepository;
import dan.tp2021.reportes.dao.external.MovimientosStockRepository;
import dan.tp2021.reportes.domain.external.material.Material;
import dan.tp2021.reportes.domain.external.material.MovimientosStock;
import dan.tp2021.reportes.domain.items.ItemMaterial;
import dan.tp2021.reportes.domain.reportes.ReporteMaterial;
import dan.tp2021.reportes.exceptions.ReporteNotFoundException;
import dan.tp2021.reportes.exceptions.SinMovimientosException;

@Service
public class ReporteMaterialServiceImpl implements ReporteMaterialService{

    private static final Logger logger = LoggerFactory.getLogger(ReporteMaterialServiceImpl.class);
    private final MovimientosStockRepository movimientosStockRepository;
    private final ReporteMaterialRepository reporteMaterialRepository;

    public ReporteMaterialServiceImpl(MovimientosStockRepository movimientosStockRepository, ReporteMaterialRepository reporteMaterialRepository) {
        this.movimientosStockRepository = movimientosStockRepository;
        this.reporteMaterialRepository = reporteMaterialRepository;
    }

    @Override
    public ReporteMaterial generarReporte(Instant fechaInicio, Instant fechaFin) throws SinMovimientosException {

        logger.debug("generarReporte: Inicia con fechaInicio " + fechaInicio + " y fechaFin: " + fechaFin);

        List<ItemMaterial> itemsItemMaterial = this.getItemsMaterial(fechaInicio, fechaFin);

        ReporteMaterial reporte = new ReporteMaterial();

        reporte.setItems(itemsItemMaterial);
        Double gastos = 0.0;
        for (ItemMaterial item: itemsItemMaterial){
            gastos += item.getGastosTotales();
        }
        reporte.setGastosTotales(gastos);
        reporte.setFechaInicio(fechaInicio);
        reporte.setFechaFin(fechaFin);

        reporte.setGenerado(Instant.now());

        logger.debug("generarReporte: Reporte generado: " + reporte);

        reporte = reporteMaterialRepository.save(reporte);

        logger.debug("generarReporte: Reporte guardado: " + reporte);

        return reporte;
    }

    private List<ItemMaterial> getItemsMaterial(Instant fechaInicio, Instant fechaFin) throws SinMovimientosException {

        logger.debug("getItemsMaterial: Inicio.");

        List<MovimientosStock> movimientosStocks = movimientosStockRepository.findMovimientosStockByFechaBetween(fechaInicio, fechaFin);

        if(movimientosStocks.isEmpty()){
            //no hay pedidos no tiene sentido generar el reporte;
            throw new SinMovimientosException("No se encontraron movimientos de stock entre las fechas " + fechaInicio + " y " + fechaFin + " al generar items para un reporte de materiales.");
        }

        logger.debug("getItemsMaterial: Todos los movimientos de stock entre las fechas: " + movimientosStocks);

        HashMap<Integer, List<MovimientosStock>> movimientosPorMaterial = new HashMap<>();

        for (MovimientosStock m: movimientosStocks){
            Integer materialId = m.getMaterial().getId();
            if(movimientosPorMaterial.containsKey(materialId)){
                //EL material ya está en el mapa, agrego el movimiento a su lista
                movimientosPorMaterial.get(materialId).add(m);
            } else {
                //El material no está en el mapa, lo agrego con una nueva lista
                List<MovimientosStock> movimientos = new ArrayList<>();
                movimientos.add(m);
                movimientosPorMaterial.put(materialId, movimientos);
            }
        }

        logger.debug("getItemsMaterial: Mapa de materiales con movimientos: " + movimientosPorMaterial);

        List<ItemMaterial> itemsMaterial = new ArrayList<>();

        for (Integer id: movimientosPorMaterial.keySet()){
            //Saco el material del primer movimiento, todos tienen el mismo
            Material material = movimientosPorMaterial.get(id).get(0).getMaterial();
            ItemMaterial item = new ItemMaterial();

            item.setIdMaterial(material.getId());
            item.setPrecio(material.getPrecio());
            item.setDescripcion(material.getDescripcion());
            item.setNombre(material.getNombre());

            double gastos = 0.0;
            Integer cantidadVendida = 0;
            Double precio = material.getPrecio();
            for (MovimientosStock movimiento: movimientosPorMaterial.get(id)){
                if (movimiento.getCantidadEntrada() != null){
                    gastos += movimiento.getCantidadEntrada() * precio;
                }
                if (movimiento.getCantidadSalida() != null){
                    cantidadVendida = movimiento.getCantidadSalida();
                }
            }

            item.setCantidadVendida(cantidadVendida);
            item.setGastosTotales(gastos);

            itemsMaterial.add(item);
        }

        logger.debug("getItemsMaterial: Items generados: " + itemsMaterial);


        return itemsMaterial;
    }

    @Override
    public ReporteMaterial getReporteById(Integer id) throws ReporteNotFoundException {
        Optional<ReporteMaterial> optional = reporteMaterialRepository.findById(id);

        if(optional.isPresent()){
            return optional.get();
        }

        //tirar una excepción porque no se encontró el reporte con ese id (404)
        throw new ReporteNotFoundException("No se encontró el reporte de materiales con id " + id);
    }

    @Override
    public List<ReporteMaterial> getAllReportes() {
        return reporteMaterialRepository.findAll();
    }
}
