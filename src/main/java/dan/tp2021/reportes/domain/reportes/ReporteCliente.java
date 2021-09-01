package dan.tp2021.reportes.domain.reportes;

import java.time.Instant;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import dan.tp2021.reportes.domain.items.ItemCliente;

@Entity
public class ReporteCliente extends Reporte{


    private Integer cantidadClientes;
    private Double ingresosTotales;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "reporte_id")
    protected List<ItemCliente> items;

    public ReporteCliente() {
    }

    public ReporteCliente(Integer id, Instant fechaInicio, Instant fechaFin, Instant generado, List<ItemCliente> items) {
        this.id = id;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.generado = generado;
        this.items = items;
    }

    public List<ItemCliente> getItems(){
        return items;
    }

    public void setItems(List<ItemCliente> items) {
        this.items = items;
    }

    public Integer getCantidadClientes() {
        return cantidadClientes;
    }

    public void setCantidadClientes(Integer cantidadClientes) {
        this.cantidadClientes = cantidadClientes;
    }

    public Double getIngresosTotales() {
        return ingresosTotales;
    }

    public void setIngresosTotales(Double ingresosTotales) {
        this.ingresosTotales = ingresosTotales;
    }

    @Override
    public String toString() {
        return "ReporteCliente{" +
                "id=" + id +
                ", fechaInicio=" + fechaInicio +
                ", fechaFin=" + fechaFin +
                ", generado=" + generado +
                ", items=" + items +
                ", cantidadClientes=" + cantidadClientes +
                ", ingresosTotales=" + ingresosTotales +
                '}';
    }
}
