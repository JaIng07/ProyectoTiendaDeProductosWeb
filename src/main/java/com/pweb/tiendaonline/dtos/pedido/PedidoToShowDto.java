package com.pweb.tiendaonline.dtos.pedido;

import com.pweb.tiendaonline.dtos.cliente.ClienteToShowDto;
import com.pweb.tiendaonline.entities.PedidoStatus;

import java.time.LocalDateTime;
import java.util.UUID;

public record PedidoToShowDto(
        Long id,
        LocalDateTime fechaPedido,
        PedidoStatus status,
        ClienteToShowDto cliente
) {
}
