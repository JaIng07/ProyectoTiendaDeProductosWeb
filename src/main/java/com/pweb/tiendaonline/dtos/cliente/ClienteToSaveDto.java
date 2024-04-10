package com.pweb.tiendaonline.dtos.cliente;

public record ClienteToSaveDto(
        Long id,
        String nombre,
        String email,
        String direccion
) {
}
