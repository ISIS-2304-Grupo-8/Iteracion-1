package uniandes.edu.co.proyecto.modelo;

import java.sql.Date;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "estados_reservas")
public class Estado_reserva {
    

    @EmbeddedId
    private Estado_reservaPK pk;

    private char estado;

    public Estado_reserva(Date fecha_inicial, Date fecha_final, char estado) {
        this.pk = new Estado_reservaPK(fecha_inicial,fecha_final);
        this.estado = estado;
    }

    public Estado_reservaPK getPk() {
        return pk;
    }

    public void setPk(Estado_reservaPK pk) {
        this.pk = pk;
    }

    public char getEstado() {
        return estado;
    }

    public void setEstado(char estado) {
        this.estado = estado;
    } 

    
}
