package com.pweb.tiendaonline.dtos.producto;

import com.pweb.tiendaonline.dtos.itemPedido.ItemPedidoDto;

import java.util.Collections;
import java.util.List;

public record ProductoDto(
        Long id,
        String nombre,
        Double price,
        Integer stock,
        List<ItemPedidoDto> itemPedidos
) {

    public ProductoDto(Long id, String nombre, Double price, Integer stock, List<ItemPedidoDto> itemPedidos) {
        this.id = id;
        this.nombre = nombre;
        this.price = price;
        this.stock = stock;
        this.itemPedidos = itemPedidos != null ? itemPedidos : Collections.emptyList();
    }

}
