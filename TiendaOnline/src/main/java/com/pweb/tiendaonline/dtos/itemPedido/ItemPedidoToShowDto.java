package com.pweb.tiendaonline.dtos.itemPedido;

import com.pweb.tiendaonline.dtos.pedido.PedidoToShowDto;
import com.pweb.tiendaonline.dtos.producto.ProductoToShowDto;

import java.util.UUID;

public record ItemPedidoToShowDto(
        long id,
        Integer cantidad,
        Float precioUnitario,
        PedidoToShowDto pedido,
        ProductoToShowDto product
) {
}
