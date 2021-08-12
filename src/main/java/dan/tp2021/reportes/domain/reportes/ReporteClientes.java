package dan.tp2021.reportes.domain.reportes;

import java.util.List;

import dan.tp2021.reportes.domain.items.Item;
import dan.tp2021.reportes.domain.items.ItemCliente;

public class ReporteClientes extends Reporte{

    private List<ItemCliente> items;
    private Integer cantidadClientes;
    private Double ingresosTotales;

    @Override
    public List<ItemCliente> getItems() {
        return null;
    }

    public void setItems(List<ItemCliente> items) {

    }
}
