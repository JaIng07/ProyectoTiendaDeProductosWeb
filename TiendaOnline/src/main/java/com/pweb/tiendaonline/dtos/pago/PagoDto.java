package com.pweb.tiendaonline.dtos.pago;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.pweb.tiendaonline.entities.PagoMetodo;
import com.pweb.tiendaonline.entities.Pedido;

public record PagoDto (
    Long id,
    Double totalPago,
    LocalDateTime fechaPago,
    PagoMetodo metodoPago,
    Pedido pedido
){
}
