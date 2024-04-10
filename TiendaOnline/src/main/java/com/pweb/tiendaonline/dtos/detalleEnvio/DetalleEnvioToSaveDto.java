package com.pweb.tiendaonline.dtos.detalleEnvio;

import com.pweb.tiendaonline.dtos.pedido.PedidoDto;
import com.pweb.tiendaonline.dtos.pedido.PedidoToShowDto;

public record DetalleEnvioToSaveDto(
        Long id,
        String direccion,
        String transportadora,
        String numeroGuia,
        PedidoToShowDto pedido
) {
}
