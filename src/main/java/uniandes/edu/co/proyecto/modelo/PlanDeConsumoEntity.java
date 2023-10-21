package uniandes.edu.co.proyecto.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "planesdeconsumo")
public class PlanDeConsumoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "planes_sequence")
    @SequenceGenerator(name = "planes_sequence", sequenceName = "planes_sequence", allocationSize = 1)

    private Integer id_plan;

    @ManyToOne
    @JoinColumn(name = "tipos_plan_tipo_plan", referencedColumnName = "tipo_plan")
    private TipoPlanEntity tipos_plan_tipo_plan;

    private String nombre;

    private String descripcion;

    public PlanDeConsumoEntity(TipoPlanEntity tipos_plan_tipo_plan, String nombre, String descripcion) {
        this.tipos_plan_tipo_plan = tipos_plan_tipo_plan;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public PlanDeConsumoEntity() {
        ;
    }

    public Integer getId_plan() {
        return id_plan;
    }

    public void setId_plan(Integer id_plan) {
        this.id_plan = id_plan;
    }

    public TipoPlanEntity getTipos_plan_tipo_plan() {
        return tipos_plan_tipo_plan;
    }

    public void setTipos_plan_tipo_plan(TipoPlanEntity tipos_plan_tipo_plan) {
        this.tipos_plan_tipo_plan = tipos_plan_tipo_plan;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

}
