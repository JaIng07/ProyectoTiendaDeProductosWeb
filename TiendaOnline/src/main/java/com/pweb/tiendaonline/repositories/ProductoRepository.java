package com.pweb.tiendaonline.repositories;

import com.pweb.tiendaonline.entities.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductoRepository extends JpaRepository<Producto, Long> {

    // CRUD
    void saveProducto(Producto producto);

    Producto findProductoById(Long id);

    List<Producto> findAllProductos();

    void updateProducto(Producto producto);

    void deleteProductoById(Long id);

    // Otros
    List<Producto> findProductoByNombre(String nombre);

    List<Producto> findProductoInStock(Integer stock);

    List<Producto> findProductoByPrecioAndStock(Double precio, Integer stock);

}
