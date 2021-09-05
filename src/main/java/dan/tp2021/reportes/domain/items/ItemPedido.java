package dan.tp2021.reportes.domain.items;

import java.time.Instant;

import javax.persistence.Entity;

@Entity
public class ItemPedido extends Item{

    private Double ingresosGenerados;
    private String estado;

    private Integer idPedido;
    private Instant fechaPedido;
    private Integer cantidadMateriales; //de los detalles
    private String direccionObra;
    private String tipoObra;
    private String razonSocialCliente;
    private String cuitCliente;

    public Double getIngresosGenerados() {
        return ingresosGenerados;
    }

    public void setIngresosGenerados(Double ingresosGenerados) {
        this.ingresosGenerados = ingresosGenerados;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Integer getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(Integer idPedido) {
        this.idPedido = idPedido;
    }

    public Instant getFechaPedido() {
        return fechaPedido;
    }

    public void setFechaPedido(Instant fechaPedido) {
        this.fechaPedido = fechaPedido;
    }

    public Integer getCantidadMateriales() {
        return cantidadMateriales;
    }

    public void setCantidadMateriales(Integer cantidadMateriales) {
        this.cantidadMateriales = cantidadMateriales;
    }

    public String getDireccionObra() {
        return direccionObra;
    }

    public void setDireccionObra(String direccionObra) {
        this.direccionObra = direccionObra;
    }

    public String getTipoObra() {
        return tipoObra;
    }

    public void setTipoObra(String tipoObra) {
        this.tipoObra = tipoObra;
    }

    public String getRazonSocialCliente() {
        return razonSocialCliente;
    }

    public void setRazonSocialCliente(String razonSocialCliente) {
        this.razonSocialCliente = razonSocialCliente;
    }

    public String getCuitCliente() {
        return cuitCliente;
    }

    public void setCuitCliente(String cuitCliente) {
        this.cuitCliente = cuitCliente;
    }

    @Override
    public String toString() {
        return "ItemPedido{" +
                "id=" + id +
                ", ingresosGenerados=" + ingresosGenerados +
                ", estado='" + estado + '\'' +
                ", idPedido=" + idPedido +
                ", fechaPedido=" + fechaPedido +
                ", cantidadMateriales=" + cantidadMateriales +
                ", direccionObra='" + direccionObra + '\'' +
                ", tipoObra='" + tipoObra + '\'' +
                ", razonSocialCliente='" + razonSocialCliente + '\'' +
                ", cuitCliente='" + cuitCliente + '\'' +
                '}';
    }
}
