package dan.tp2021.reportes.domain.items;

import javax.persistence.Entity;

@Entity
public class ItemMaterial extends Item{

    //Representa un material, pero tiene más datos para el reporte
    private Integer cantidadVendida; //Cantidad vendida de este material.
    private Double gastosTotales; //por las proviciones de este material.

    private Integer idMaterial;
    private String nombre;
    private String descripcion;
    private Double precio;

}
