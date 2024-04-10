package com.pweb.tiendaonline.dtos.cliente;

import com.pweb.tiendaonline.dtos.pedido.PedidoDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collections;
import java.util.List;

public record ClienteDto(
        Long id,
        String nombre,
        String email,
        String direccion,
        List<PedidoDto> pedidos
) {

    public ClienteDto(Long id, String nombre, String email, String direccion, List<PedidoDto> pedidos) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.direccion = direccion;
        this.pedidos = pedidos != null ? pedidos : Collections.emptyList();
    }

}
