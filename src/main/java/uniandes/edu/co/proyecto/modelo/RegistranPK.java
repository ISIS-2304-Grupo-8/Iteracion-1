package uniandes.edu.co.proyecto.modelo;

import java.io.Serializable;

import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Embeddable
public class RegistranPK implements Serializable{
    

    @ManyToOne
    @JoinColumn(name = "clientes_num_doc", referencedColumnName = "num_doc")
    private ClienteEntity id_cliente;

    @ManyToOne
    @JoinColumn(name = "empleados_num_doc", referencedColumnName = "num_doc")
    private EmpleadoEntity id_empleado;

    public RegistranPK(){;}

    public RegistranPK(ClienteEntity id_cliente, EmpleadoEntity id_empleado) {
        this.id_cliente = id_cliente;
        this.id_empleado = id_empleado;
    }

    public ClienteEntity getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(ClienteEntity id_cliente) {
        this.id_cliente = id_cliente;
    }

    public EmpleadoEntity getId_empleado() {
        return id_empleado;
    }

    public void setId_empleado(EmpleadoEntity id_empleado) {
        this.id_empleado = id_empleado;
    }

    
    
}
