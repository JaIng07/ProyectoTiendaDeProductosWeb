package com.pweb.tiendaonline.repositories;

import com.pweb.tiendaonline.entities.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductoRepository extends JpaRepository<Producto, Long> {

    // Otros
    List<Producto> findProductoByNombre(String nombre);

    List<Producto> findProductoByStockGreaterThan(Integer stock);

    @Query("SELECT p FROM Producto p WHERE p.price = :price AND p.stock = :stock")
    List<Producto> findProductoByPriceAndStock(@Param("price") Double price, @Param("stock") Integer stock);

}
