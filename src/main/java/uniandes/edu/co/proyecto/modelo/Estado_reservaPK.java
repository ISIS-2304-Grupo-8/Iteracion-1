package uniandes.edu.co.proyecto.modelo;

import java.io.Serializable;
import java.sql.Date;

import jakarta.persistence.Embeddable;

@Embeddable
public class Estado_reservaPK implements Serializable {

    private Date fecha_inicial;

    private Date fecha_fin;

    public Estado_reservaPK() {
        super();
    }

    public Estado_reservaPK(Date fecha_inicial, Date fecha_fin) {
        super();
        this.fecha_inicial = fecha_inicial;
        this.fecha_fin = fecha_fin;
    }

    public Date getFecha_inicial() {
        return fecha_inicial;
    }

    public void setFecha_inicial(Date fecha_inicial) {
        this.fecha_inicial = fecha_inicial;
    }

    public Date getFecha_final() {
        return fecha_fin;
    }

    public void setFecha_final(Date fecha_fin) {
        this.fecha_fin = fecha_fin;
    }

}
