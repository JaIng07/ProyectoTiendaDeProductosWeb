package com.pweb.tiendaonline.services;

import com.pweb.tiendaonline.dtos.producto.ProductoDto;
import com.pweb.tiendaonline.dtos.producto.ProductoMapper;
import com.pweb.tiendaonline.dtos.producto.ProductoToSaveDto;
import com.pweb.tiendaonline.entities.Producto;
import com.pweb.tiendaonline.exceptions.NotAbleToDeleteException;
import com.pweb.tiendaonline.exceptions.ProductoNotFoundException;
import com.pweb.tiendaonline.repositories.ProductoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoServiceImpl implements ProductoService {

    private final ProductoMapper productoMapper;
    private final ProductoRepository productoRepository;

    public ProductoServiceImpl(ProductoMapper productoMapper, ProductoRepository productoRepository) {
        this.productoMapper = productoMapper;
        this.productoRepository = productoRepository;
    }

    // Implementación de los métodos de la interfaz ProductoService
    @Override
    public ProductoDto saveProducto(ProductoToSaveDto productoDto) {
        Producto producto = productoMapper.productoSaveDtoToProductoEntity(productoDto);
        Producto productoGuardado = productoRepository.save(producto);
        return productoMapper.productoEntityToProductoDto(productoGuardado);
    }

    @Override
    public ProductoDto findProductoById(Long id) throws ProductoNotFoundException {
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new ProductoNotFoundException("Producto no encontrado"));
        return productoMapper.productoEntityToProductoDto(producto);
    }

    @Override
    public List<ProductoDto> findAllProductos() {
        List<Producto> productos = productoRepository.findAll();
        return productos.stream()
                .map(producto -> productoMapper.productoEntityToProductoDto(producto))
                .toList();
    }

    @Override
    public ProductoDto updateProducto(Long id, ProductoToSaveDto productoDto) {
        Producto productoInDb = productoRepository.findById(id)
                        .orElseThrow(() -> new ProductoNotFoundException("Producto no encontrado"));

        productoInDb.setNombre(productoDto.nombre());
        productoInDb.setPrice(productoDto.price());
        productoInDb.setStock(productoDto.stock());

        Producto productoGuardado = productoRepository.save(productoInDb);

        return productoMapper.productoEntityToProductoDto(productoGuardado);
    }

    @Override
    public void deleteProducto(Long id) {
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new NotAbleToDeleteException("Producto no encontrado"));
        productoRepository.delete(producto);
    }

    @Override
    public List<ProductoDto> findProductoByNombre(String nombre) {
        List<Producto> productos = productoRepository.findProductoByNombre(nombre);
        if (productos.isEmpty()) {
            throw new ProductoNotFoundException("Producto no encontrado");
        }
        return productos.stream()
                .map(producto -> productoMapper.productoEntityToProductoDto(producto))
                .toList();
    }

    @Override
    public List<ProductoDto> findProductoByStockGreaterThan(Integer stock) {
        List<Producto> productos = productoRepository.findProductoByStockGreaterThan(stock);
        if (productos.isEmpty()) {
            throw new ProductoNotFoundException("Producto no encontrado");
        }
        return productos.stream()
                .map(producto -> productoMapper.productoEntityToProductoDto(producto))
                .toList();
    }

    @Override
    public List<ProductoDto> findProductoByPriceAndStock(Double price, Integer stock) {
        List<Producto> productos = productoRepository.findProductoByPriceAndStock(price, stock);
        if (productos.isEmpty()) {
            throw new ProductoNotFoundException("Producto no encontrado");
        }
        return productos.stream()
                .map(producto -> productoMapper.productoEntityToProductoDto(producto))
                .toList();
    }

}
