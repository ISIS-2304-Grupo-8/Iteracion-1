package uniandes.edu.co.proyecto.modelo;

import java.io.Serializable;

import jakarta.annotation.Generated;
import jakarta.persistence.Embeddable;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Embeddable
public class DescuentoEntityPK implements Serializable {

    @ManyToOne
    @JoinColumn(name = "tipos_plan_tipo_plan", referencedColumnName = "tipo_plan")
    private TipoPlanEntity tipos_plan_tipo_plan;

    private String servicio_descuentado;

    public DescuentoEntityPK(TipoPlanEntity tipos_plan_tipo_plan, String servicio_descuentado) {
        super();
        this.tipos_plan_tipo_plan = tipos_plan_tipo_plan;
        this.servicio_descuentado = servicio_descuentado;
    }

    public DescuentoEntityPK() {
        super();
    }

    public TipoPlanEntity getTipos_plan_tipo_plan() {
        return tipos_plan_tipo_plan;
    }

    public void setTipos_plan_tipo_plan(TipoPlanEntity tipos_plan_tipo_plan) {
        this.tipos_plan_tipo_plan = tipos_plan_tipo_plan;
    }

    public String getServicio_descuentado() {
        return servicio_descuentado;
    }

    public void setServicio_descuentado(String servicio_descuentado) {
        this.servicio_descuentado = servicio_descuentado;
    }

}
