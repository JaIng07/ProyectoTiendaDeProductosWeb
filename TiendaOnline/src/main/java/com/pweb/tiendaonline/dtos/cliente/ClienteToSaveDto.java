package com.pweb.tiendaonline.dtos.cliente;

import com.pweb.tiendaonline.dtos.pedido.PedidoDto;

import java.util.List;

public record ClienteToSaveDto(
        Long id,
        String nombre,
        String email,
        String direccion
) {
}
