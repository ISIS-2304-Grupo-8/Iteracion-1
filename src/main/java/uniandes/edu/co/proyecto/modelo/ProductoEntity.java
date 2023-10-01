package uniandes.edu.co.proyecto.modelo;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "productos")
public class ProductoEntity {

    @EmbeddedId
    private ProductoEntityPK pk;

    private String nombre;
    private Double costo;

    public ProductoEntity(TipoServicioEntity tipos_servicio_tipo_servicio, String nombre, Double costo) {
        this.pk = new ProductoEntityPK(tipos_servicio_tipo_servicio);
        this.nombre = nombre;
        this.costo = costo;

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

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Double getCosto() {
        return costo;
    }

    public void setCosto(Double costo) {
        this.costo = costo;
    }

}
