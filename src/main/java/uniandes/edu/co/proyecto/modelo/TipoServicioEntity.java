package uniandes.edu.co.proyecto.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="Tipos_servicio")
public class TipoServicioEntity {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id_tipo_ser;

    //attributes
    private String tipo_servicio;
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
    public Integer getId_tipo_ser() {
        return id_tipo_ser;
    }

    public void setId_tipo_ser(Integer id_tipo_ser) {
        this.id_tipo_ser = id_tipo_ser;
    }

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
