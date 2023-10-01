package uniandes.edu.co.proyecto.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="empleados")
public class EmpleadoEntity {

    @Id
    private Integer num_doc;
    private String tipo_doc;
    private String rol;
    private String nombre;
    private String email;

    public EmpleadoEntity(){;}

    public EmpleadoEntity(Integer num_doc, String tipo_doc, String rol, String nombre, String email) {
        this.num_doc = num_doc;
        this.tipo_doc = tipo_doc;
        this.rol = rol;
        this.nombre = nombre;
        this.email = email;
    }

    public Integer getNum_doc() {
        return num_doc;
    }

    public void setNum_doc(Integer num_doc) {
        this.num_doc = num_doc;
    }

    public String getTipo_doc() {
        return tipo_doc;
    }

    public void setTipo_doc(String tipo_doc) {
        this.tipo_doc = tipo_doc;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    
}
