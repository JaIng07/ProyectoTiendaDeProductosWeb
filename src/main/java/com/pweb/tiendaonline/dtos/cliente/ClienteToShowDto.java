package com.pweb.tiendaonline.dtos.cliente;

import java.util.UUID;

public record ClienteToShowDto(
        Long id,
        String nombre,
        String email,
        String direccion
) {
}
