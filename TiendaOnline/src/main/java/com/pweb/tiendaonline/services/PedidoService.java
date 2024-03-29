package com.pweb.tiendaonline.services;

import com.pweb.tiendaonline.dtos.pedido.PedidoDto;
import com.pweb.tiendaonline.dtos.pedido.PedidoToSaveDto;
import com.pweb.tiendaonline.entities.Pedido;
import com.pweb.tiendaonline.entities.PedidoStatus;
import com.pweb.tiendaonline.exceptions.PedidoNotFoundException;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface PedidoService {

    // CRUD
    PedidoDto savePedido(PedidoToSaveDto pedido);

    PedidoDto findPedidoById(Long id) throws PedidoNotFoundException;

    List<PedidoDto> findAllPedidos();

    PedidoDto updatePedido(Long id, PedidoToSaveDto pedido);

    void deletePedido(Long id);

    // Otros
    List<PedidoDto> findPedidoByFechaPedidoBetween(LocalDateTime fechaInicio, LocalDateTime fechaFin);

    @Query("SELECT p FROM Pedido p WHERE p.cliente.id = :clienteId AND p.status = :status")
    List<PedidoDto> findPedidoByClienteAndStatus(Long idCliente, PedidoStatus status);

    @Query("SELECT p FROM Pedido p JOIN FETCH p.itemsPedidos WHERE p.cliente.id = :idCliente")
    List<PedidoDto> findPedidoByClienteWithItems(@Param("idCliente") Long idCliente);

}
