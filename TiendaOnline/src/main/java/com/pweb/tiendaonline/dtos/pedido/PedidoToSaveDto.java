package com.pweb.tiendaonline.dtos.pedido;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.pweb.tiendaonline.dtos.cliente.ClienteDto;
import com.pweb.tiendaonline.entities.PedidoStatus;

public record PedidoToSaveDto(
    Long id,
    LocalDateTime fechaPedido,
    PedidoStatus status,
    ClienteDto cliente
) {
}
