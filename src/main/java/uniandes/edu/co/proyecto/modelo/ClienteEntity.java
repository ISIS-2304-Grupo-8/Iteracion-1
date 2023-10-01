package uniandes.edu.co.proyecto.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "clientes")
public class ClienteEntity {

    @Id
    private Integer num_doc;
    private String nombre;
    private String email;
    private String tipo_doc;
    private String rol_cliente;

    @OneToOne
    @JoinColumn(name = "reservas_id_reserva", referencedColumnName = "id_reserva")
    private ReservaEntity reserva;

    public ClienteEntity() {;}

    public ClienteEntity(Integer num_doc, String nombre, String email, String tipo_doc, String rol_cliente,
            ReservaEntity reserva) {
        this.num_doc = num_doc;
        this.nombre = nombre;
        this.email = email;
        this.tipo_doc = tipo_doc;
        this.rol_cliente = rol_cliente;
        this.reserva = reserva;
    }

    public Integer getNum_doc() {
        return num_doc;
    }

    public void setNum_doc(Integer num_doc) {
        this.num_doc = num_doc;
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

    public String getTipo_doc() {
        return tipo_doc;
    }

    public void setTipo_doc(String tipo_doc) {
        this.tipo_doc = tipo_doc;
    }

    public String getRol_cliente() {
        return rol_cliente;
    }

    public void setRol_cliente(String rol_cliente) {
        this.rol_cliente = rol_cliente;
    }

    public ReservaEntity getReserva() {
        return reserva;
    }

    public void setReserva(ReservaEntity reserva) {
        this.reserva = reserva;
    }

    


}
