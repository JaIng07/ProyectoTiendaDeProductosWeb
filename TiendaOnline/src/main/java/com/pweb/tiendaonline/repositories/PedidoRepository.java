package com.pweb.tiendaonline.repositories;

import com.pweb.tiendaonline.entities.Pedido;
import com.pweb.tiendaonline.entities.PedidoStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {

    // CRUD
    void savePedido(Pedido pedido);

    Pedido findPedidoById(Long id);

    List<Pedido> findAllPedidos();

    void updatePedido(Pedido pedido);

    void deletePedidoById(Long id);

    // Otros
    List<Pedido> findPedidoByFechaPedidoBetween(LocalDateTime fechaInicio, LocalDateTime fechaFin);

    List<Pedido> findPedidoByClienteAndStatus(Long idCliente, PedidoStatus status);

    // Hay que revisarlo, no estamos seguros de si funciona o no
    @Query("SELECT p FROM Pedido p JOIN FETCH p.itemsPedidos WHERE p.cliente.id = :idCliente")
    List<Pedido> findPedidoByClienteWithItems(@Param("idCliente") Long idCliente);

}
