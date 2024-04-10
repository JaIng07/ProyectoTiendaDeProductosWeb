package com.pweb.tiendaonline.controllers;

import com.pweb.tiendaonline.dtos.cliente.ClienteDto;
import com.pweb.tiendaonline.dtos.pago.PagoDto;
import com.pweb.tiendaonline.dtos.pedido.PedidoDto;
import com.pweb.tiendaonline.dtos.pedido.PedidoMapper;
import com.pweb.tiendaonline.dtos.pedido.PedidoToSaveDto;
import com.pweb.tiendaonline.entities.Cliente;
import com.pweb.tiendaonline.entities.PagoMetodo;
import com.pweb.tiendaonline.entities.Pedido;
import com.pweb.tiendaonline.entities.PedidoStatus;
import com.pweb.tiendaonline.services.PedidoService;
import org.hamcrest.CoreMatchers;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
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

@WebMvcTest(controllers = PedidoController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
public class PedidoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PedidoService pedidoService;

    @Autowired
    private ObjectMapper objectMapper;
    private PedidoMapper pedidoMapper;
    private Pedido order;
    private PedidoDto orderDto;
    private PedidoToSaveDto orderToSaveDto;
    private Cliente client;
    private ClienteDto clientDto;
    @BeforeEach
    void setUp(){
        order = Pedido.builder()
                .id(1l)
                .status(PedidoStatus.ENVIADO)
                .fechaPedido(LocalDateTime.of(2023, 12, 24, 13, 30, 0))
                .cliente(client)
                .build();
        orderDto = new PedidoDto(
                order.getId(),
                order.getFechaPedido(),
                order.getStatus(),
                Collections.emptyList()
        );
        orderToSaveDto = new PedidoToSaveDto(
                order.getId(),
                order.getFechaPedido(),
                order.getStatus(),
                clientDto
        );

        client = Cliente.builder()
                .id(1l)
                .nombre("Julian Pizarro")
                .direccion("Calle2i#34-I0")
                .email("jpizarro@unimagdalena.edu.co")
                .build();
        clientDto = new ClienteDto(
                client.getId(),
                client.getNombre(),
                client.getEmail(),
                client.getDireccion(),
                Collections.emptyList()
        );
    }

    @Test
    public void orderControllerTest_getPedidoById_ReturnResponse() throws Exception{
        Long orderID = order.getId();
        when(pedidoService.findPedidoById(orderID)).thenReturn(orderDto);

        ResultActions response = mockMvc.perform(get("/api/v1/orders")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(orderDto)));

        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", CoreMatchers.is(orderDto.id())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.estado", CoreMatchers.is(orderDto.status())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.fechaPedido", CoreMatchers.is(orderDto.fechaPedido())));
    }

    @Test
    public void orderControllerTest_getAllPedidos_ReturnResponse() throws Exception{
        PedidoDto firstOrder = new PedidoDto(
                2l,
                LocalDateTime.of(2023, 12, 24, 13, 30, 0),
                PedidoStatus.ENVIADO,
                Collections.emptyList()
        );
        List<PedidoDto> orderList = List.of(firstOrder, orderDto);

        when(pedidoService.findAllPedidos()).thenReturn(orderList);

        ResultActions response = mockMvc.perform(get("/api/v1/orders")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(orderDto)));

        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()", CoreMatchers.is(Arrays.asList(firstOrder, orderDto).size())))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id", is(2l)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].Estado", is(PedidoStatus.ENVIADO)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].Estado", is(PedidoStatus.ENVIADO)));
    }

    @Test
    public void orderControllerTest_getPedidosByDateRange_ReturnResponse() throws Exception{
        LocalDateTime fechaInicial = LocalDateTime.of(2020, 10, 10, 10, 10);
        LocalDateTime fechaFinal = LocalDateTime.now();
        when(pedidoService.findPedidoByFechaPedidoBetween(fechaInicial, fechaFinal)).thenReturn(Arrays.asList(orderDto));

        ResultActions response = mockMvc.perform(get("/api/v1/orders")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(orderDto)));

        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", CoreMatchers.is(orderDto.id())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.estado", CoreMatchers.is(orderDto.status())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.fechaPedido", CoreMatchers.is(orderDto.fechaPedido())));
    }

    @Test
    public void orderControllerTest_savePedido_ReturnResponse() throws Exception{
        given(pedidoMapper.pedidoSaveDtoToPedidoEntity(any())).willReturn(order);
        given(pedidoService.savePedido(orderToSaveDto)).willReturn(orderDto);

        ResultActions response = mockMvc.perform(get("/api/v1/orders")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));

        response.andExpect(MockMvcResultMatchers.status().isAccepted())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", CoreMatchers.is(orderDto.id())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.estado", CoreMatchers.is(orderDto.status())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.fechaPedido", CoreMatchers.is(orderDto.fechaPedido())));
    }

    @Test
    public void orderControllerTest_updatePedido_ReturnResponse() throws Exception{
        Long orderID = order.getId();
        when(pedidoService.updatePedido(orderID, orderToSaveDto)).thenReturn(orderDto);

        ResultActions response = mockMvc.perform(put("/api/v1/orders")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(orderDto)));

        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", CoreMatchers.is(orderDto.id())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.estado", CoreMatchers.is(orderDto.status())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.fechaPedido", CoreMatchers.is(orderDto.fechaPedido())));
    }

    @Test
    public void orderControllerTest_deletePedido_ReturnResponse() throws Exception{
        doNothing().when(pedidoService).deletePedido(1l);

        ResultActions response = mockMvc.perform(delete("/api/v1/orders")
                .contentType(MediaType.APPLICATION_JSON));

        response.andExpect(MockMvcResultMatchers.status().isOk());
        response.andExpect(MockMvcResultMatchers.status().isNotFound());

    }
}
