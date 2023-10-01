package uniandes.edu.co.proyecto.repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import uniandes.edu.co.proyecto.modelo.ProductoEntity;

public interface ProductoRepository extends JpaRepository<ProductoEntity, Integer> {

    // Read productos
    @Query(value = "SELECT * FROM productos", nativeQuery = true)
    Collection<ProductoEntity> darProductos();

    // Read producto dado un producto
    @Query(value = "SELECT * FROM productos WHERE id_producto =:id_producto AND ts_tipo_servicio=:tipo_servicio ", nativeQuery = true)
    ProductoEntity darProducto(@Param("id_producto") Integer id_producto, @Param("tipo_servicio") String tipo_servicio);

    // Crear un producto
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO productos (id_producto, nombre, costo, ts_tipo_servicio) VALUES (productos_sequence.nextval, :nombre, :costo, :tipo_servicio)", nativeQuery = true)
    void crearProducto(@Param("nombre") String nombre, @Param("costo") Integer costo,
            @Param("tipo_servicio") String tipo_servicio);

    // Actualizar un producto
    @Modifying
    @Transactional
    @Query(value = "UPDATE productos SET nombre=:nombre, costo=:costo WHERE id_producto=:id_producto AND ts_tipo_servicio=:tipo_servicio", nativeQuery = true)
    void actualizarProducto(@Param("id_producto") Integer id_producto, @Param("nombre") String nombre,
            @Param("costo") Integer costo, @Param("tipo_servicio") String tipo_servicio);

    // Eliminar producto
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM productos WHERE id_producto =:id_producto AND ts_tipo_servicio=:tipo_servicio", nativeQuery = true)
    void eliminarProducto(@Param("id_producto") Integer id_producto, @Param("tipo_servicio") String tipo_servicio);
}
