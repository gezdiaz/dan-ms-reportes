package dan.tp2021.reportes.domain.external.material;

import java.time.Instant;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import dan.tp2021.reportes.dao.utils.SoloLectura;

@Entity
@EntityListeners(SoloLectura.class)
@Table(catalog = "`dan-ms-productos`")
public class Provision {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Instant fechaProvision;
    @OneToMany(cascade = CascadeType.ALL)
    private List<DetalleProvision> detalle;
}
