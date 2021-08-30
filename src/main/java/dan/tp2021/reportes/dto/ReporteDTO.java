package dan.tp2021.reportes.dto;

import org.hibernate.validator.internal.util.privilegedactions.LoadClass;

import java.time.Instant;
import java.time.LocalDate;

import dan.tp2021.reportes.domain.reportes.TipoReporte;

public class ReporteDTO {
    //Clase pare recibir los datos para generar un reporte en el ReporteRest
    public TipoReporte tipo;
    public LocalDate fechaInicio;
    public LocalDate fechaFin;

    public ReporteDTO() {
    }

    public ReporteDTO(TipoReporte tipo, LocalDate fechaInicio, LocalDate fechaFin) {
        this.tipo = tipo;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
    }
}
