package uniandes.edu.co.proyecto.modelo;

import java.io.Serializable;

import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Embeddable
public class ReservanPK implements Serializable{
    @ManyToOne
    @JoinColumn(name = "hab_id", referencedColumnName = "id_habitacion")
    private HabitacionEntity hab_id;

    @ManyToOne
    @JoinColumn(name = "serv_id", referencedColumnName = "id_servicio")
    private ServicioEntity serv_id;

    public ReservanPK(HabitacionEntity hab_id, ServicioEntity serv_id) {
        super();
        this.hab_id = hab_id;
        this.serv_id = serv_id;
    }

    //getters & setters
    public HabitacionEntity getHab_id() {
        return hab_id;
    }

    public void setHab_id(HabitacionEntity hab_id) {
        this.hab_id = hab_id;
    }

    public ServicioEntity getServ_id() {
        return serv_id;
    }

    public void setServ_id(ServicioEntity serv_id) {
        this.serv_id = serv_id;
    }
    
}
