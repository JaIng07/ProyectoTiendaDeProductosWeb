package com.pweb.tiendaonline.dtos.detalleEnvio;

import com.pweb.tiendaonline.dtos.pedido.PedidoToShowDto;

import java.util.UUID;

public record DetalleEnvioToShowDto(
        Long id,
        String direccion,
        String transportadora,
        String numeroGuia,
        PedidoToShowDto pedido
) {
}
