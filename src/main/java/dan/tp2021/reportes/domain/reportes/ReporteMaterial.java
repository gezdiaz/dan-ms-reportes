package dan.tp2021.reportes.domain.reportes;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import dan.tp2021.reportes.domain.items.ItemMaterial;

@Entity
public class ReporteMaterial extends Reporte {

    Double gastosTotales; //por las proviciones.

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "reporte_id")
    private List<ItemMaterial> items;

    public Double getGastosTotales() {
        return gastosTotales;
    }

    public void setGastosTotales(Double gastosTotales) {
        this.gastosTotales = gastosTotales;
    }

    public List<ItemMaterial> getItems() {
        return items;
    }

    public void setItems(List<ItemMaterial> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "ReporteMaterial{" +
                "id=" + id +
                ", fechaInicio=" + fechaInicio +
                ", fechaFin=" + fechaFin +
                ", generado=" + generado +
                ", gastosTotales=" + gastosTotales +
                ", items=" + items +
                '}';
    }
}
