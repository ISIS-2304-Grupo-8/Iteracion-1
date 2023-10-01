package uniandes.edu.co.proyecto.modelo;

import java.io.Serializable;

import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinColumns;
import jakarta.persistence.ManyToOne;

@Embeddable
public class IncluyenEntityPK implements Serializable {

    @ManyToOne
    @JoinColumn(name = "planesdeconsumo_id_plan", referencedColumnName = "id_plan")
    private PlanDeConsumoEntity planesdeconsumo_id_plan;

    @ManyToOne
    @JoinColumns({ @JoinColumn(name = "productos_id_producto", referencedColumnName = "id_producto"),
            @JoinColumn(name = "productos_tipo_servicio", referencedColumnName = "ts_tipo_servicio") })
    private ProductoEntity producto;

    public IncluyenEntityPK(PlanDeConsumoEntity planesdeconsumo_id_plan, ProductoEntity producto) {
        super();
        this.planesdeconsumo_id_plan = planesdeconsumo_id_plan;
        this.producto = producto;
    }

    public IncluyenEntityPK() {
        super();
    }

    public PlanDeConsumoEntity getPlanesdeconsumo_id_plan() {
        return planesdeconsumo_id_plan;
    }

    public void setPlanesdeconsumo_id_plan(PlanDeConsumoEntity planesdeconsumo_id_plan) {
        this.planesdeconsumo_id_plan = planesdeconsumo_id_plan;
    }

    public ProductoEntity getProducto() {
        return producto;
    }

    public void setProducto(ProductoEntity producto) {
        this.producto = producto;
    }

}
