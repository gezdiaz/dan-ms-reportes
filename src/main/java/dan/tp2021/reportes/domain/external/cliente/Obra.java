package dan.tp2021.reportes.domain.external.cliente;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import dan.tp2021.reportes.dao.utils.SoloLectura;

@Entity
@Table(catalog = "`dan-ms-usuarios`")
@EntityListeners(SoloLectura.class)
public class Obra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String descripcion;
    private String direccion;
    private Integer superficie;
    @ManyToOne(optional = false, cascade = {CascadeType.PERSIST, CascadeType.MERGE}) //No hay obras sin tipo TODO ver si hacemos que se cree el tipo junto con la obra o aparte, o ambos.
    private TipoObra tipo;
    @ManyToOne(optional = false, cascade = CascadeType.PERSIST) //El cliente se tiene que crear entes que las obras, no puede haber obras sin cliente
    private Cliente cliente;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Integer getSuperficie() {
        return superficie;
    }

    public void setSuperficie(Integer superficie) {
        this.superficie = superficie;
    }

    public TipoObra getTipo() {
        return tipo;
    }

    public void setTipo(TipoObra tipo) {
        this.tipo = tipo;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
}
