package uniandes.edu.co.proyecto.repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import uniandes.edu.co.proyecto.modelo.TipoUsuarioEntity;

public interface TipoUsuarioRepository extends JpaRepository<TipoUsuarioEntity, Integer> {

    // Create tipo de usuario
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO tiposusuario (id_usuario, rol) VALUES (tipos_us_seq.nextval,:rol)", nativeQuery = true)
    void crearTipoUsuario(@Param("rol") String rol);

    // Read tipos de usuario
    @Query(value = "SELECT * FROM tiposusuario", nativeQuery = true)
    Collection<TipoUsuarioEntity> darTiposUsuario();

    // Read tipo de usuario dado un tipo de usuario
    @Query(value = "SELECT * FROM tiposusuario WHERE id_usuario= :id_usuario ", nativeQuery = true)
    TipoUsuarioEntity darTipoUsuario(@Param("id_usuario") int id_usuario);

    // Update tipo de usuario
    @Modifying
    @Transactional
    @Query(value = "UPDATE tiposusuario SET rol = :rol WHERE id_usuario= :id_usuario", nativeQuery = true)
    void actualizarTipoUsuario(@Param("id_usuario") int id_usuario, @Param("rol") String rol);

    // Delete tipo de usuario
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM tiposusuario WHERE id_usuario = :id_usuario", nativeQuery = true)
    void eliminarTipoUsuario(@Param("id_usuario") int id_usuario);
}
