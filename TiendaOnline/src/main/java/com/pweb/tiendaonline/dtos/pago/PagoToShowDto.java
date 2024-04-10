package com.pweb.tiendaonline.dtos.pago;

import com.pweb.tiendaonline.dtos.pedido.PedidoToShowDto;
import com.pweb.tiendaonline.entities.PagoMetodo;

import java.time.LocalDateTime;
import java.util.UUID;

public record PagoToShowDto(
        Long id,
        Integer totalPago,
        LocalDateTime fechaPago,
        PagoMetodo metodoPago,
        PedidoToShowDto pedido
) {
}
