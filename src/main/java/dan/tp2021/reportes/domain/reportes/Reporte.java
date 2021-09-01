package dan.tp2021.reportes.domain.reportes;

import org.hibernate.annotations.Type;

import java.time.Instant;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Reporte {

    @Id @GeneratedValue(strategy = GenerationType.TABLE)
    protected Integer id;
    protected Instant fechaInicio;
    protected Instant fechaFin;
    protected Instant generado;


    public Reporte() {
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Instant getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Instant fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Instant getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Instant fechaFin) {
        this.fechaFin = fechaFin;
    }

    public Instant getGenerado() {
        return generado;
    }

    public void setGenerado(Instant generado) {
        this.generado = generado;
    }

}
