package uniandes.edu.co.proyecto.modelo;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "incluyen")
public class IncluyenEntity {

    @EmbeddedId
    private IncluyenEntityPK pk;

    public IncluyenEntity(PlanDeConsumoEntity planesdeconsumo_id_plan, ProductoEntity productos_id_producto,
            ProductoEntity productos_tipo_servicio) {
        this.pk = new IncluyenEntityPK(planesdeconsumo_id_plan, productos_id_producto, productos_tipo_servicio);

    }

    public IncluyenEntity() {
        ;
    }

    public IncluyenEntityPK getPk() {
        return pk;
    }

    public void setPk(IncluyenEntityPK pk) {
        this.pk = pk;
    }

}
