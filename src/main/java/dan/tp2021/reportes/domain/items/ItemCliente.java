package dan.tp2021.reportes.domain.items;

import javax.persistence.Entity;

@Entity
public class ItemCliente extends Item{

    //Datos calculados para el reporte.
    private Integer cantidadPedidos;
    private Integer cantidadObras;
    private Double gananciasGeneradas;

    //datos del cliente
    private Integer idCliente; //El id del cliente, no del item cliente
    private String razonSocial;
    private String cuit;
    private String mail;

    public Integer getCantidadPedidos() {
        return cantidadPedidos;
    }

    public void setCantidadPedidos(Integer cantidadPedidos) {
        this.cantidadPedidos = cantidadPedidos;
    }

    public Integer getCantidadObras() {
        return cantidadObras;
    }

    public void setCantidadObras(Integer cantidadObras) {
        this.cantidadObras = cantidadObras;
    }

    public Double getGananciasGeneradas() {
        return gananciasGeneradas;
    }

    public void setGananciasGeneradas(Double gananciasGeneradas) {
        this.gananciasGeneradas = gananciasGeneradas;
    }

    public Integer getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public String getCuit() {
        return cuit;
    }

    public void setCuit(String cuit) {
        this.cuit = cuit;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    @Override
    public String toString() {
        return "ItemCliente{" +
                "id=" + id +
                ", cantidadPedidos=" + cantidadPedidos +
                ", cantidadObras=" + cantidadObras +
                ", gananciasGeneradas=" + gananciasGeneradas +
                ", idCliente=" + idCliente +
                ", razonSocial='" + razonSocial + '\'' +
                ", cuit='" + cuit + '\'' +
                ", mail='" + mail + '\'' +
                '}';
    }
}
