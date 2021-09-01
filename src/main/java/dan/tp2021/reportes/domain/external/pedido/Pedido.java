package dan.tp2021.reportes.domain.external.pedido;

import java.time.Instant;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import dan.tp2021.reportes.dao.utils.SoloLectura;
import dan.tp2021.reportes.domain.external.cliente.Obra;

@Entity
@EntityListeners(SoloLectura.class)
@Table(catalog = "`dan-ms-pedido`")
public class Pedido {

    @Id
    private Integer id;
    private Instant fechaPedido;

    @ManyToOne
    private Obra obra; //TODO verlo. Creo que debemos ir al servicio de USUARIOS y buscar si existe una obra con este ID, en ese caso, la guardamos en esta BD antes de guardar el Pedido, solo con el proposito de tener la relacion. Otra opcion seria que cada cambio que ocurra en la BD de datos del servicio USUARIOS se refleje en esta BD.

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_pedido")
    private List<DetallePedido> detalle;

    @ManyToOne
//	@JoinColumn(name = "ID_ESTADO_PEDIDO")
    private EstadoPedido estado; //Lo tenemos precargados en la BD.

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Instant getFechaPedido() {
        return fechaPedido;
    }

    public void setFechaPedido(Instant fechaPedido) {
        this.fechaPedido = fechaPedido;
    }

    public Obra getObra() {
        return obra;
    }

    public void setObra(Obra obra) {
        this.obra = obra;
    }

    public List<DetallePedido> getDetalle() {
        return detalle;
    }

    public void setDetalle(List<DetallePedido> detalle) {
        this.detalle = detalle;
    }

    public EstadoPedido getEstado() {
        return estado;
    }

    public void setEstado(EstadoPedido estado) {
        this.estado = estado;
    }

    public Double getIngresos() {
        Double ingresos = 0.0;
        for (DetallePedido dp: this.detalle){
            ingresos += dp.getPrecio();
        }
        return ingresos;
    }

    @Override
    public String toString() {
        return "Pedido{" +
                "id=" + id +
                ", fechaPedido=" + fechaPedido +
                ", obra=" + obra +
                ", detalle=" + detalle +
                ", estado=" + estado +
                '}';
    }
}
