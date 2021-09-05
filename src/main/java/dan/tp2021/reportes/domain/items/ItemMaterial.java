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

    public Integer getCantidadVendida() {
        return cantidadVendida;
    }

    public void setCantidadVendida(Integer cantidadVendida) {
        this.cantidadVendida = cantidadVendida;
    }

    public Double getGastosTotales() {
        return gastosTotales;
    }

    public void setGastosTotales(Double gastosTotales) {
        this.gastosTotales = gastosTotales;
    }

    public Integer getIdMaterial() {
        return idMaterial;
    }

    public void setIdMaterial(Integer idMaterial) {
        this.idMaterial = idMaterial;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    @Override
    public String toString() {
        return "ItemMaterial{" +
                "id=" + id +
                ", cantidadVendida=" + cantidadVendida +
                ", gastosTotales=" + gastosTotales +
                ", idMaterial=" + idMaterial +
                ", nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", precio=" + precio +
                '}';
    }
}
