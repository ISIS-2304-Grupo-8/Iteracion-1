// package uniandes.edu.co.proyecto.repositories;

// import java.util.Collection;

// import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.data.jpa.repository.Query;
// import org.springframework.data.repository.query.Param;

// import uniandes.edu.co.proyecto.modelo.ClienteEntity;

// public interface ClienteRepository extends JpaRepository<ClienteEntity,
// Integer>{

// @Query(value = "SELECT * FROM clientes", nativeQuery = true)
// Collection<ClienteEntity> darClientes();

// @Query(value = "SELECT * FROM clientes WHERE num_doc= :num_doc ", nativeQuery
// = true)
// ClienteEntity darCliente(@Param("num_doc") int id);

// void insertarCliente(int num_doc, String tipo_doc, String rol_cliente, String
// nombre, String email );

// }
