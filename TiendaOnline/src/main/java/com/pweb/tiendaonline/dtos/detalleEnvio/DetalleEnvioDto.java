package com.pweb.tiendaonline.dtos.detalleEnvio;

import com.pweb.tiendaonline.entities.Pedido;

public record DetalleEnvioDto(
        Long id,
        String direccion,
        String transportadora,
        String numeroGuia,

        // Hay que aplicar lo de la relacion 1:1 entre DetalleEnvio y Pedido
        Pedido pedido
) {
}
