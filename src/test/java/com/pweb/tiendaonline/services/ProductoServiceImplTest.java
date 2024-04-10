package com.pweb.tiendaonline.services;


import com.pweb.tiendaonline.dtos.producto.ProductoDto;
import com.pweb.tiendaonline.dtos.producto.ProductoMapper;
import com.pweb.tiendaonline.dtos.producto.ProductoToSaveDto;
import com.pweb.tiendaonline.dtos.producto.ProductoToShowDto;
import com.pweb.tiendaonline.entities.Producto;
import com.pweb.tiendaonline.repositories.ProductoRepository;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class ProductoServiceImplTest {

    @Mock
    private ProductoRepository productoRepository;

    @Mock
    private ProductoMapper productoMapper;

    @InjectMocks
    private ProductoService productoService;

    private Producto firstproduct;
    private ProductoToShowDto firstProductToShowDto;
    private Producto secondProduct;
    private ProductoToShowDto secondProductToShowDto;

    @BeforeEach
    void setUp() {
        firstproduct = Producto.builder()
                .id(1l)
                .nombre("Laptop")
                .stock(12)
                .price(150.000)
                .build();
        firstProductToShowDto = new ProductoToShowDto(
                firstproduct.getId(),
                firstproduct.getNombre(),
                firstproduct.getPrice(),
                firstproduct.getStock()
        );
        secondProduct = Producto.builder()
                .id(2l)
                .nombre("Mouse")
                .price(20.000)
                .stock(12)
                .build();
        secondProductToShowDto = new ProductoToShowDto(
                secondProduct.getId(),
                secondProduct.getNombre(),
                secondProduct.getPrice(),
                secondProduct.getStock()
        );
    }

    @Test
    void ProductServiceTest_WhenSaveProduct_ThenReturnProduct() {

        ProductoToSaveDto productToSaveDto = new ProductoToSaveDto(
                firstproduct.getId(),
                firstproduct.getNombre(),
                firstproduct.getPrice(),
                firstproduct.getStock()
        );

        given(productoRepository.findById(firstproduct.getId())).willReturn(Optional.ofNullable(firstproduct));
        given(productoMapper.productoSaveDtoToProductoEntity(any())).willReturn(firstproduct);
        when(productoRepository.save(any())).thenReturn(firstproduct);

        ProductoDto savedProduct = productoService.saveProducto(productToSaveDto);

        Assertions.assertThat(savedProduct).isNotNull();
        Assertions.assertThat(savedProduct.nombre()).isEqualTo("Laptop");
    }

    @Test
    void ProductServiceTest_WhenfindProductoById_ThenReturnProduct() {

        given(productoRepository.findById(secondProduct.getId())).willReturn(Optional.ofNullable(secondProduct));

        ProductoDto savedProduct = productoService.findProductoById(secondProduct.getId());

        Assertions.assertThat(savedProduct).isNotNull();
        Assertions.assertThat(savedProduct.nombre()).isEqualTo("Mouse");
    }

    @Test
    void ProductServiceTest_WhenupdateProducto_ThenReturnProduct() {

        ProductoToSaveDto productToSaveDto = new ProductoToSaveDto(
                firstproduct.getId(),
                firstproduct.getNombre(),
                firstproduct.getPrice(),
                firstproduct.getStock()
        );
        when(productoRepository.findById(1l)).thenReturn(Optional.ofNullable(firstproduct));
        when(productoRepository.save(Mockito.any(Producto.class))).thenReturn(firstproduct);

        ProductoDto savedProduct = productoService.findProductoById(1l);

        assertThat(savedProduct).isNotNull();
        assertThat(savedProduct.id()).isEqualTo(1l);
    }

    @Test
    void ProductServiceTest_WhendeleteProducto_ThenReturnProduct() {

        given(productoRepository.findById(1l)).willReturn(Optional.ofNullable(firstproduct));
        doNothing().when(productoRepository).deleteById(1l);

        ProductoDto deletedProduct = productoService.findProductoById(1l);

        assertAll(() -> productoService.deleteProducto(1l));
        assertThat(deletedProduct).isNull();
    }

    @Test
    void ProductServiceTest_WhenfindProductoByNombre_ThenReturnProduct() {

        when(productoRepository.findProductoByNombre("Mouse")).thenReturn(List.of(secondProduct));

        List<ProductoDto> savedProducts = productoService.findProductoByNombre("Mouse");

        assertThat(savedProducts.size()).isGreaterThan(0);
        assertThat(savedProducts.contains(secondProduct)).isTrue();
    }

    @Test
    void ProductServiceTest_WhenfindProductoByStockGreaterThan_ThenReturnProduct() {

        when(productoRepository.findProductoByStockGreaterThan(12)).thenReturn(List.of(firstproduct, secondProduct));

        List<ProductoDto> savedProducts = productoService.findProductoByStockGreaterThan(12);

        assertThat(savedProducts.size()).isGreaterThan(0);
        assertThat(savedProducts.contains(secondProduct)).isTrue();
    }

    @Test
    void ProductServiceTest_WhenfindProductoByPriceAndStock_ThenReturnProduct() {

        given(productoRepository.findProductoByPriceAndStock(150.000, 12)).willReturn(List.of(firstproduct));

        List<ProductoDto> savedProducts = productoService.findProductoByPriceAndStock(150.000, 12);

        assertThat(savedProducts.size()).isGreaterThan(0);
        assertThat(savedProducts.contains(firstproduct)).isTrue();
        assertThat(savedProducts.get(0).id()).isEqualTo(1l);
        assertThat(savedProducts.getFirst()).isEqualTo(firstproduct);

    }
}
