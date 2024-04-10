package com.pweb.tiendaonline.dtos.pago;

import java.time.LocalDateTime;

import com.pweb.tiendaonline.entities.PagoMetodo;

public record PagoToSaveDto(
    Long id,
    Double totalPago,
    LocalDateTime fechaPago,
    PagoMetodo metodoPago
) {
}
