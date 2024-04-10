package com.pweb.tiendaonline.services;

import com.pweb.tiendaonline.dtos.producto.ProductoDto;
import com.pweb.tiendaonline.dtos.producto.ProductoToSaveDto;
import com.pweb.tiendaonline.entities.Producto;
import com.pweb.tiendaonline.exceptions.ProductoNotFoundException;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProductoService {

    // CRUD
    ProductoDto saveProducto(ProductoToSaveDto producto);

    ProductoDto findProductoById(Long id) throws ProductoNotFoundException;

    List<ProductoDto> findAllProductos();

    ProductoDto updateProducto(Long id, ProductoToSaveDto producto);

    void deleteProducto(Long id);

    // Otros
    List<ProductoDto> findProductoByNombre(String nombre);

    List<ProductoDto> findProductoByStockGreaterThan(Integer stock);

    @Query("SELECT p FROM Producto p WHERE p.price = :price AND p.stock = :stock")
    List<ProductoDto> findProductoByPriceAndStock(@Param("price") Double price, @Param("stock") Integer stock);

}
