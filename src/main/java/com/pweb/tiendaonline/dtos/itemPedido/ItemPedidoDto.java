package com.pweb.tiendaonline.dtos.itemPedido;

public record ItemPedidoDto(
        Long id,
        Integer cantidad,
        Double precioUnitario
) {
}
