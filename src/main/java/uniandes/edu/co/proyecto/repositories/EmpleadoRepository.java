package uniandes.edu.co.proyecto.repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import uniandes.edu.co.proyecto.modelo.EmpleadoEntity;


public interface EmpleadoRepository extends JpaRepository<EmpleadoEntity,
    Integer>{

    @Query(value = "SELECT * FROM empleados", nativeQuery = true)
    Collection<EmpleadoEntity> darEmpleados();

    @Query(value = "SELECT * FROM empleados WHERE num_doc= :num_doc ", nativeQuery
    = true)
    EmpleadoEntity darEmpleado(@Param("num_doc") int num_doc);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO empleados (num_doc, nombre, email, tipo_doc, rol) VALUES (:num_doc, :nombre, :email, :tipo_doc, :rol)", nativeQuery = true)
    void insertarEmpleado(@Param("num_doc") int num_doc, @Param("nombre") String nombre, @Param("email") String email , 
    @Param("tipo_doc") String tipo_doc, @Param("rol") String rol);

    @Modifying
    @Transactional
    @Query(value = "UPDATE empleados SET nombre = :nombre, email = :email, tipo_doc = :tipo_doc, rol = :rol WHERE num_doc = :num_doc", nativeQuery = true)
    void actualizarEmpleado(@Param("num_doc") int num_doc, @Param("nombre") String nombre, @Param("email") String email , 
    @Param("tipo_doc") String tipo_doc, @Param("rol") String rol);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM empleados WHERE num_doc = :num_doc", nativeQuery = true)
    void eliminarEmpleado(@Param("num_doc") int num_doc);
}
