package com.pweb.tiendaonline.dtos.pedido;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import com.pweb.tiendaonline.dtos.itemPedido.ItemPedidoDto;
import com.pweb.tiendaonline.entities.PedidoStatus;

public record PedidoDto(
    Long id,
    LocalDateTime fechaPedido,
    PedidoStatus status,
    List<ItemPedidoDto> itemPedido
) {
    public List<ItemPedidoDto> itemPedido(){
        return Collections.unmodifiableList(itemPedido);
    }
}
