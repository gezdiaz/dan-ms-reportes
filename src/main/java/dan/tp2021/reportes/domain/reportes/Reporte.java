package dan.tp2021.reportes.domain.reportes;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.Type;
import org.springframework.context.annotation.Primary;

import java.time.Instant;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import dan.tp2021.reportes.domain.items.Item;
import dan.tp2021.reportes.domain.items.ItemCliente;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Reporte {

    @Id @GeneratedValue(strategy = GenerationType.TABLE)
    protected Integer id;
    protected LocalDate fechaInicio;
    protected LocalDate fechaFin;
    protected Instant generado;


    public Reporte() {
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
