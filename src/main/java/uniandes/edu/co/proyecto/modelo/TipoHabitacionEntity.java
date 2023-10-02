package uniandes.edu.co.proyecto.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "Tipos_habitacion")
public class TipoHabitacionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tipoHab_sequence")
    @SequenceGenerator(name = "tipoHab_sequence", sequenceName = "tipoHab_sequence", allocationSize = 1)
    private Integer id_tipo;

    // attributes
    private Integer costo;
    private Integer capacidad;
    private String descripcion;
    private String tipo;

    // constructor
    public TipoHabitacionEntity(Integer costo, Integer capacidad,
            String descripcion, String tipo) {
        this.costo = costo;
        this.capacidad = capacidad;
        this.descripcion = descripcion;
        this.tipo = tipo;
    }

    // constructor vacío
    public TipoHabitacionEntity() {
        ;
    }

    // getters
    public Integer getId_tipo() {
        return id_tipo;
    }

    public Integer getCosto() {
        return costo;
    }

    public Integer getCapacidad() {
        return capacidad;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getTipo() {
        return tipo;
    }

    // setters
    public void setId_tipo(Integer id_tipo) {
        this.id_tipo = id_tipo;
    }

    public void setCosto(Integer costo) {
        this.costo = costo;
    }

    public void setCapacidad(Integer capacidad) {
        this.capacidad = capacidad;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

}
