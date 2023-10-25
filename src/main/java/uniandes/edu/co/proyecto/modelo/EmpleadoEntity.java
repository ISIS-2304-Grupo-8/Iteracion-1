package uniandes.edu.co.proyecto.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="empleados")
public class EmpleadoEntity {

    @Id
    private Integer num_doc;
    private String tipo_doc;
    private String nombre;
    private String email;

    @ManyToOne
    @JoinColumn(name = "rol", referencedColumnName = "id_usuario")
    private TipoUsuarioEntity rol;

    public EmpleadoEntity(){;}

    public EmpleadoEntity(Integer num_doc, String tipo_doc, TipoUsuarioEntity rol, String nombre, String email) {
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

    public TipoUsuarioEntity getRol() {
        return rol;
    }

    public void setRol(TipoUsuarioEntity rol) {
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
