package com.pweb.tiendaonline.controllers;

import com.pweb.tiendaonline.dtos.producto.ProductoDto;
import com.pweb.tiendaonline.dtos.producto.ProductoToSaveDto;
import com.pweb.tiendaonline.exceptions.ProductoNotFoundException;
import com.pweb.tiendaonline.services.ProductoService;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
public class ProductoController {

    private final ProductoService productoService;

    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductoDto> getProductoById(@PathVariable("id") Long idProducto) {
        try {
            ProductoDto productoDto = productoService.findProductoById(idProducto);
            return ResponseEntity.ok().body(productoDto);
            } catch (ProductoNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<ProductoDto>> getAllProductos() {
        List<ProductoDto> productos = productoService.findAllProductos();
        return ResponseEntity.ok().body(productos);
    }

    @GetMapping("/search")
    public ResponseEntity<List<ProductoDto>> getProductoByNombre(@RequestParam String searchTerm) {
        try {
            List<ProductoDto> productos = productoService.findProductoByNombre(searchTerm);
            return ResponseEntity.ok().body(productos);
        } catch (ProductoNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/instock")
    public ResponseEntity<List<ProductoDto>> getProductosInStock() {
        List<ProductoDto> productos = productoService.findProductoByStockGreaterThan(0);
        return ResponseEntity.ok().body(productos);
    }

    @PostMapping
    public ResponseEntity<ProductoDto> saveProducto(@RequestBody ProductoToSaveDto productoDto) {
        ProductoDto nuevoProducto = productoService.saveProducto(productoDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoProducto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductoDto> updateProducto(@PathVariable Long id, @RequestBody ProductoToSaveDto productoDto) {
        ProductoDto productoActualizado = productoService.updateProducto(id, productoDto);
        return ResponseEntity.ok().body(productoActualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProducto(@PathVariable Long id) {
        productoService.deleteProducto(id);
        return ResponseEntity.noContent().build();
    }

}
