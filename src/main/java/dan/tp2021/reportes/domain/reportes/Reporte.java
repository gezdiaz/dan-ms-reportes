package dan.tp2021.reportes.domain.reportes;

import java.time.Instant;
import java.time.LocalDate;
import java.util.List;

import dan.tp2021.reportes.domain.items.Item;
import dan.tp2021.reportes.domain.items.ItemCliente;

public abstract class Reporte<I extends Item> {

    protected Integer id;
    protected LocalDate fechaInicio;
    protected LocalDate fechaFin;
    protected Instant generado;
    protected List<I> items;

    public Reporte() {
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<I> getItems(){
        return items;
    }

    public void setItems(List<I> items) {
        this.items = items;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
    }

    public Instant getGenerado() {
        return generado;
    }

    public void setGenerado(Instant generado) {
        this.generado = generado;
    }

}
