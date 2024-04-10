package com.pweb.tiendaonline.dtos.producto;

import java.util.UUID;

public record ProductoToShowDto(
        Long id,
        String nombre,
        Double price,
        Integer stock
) {
}
