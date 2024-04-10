package com.pweb.tiendaonline.repositories;

import com.pweb.tiendaonline.dtos.pedido.PedidoToSaveDto;
import com.pweb.tiendaonline.entities.Pedido;
import com.pweb.tiendaonline.entities.PedidoStatus;
import com.pweb.tiendaonline.exceptions.PedidoNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {

    // Otros
    List<Pedido> findPedidoByFechaPedidoBetween(LocalDateTime fechaInicio, LocalDateTime fechaFin);

    @Query("SELECT p FROM Pedido p WHERE p.cliente.id = :idCliente AND p.status = :status")
    List<Pedido> findPedidoByClienteAndStatus(Long idCliente, PedidoStatus status);


    // Hay que revisarlo, no estamos seguros de si funciona o no
    @Query("SELECT p FROM Pedido p JOIN FETCH p.itemsPedidos WHERE p.cliente.id = :idCliente")
    List<Pedido> findPedidoByClienteWithItems(@Param("idCliente") Long idCliente);

}
