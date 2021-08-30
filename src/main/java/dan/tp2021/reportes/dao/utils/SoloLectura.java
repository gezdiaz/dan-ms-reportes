package dan.tp2021.reportes.dao.utils;

import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;

public class SoloLectura {

    //Esta clase se usa como un EntityListener para evitar que se modifiquen entidades que no se tendrían que modificar en este servicio (externas)

    @PrePersist
    void onPrePersist(Object o) {
        throw new IllegalStateException("Intento de guardar una entidad de tipo " + (o == null ? "null" : o.getClass()));
    }

    @PreUpdate
    void onPreUpdate(Object o) {
        throw new IllegalStateException("Intento de actualizar una entidad de tipo " + (o == null ? "null" : o.getClass()));
    }

    @PreRemove
    void onPreRemove(Object o) {
        throw new IllegalStateException("Intento de eliminar una entidad de tipo " + (o == null ? "null" : o.getClass()));
    }

}
