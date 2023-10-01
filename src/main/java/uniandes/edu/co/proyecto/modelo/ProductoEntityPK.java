package uniandes.edu.co.proyecto.modelo;

import java.io.Serializable;

import jakarta.persistence.Embeddable;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Embeddable
public class ProductoEntityPK implements Serializable {

    @ManyToOne
    @JoinColumn(name = "ts_tipo_servicio", referencedColumnName = "tipo_servicio")
    private TipoServicioEntity ts_tipo_servicio;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id_producto;

    public ProductoEntityPK(TipoServicioEntity ts_tipo_servicio) {
        super();
        this.ts_tipo_servicio = ts_tipo_servicio;
    }

    public ProductoEntityPK() {
        super();
    }

    public TipoServicioEntity getTipos_servicio_tipo_servicio() {
        return ts_tipo_servicio;
    }

    public void setTipos_servicio_tipo_servicio(TipoServicioEntity ts_tipo_servicio) {
        this.ts_tipo_servicio = ts_tipo_servicio;
    }

    public Integer getId_producto() {
        return id_producto;
    }

    public void setId_producto(Integer id_producto) {
        this.id_producto = id_producto;
    }

}
