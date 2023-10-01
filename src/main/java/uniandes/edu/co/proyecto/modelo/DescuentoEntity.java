package uniandes.edu.co.proyecto.modelo;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "descuentos")
public class DescuentoEntity {

    @EmbeddedId
    private DescuentoEntityPK pk;

    private Double descuento;

    public DescuentoEntity(TipoPlanEntity tipos_plan_tipo_plan, String servicio_descuentado, Double descuento) {

        this.pk = new DescuentoEntityPK(tipos_plan_tipo_plan, servicio_descuentado);
        this.descuento = descuento;
    }

    public DescuentoEntity() {
        ;
    }

    public DescuentoEntityPK getPk() {
        return pk;
    }

    public void setPk(DescuentoEntityPK pk) {
        this.pk = pk;
    }

    public Double getDescuento() {
        return descuento;
    }

    public void setDescuento(Double descuento) {
        this.descuento = descuento;
    }

}
