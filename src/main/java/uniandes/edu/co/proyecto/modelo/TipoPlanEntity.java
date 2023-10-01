package uniandes.edu.co.proyecto.modelo;

import jakarta.annotation.Generated;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tipos_plan")
public class TipoPlanEntity {

    @Id
    // TODO: Revisar si es necesario
    private String tipo_plan;

    private Integer vigencia;

    public TipoPlanEntity(String tipo_plan, Integer vigencia) {
        this.tipo_plan = tipo_plan;
        this.vigencia = vigencia;
    }

    public TipoPlanEntity() {
        ;
    }

    public String getTipo_plan() {
        return tipo_plan;
    }

    public void setTipo_plan(String tipo_plan) {
        this.tipo_plan = tipo_plan;
    }

    public Integer getVigencia() {
        return vigencia;
    }

    public void setVigencia(Integer vigencia) {
        this.vigencia = vigencia;
    }

    @Override
    public String toString() {
        return "TipoPlanEntity [tipo_plan=" + tipo_plan + ", vigencia=" + vigencia + "]";
    }

}
