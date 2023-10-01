package uniandes.edu.co.proyecto.repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import uniandes.edu.co.proyecto.modelo.Registran;


public interface RegistranRepository extends JpaRepository<Registran, Integer>{

    @Query(value = "SELECT * FROM registran", nativeQuery = true)
    Collection<Registran> darRegistran();

    @Query(value = "SELECT * FROM registran WHERE clientes_num_doc = :clientes_num_doc AND empleados_num_doc = :empleados_num_doc", nativeQuery = true)
    Registran darRegistranPorId(@Param("clientes_num_doc") int clientes_num_doc, @Param("empleados_num_doc") int empleados_num_doc);
    
    @Modifying
    @Transactional
    @Query(value = "UPDATE registran SET clientes_num_doc = :clientes_num_doc_actualizado, empleados_num_doc = :empleados_num_doc_actualizado, check_in = :check_in WHERE clientes_num_doc = :clientes_num_doc AND empleados_num_doc = :empleados_num_doc", nativeQuery = true)
    void actualizarRegistran(@Param("clientes_num_doc") int clientes_num_doc, @Param("empleados_num_doc") int empleados_num_doc, @Param("check_in") int check_in, 
    @Param("clientes_num_doc_actualizado") int clientes_num_doc_actualizado, @Param("empleados_num_doc_actualizado") int empleados_num_doc_actualizado);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO registran (clientes_num_doc, empleados_num_doc, check_in) VALUES (:clientes_num_doc, :empleados_num_doc, :check_in)", nativeQuery = true)
    void insertarRegistran(@Param("clientes_num_doc") int clientes_num_doc, @Param("empleados_num_doc") int empleados_num_doc, @Param("check_in") int check_in);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM registran WHERE clientes_num_doc = :clientes_num_doc AND empleados_num_doc = :empleados_num_doc", nativeQuery = true)
    void eliminarRegistran(@Param("clientes_num_doc") int clientes_num_doc, @Param("empleados_num_doc") int empleados_num_doc);

}
