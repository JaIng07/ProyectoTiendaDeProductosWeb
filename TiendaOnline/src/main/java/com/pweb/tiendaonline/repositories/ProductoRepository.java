package com.pweb.tiendaonline.repositories;

import com.pweb.tiendaonline.dtos.producto.ProductoToSaveDto;
import com.pweb.tiendaonline.entities.Producto;
import com.pweb.tiendaonline.exceptions.ProductoNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {

    // Otros
    List<Producto> findProductoByNombre(String nombre);

    List<Producto> findProductoByStockGreaterThan(Integer stock);

    @Query("SELECT p FROM Producto p WHERE p.price = :price AND p.stock = :stock")
    List<Producto> findProductoByPriceAndStock(@Param("price") Double price, @Param("stock") Integer stock);

}
