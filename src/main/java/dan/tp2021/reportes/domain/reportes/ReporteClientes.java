package dan.tp2021.reportes.domain.reportes;

import java.time.Instant;
import java.time.LocalDate;
import java.util.List;

import dan.tp2021.reportes.domain.items.Item;
import dan.tp2021.reportes.domain.items.ItemCliente;

public class ReporteClientes extends Reporte<ItemCliente>{


    private Integer cantidadClientes;
    private Double ingresosTotales;

    public ReporteClientes() {
    }

    public ReporteClientes(Integer id, LocalDate fechaInicio, LocalDate fechaFin, Instant generado, List<ItemCliente> items) {
        this.id = id;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.generado = generado;
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
}
