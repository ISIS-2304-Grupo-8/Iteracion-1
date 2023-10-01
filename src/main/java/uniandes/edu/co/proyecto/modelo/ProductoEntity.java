package uniandes.edu.co.proyecto.modelo;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "productos")
public class ProductoEntity {

    @EmbeddedId
    private ProductoEntityPK pk;

    public ProductoEntity(TipoServicioEntity tipos_servicio_tipo_servicio) {
        this.pk = new ProductoEntityPK(tipos_servicio_tipo_servicio);

    }

    public ProductoEntity() {
        ;
    }

    public ProductoEntityPK getPk() {
        return pk;
    }

    public void setPk(ProductoEntityPK pk) {
        this.pk = pk;
    }

}
