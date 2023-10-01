package uniandes.edu.co.proyecto.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinColumns;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "reservas")
public class ReservaEntity {

    @Id
    private Integer id_reserva;
    private Integer num_personas;

    @ManyToOne
    @JoinColumns({
        @JoinColumn(name="estados_reservas_fecha_inicial", referencedColumnName = "fecha_inicial"),
        @JoinColumn(name="estados_reservas_fecha_final", referencedColumnName = "fecha_final")
    })
    private Estado_reserva estado_reserva;

    /*
    
    @ManyToOne
    @JoinColumn(name="planesdeconsumo_id_plan", referencedColumnName="id_plan");
    private PlanDeConsumo id_plan_consumo;
    
    public ReservaEntity() {;}
    
    public ReservaEntity(Integer id_reserva, Integer num_personas, Estado_reserva estado_reserva,
    PlanDeConsumo id_plan_consumo) {
        this.id_reserva = id_reserva;
        this.num_personas = num_personas;
        this.estado_reserva = estado_reserva;
        this.id_plan_consumo = id_plan_consumo;
    }
    */

    public Integer getId_reserva() {
        return id_reserva;
    }

    public void setId_reserva(Integer id_reserva) {
        this.id_reserva = id_reserva;
    }

    public Integer getNum_personas() {
        return num_personas;
    }

    public void setNum_personas(Integer num_personas) {
        this.num_personas = num_personas;
    }

    public Estado_reserva getEstado_reserva() {
        return estado_reserva;
    }

    public void setEstado_reserva(Estado_reserva estado_reserva) {
        this.estado_reserva = estado_reserva;
    }
    /* 
     
    public PlanDeConsumo getId_plan_consumo() {
        return id_plan_consumo;
    }
    
    public void setId_plan_consumo(PlanDeConsumo id_plan_consumo) {
        this.id_plan_consumo = id_plan_consumo;
    }
    */

    
}
