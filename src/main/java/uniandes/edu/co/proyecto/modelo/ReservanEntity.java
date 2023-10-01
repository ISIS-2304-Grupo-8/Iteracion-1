package uniandes.edu.co.proyecto.modelo;

import java.sql.Date;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "Reservan")
public class ReservanEntity {
    //primary, foreign keys
    @EmbeddedId
    private ReservanPK pk;

    //attributes
    private Date fecha_inicio;
    private Date fecha_fin;

    //default constructor
    public ReservanEntity(HabitacionEntity hab_id, ServicioEntity serv_id,
    Date fecha_inicio, Date fecha_fin){
        this.pk = new ReservanPK(hab_id, serv_id);
        this.fecha_inicio = fecha_inicio;
        this.fecha_fin = fecha_fin;
    }

    //empty constructor
    public ReservanEntity(){;}

    //getters & setters
    public ReservanPK getPk() {
        return pk;
    }

    public void setPk(ReservanPK pk) {
        this.pk = pk;
    }

    public Date getFecha_inicio() {
        return fecha_inicio;
    }

    public void setFecha_inicio(Date fecha_inicio) {
        this.fecha_inicio = fecha_inicio;
    }

    public Date getFecha_fin() {
        return fecha_fin;
    }

    public void setFecha_fin(Date fecha_fin) {
        this.fecha_fin = fecha_fin;
    }

    
}
