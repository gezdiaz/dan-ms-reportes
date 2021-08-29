package dan.tp2021.reportes.domain.reportes;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import dan.tp2021.reportes.domain.items.ItemCliente;
import dan.tp2021.reportes.domain.items.ItemPedido;

@Entity
public class ReportePedido extends Reporte{

    private Integer cantidadPedidos;
    private Double ingresosGenerados;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "reporte_id")
    protected List<ItemPedido> items;

    public Integer getCantidadPedidos() {
        return cantidadPedidos;
    }

    public void setCantidadPedidos(Integer cantidadPedidos) {
        this.cantidadPedidos = cantidadPedidos;
    }

    public Double getIngresosGenerados() {
        return ingresosGenerados;
    }

    public void setIngresosGenerados(Double ingresosGenerados) {
        this.ingresosGenerados = ingresosGenerados;
    }

    public List<ItemPedido> getItems() {
        return items;
    }

    public void setItems(List<ItemPedido> items) {
        this.items = items;
    }
}
