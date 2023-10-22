package uniandes.edu.co.proyecto.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tiposusuario")
public class TipoUsuarioEntity {

    @Id
    private Integer id_usuario;

    private String rol;

    public TipoUsuarioEntity() {
        ;
    }

    public TipoUsuarioEntity(String rol) {
        this.rol = rol;
    }

    public Integer getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(Integer id) {
        this.id_usuario = id;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;

    }

    @Override
    public String toString() {
        return "{id_usuario=" + id_usuario + ", rol=" + rol + "}";
    }

}
