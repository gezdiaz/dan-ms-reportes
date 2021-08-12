package dan.tp2021.reportes.domain.reportes;

import java.time.Instant;
import java.time.LocalDate;
import java.util.List;

public abstract class Reporte {

    private Integer id;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private Instant generado;

    public Reporte() {
    }

    public Reporte(Integer id, LocalDate fechaInicio, LocalDate fechaFin, Instant generado) {
        this.id = id;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.generado = generado;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public abstract List getItems();

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
