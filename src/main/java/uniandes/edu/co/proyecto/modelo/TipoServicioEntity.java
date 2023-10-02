package uniandes.edu.co.proyecto.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="Tipos_servicio")
public class TipoServicioEntity {
    @Id
    private String tipo_servicio;

    //attributes
    private Integer precio;
    private Integer capacidad;

    //constructor
    public TipoServicioEntity(String tipo_servicio,
    Integer precio, Integer capacidad){
        this.tipo_servicio = tipo_servicio;
        this.precio = precio;
        this.capacidad = capacidad;
    }

    //empty constructor
    public TipoServicioEntity(){;}

    //getters and setters
    public String getTipo_servicio() {
        return tipo_servicio;
    }

    public void setTipo_servicio(String tipo_servicio) {
        this.tipo_servicio = tipo_servicio;
    }

    public Integer getPrecio() {
        return precio;
    }

    public void setPrecio(Integer precio) {
        this.precio = precio;
    }

    public Integer getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(Integer capacidad) {
        this.capacidad = capacidad;
    }

    
}
