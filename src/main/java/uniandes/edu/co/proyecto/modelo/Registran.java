package uniandes.edu.co.proyecto.modelo;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name="registran")
public class Registran {

    @EmbeddedId
    private RegistranPK pk;

    private Integer check_in;

    public Registran(){;}

    public Registran(ClienteEntity id_cliente, EmpleadoEntity id_empleado, Integer check_in) {
        this.pk = new RegistranPK(id_cliente, id_empleado);
        this.check_in = check_in;
    }

    public RegistranPK getPk() {
        return pk;
    }

    public void setPk(RegistranPK pk) {
        this.pk = pk;
    }

    public Integer getCheck_in() {
        return check_in;
    }

    public void setCheck_in(Integer check_in) {
        this.check_in = check_in;
    }


    
    
    
}
