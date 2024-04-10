package com.pweb.tiendaonline.dtos.itemPedido;

import com.pweb.tiendaonline.dtos.pedido.PedidoDto;
import com.pweb.tiendaonline.dtos.producto.ProductoDto;

public record ItemPedidoToSaveDto(
        Long id,
        Integer cantidad,
        Double precioUnitario,
        PedidoDto pedido,
        ProductoDto producto
) {
}
