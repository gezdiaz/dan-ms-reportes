package dan.tp2021.reportes.domain.external.material;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import dan.tp2021.reportes.dao.utils.SoloLectura;

@Entity
@EntityListeners(SoloLectura.class)
@Table(catalog = "`dan-ms-productos`")
public class DetalleProvision {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne(optional = false)
    private Material material;
    private Integer cantidad;
    @ManyToOne
    private Provision provision;
}
