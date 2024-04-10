package com.pweb.tiendaonline.controllers;

import com.pweb.tiendaonline.dtos.cliente.ClienteDto;
import com.pweb.tiendaonline.dtos.itemPedido.ItemPedidoDto;
import com.pweb.tiendaonline.dtos.itemPedido.ItemPedidoMapper;
import com.pweb.tiendaonline.dtos.itemPedido.ItemPedidoToSaveDto;
import com.pweb.tiendaonline.dtos.pedido.PedidoDto;
import com.pweb.tiendaonline.dtos.pedido.PedidoToShowDto;
import com.pweb.tiendaonline.dtos.producto.ProductoDto;
import com.pweb.tiendaonline.entities.ItemPedido;
import com.pweb.tiendaonline.entities.Pedido;
import com.pweb.tiendaonline.entities.PedidoStatus;
import com.pweb.tiendaonline.entities.Producto;
import com.pweb.tiendaonline.services.ItemPedidoService;
import org.hamcrest.CoreMatchers;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@WebMvcTest(controllers = ItemPedidoController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
public class ItemPedidoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ItemPedidoService itemPedidoService;

    @Autowired
    private ObjectMapper objectMapper;
    private ItemPedidoMapper itemPedidoMapper;
    private ItemPedido orderItem;
    private ItemPedidoDto orderItemDto;
    private ItemPedidoToSaveDto orderItemToSaveDto;
    private Pedido order;
    private PedidoDto orderDto;
    private Producto product;
    private ProductoDto productDto;
    @BeforeEach
    void setUp(){
        orderItem = ItemPedido.builder()
                .id(1l)
                .cantidad(5)
                .precioUnitario(100.000)
                .producto(product)
                .build();
        orderItemDto = new ItemPedidoDto(
                orderItem.getId(),
                orderItem.getCantidad(),
                orderItem.getPrecioUnitario()
        );
        orderItemToSaveDto = new ItemPedidoToSaveDto(
                orderItem.getId(),
                orderItem.getCantidad(),
                orderItem.getPrecioUnitario(),
                orderDto,
                productDto
        );

        order = Pedido.builder()
                .id(1l)
                .status(PedidoStatus.PENDIENTE)
                .fechaPedido(LocalDateTime.of(2023, 12, 24, 13, 30, 0))
                .build();
        orderDto = new PedidoDto(
                order.getId(),
                order.getFechaPedido(),
                order.getStatus(),
                Collections.emptyList()
        );

        product = Producto.builder()
                .id(1l)
                .nombre("Laptop")
                .price(900.000)
                .stock(12)
                .build();
        productDto = new ProductoDto(
                product.getId(),
                product.getNombre(),
                product.getPrice(),
                product.getStock(),
                Collections.emptyList()
        );
    }

    @Test
    public void orderItemControllerTest_getItemPedidoById_ReturnResponse() throws Exception{
        Long orderItemId = 1l;
        when(itemPedidoService.findItemPedidoById(orderItemId)).thenReturn(orderItemDto);

        ResultActions response = mockMvc.perform(get("/api/v1/order-items")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(orderItemDto)));

        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", CoreMatchers.is(orderItemDto.id())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.cantidad", CoreMatchers.is(orderItemDto.cantidad())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.precioUnitario", CoreMatchers.is(orderItemDto.precioUnitario())));
    }

    @Test
    public void orderItemControllerTest_getAllItemPedidos_ReturnResponse() throws Exception{
        ItemPedidoDto firstOrderItem = new ItemPedidoDto(
                2l,
                10,
                200.000
        );
        List<ItemPedidoDto> orderItemList = List.of(firstOrderItem, orderItemDto);

        when(itemPedidoService.findAllItemPedidos()).thenReturn(orderItemList);

        ResultActions response = mockMvc.perform(get("/api/v1/order-items")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(orderItemDto)));

        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()", CoreMatchers.is(Arrays.asList(firstOrderItem, orderItemDto).size())))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id", is(2l)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].cantidad", is(10)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].precioUnitario", is(100.000)));
    }

    @Test
    public void orderItemControllerTest_getItemPedidosByOrderId_ReturnResponse() throws Exception{
        Long orderID = order.getId();
        when(itemPedidoService.findItemPedidoById(orderID)).thenReturn(orderItemDto);

        ResultActions response = mockMvc.perform(get("/api/v1/order-items")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(orderItemDto)));

        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", CoreMatchers.is(orderItemDto.id())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.cantidad", CoreMatchers.is(orderItemDto.cantidad())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.precioUnitario", CoreMatchers.is(orderItemDto.precioUnitario())));
    }

    @Test
    public void orderItemControllerTest_getItemPedidosByProductId_ReturnResponse() throws Exception{
        Long productID = product.getId();
        when(itemPedidoService.findItemPedidoByProductoId(productID)).thenReturn(Arrays.asList(orderItemDto));

        ResultActions response = mockMvc.perform(get("/api/v1/order-items")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(orderItemDto)));

        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()", CoreMatchers.is(Arrays.asList(orderItemDto).size())))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id", is(1l)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].cantidad", is(5)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].precioUnitario", is(100.000)));
    }

    @Test
    public void orderItemControllerTest_saveItemPedido_ReturnResponse() throws Exception{
        given(itemPedidoMapper.itemPedidoSaveDtoToItemPedidoEntity(any())).willReturn(orderItem);
        given(itemPedidoService.saveItemPedido(orderItemToSaveDto)).willReturn(orderItemDto);

        ResultActions response = mockMvc.perform(get("/api/v1/order-items")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));

        response.andExpect(MockMvcResultMatchers.status().isAccepted())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", CoreMatchers.is(orderItemDto.id())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.cantidad", CoreMatchers.is(orderItemDto.cantidad())));
    }

    @Test
    public void orderItemControllerTest_updateItemPedido_ReturnResponse() throws Exception{
        Long orderItemID = 1l;
        when(itemPedidoService.updateItemPedido(orderItemID, orderItemToSaveDto)).thenReturn(orderItemDto);

        ResultActions response = mockMvc.perform(put("/api/v1/order-items")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(orderItemDto)));

        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", CoreMatchers.is(orderItemDto.id())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.cantidad", CoreMatchers.is(orderItemDto.cantidad())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.precioUnitario", CoreMatchers.is(orderItemDto.precioUnitario())));
    }

    @Test
    public void orderItemControllerTest_deleteItemPedido_ReturnResponse() throws Exception{
        doNothing().when(itemPedidoService).deleteItemPedido(1l);

        ResultActions response = mockMvc.perform(delete("/api/v1/order-items")
                .contentType(MediaType.APPLICATION_JSON));

        response.andExpect(MockMvcResultMatchers.status().isOk());
    }
}
