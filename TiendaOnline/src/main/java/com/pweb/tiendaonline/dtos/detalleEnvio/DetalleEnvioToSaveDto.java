package com.pweb.tiendaonline.dtos.detalleEnvio;

import com.pweb.tiendaonline.dtos.pedido.PedidoDto;

public record DetalleEnvioToSaveDto(
        Long id,
        String direccion,
        String transportadora,
        String numeroGuia,
        PedidoDto pedido
) {
}
