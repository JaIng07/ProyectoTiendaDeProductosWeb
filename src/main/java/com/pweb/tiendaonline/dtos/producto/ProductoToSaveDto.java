package com.pweb.tiendaonline.dtos.producto;

public record ProductoToSaveDto(
        Long id,
        String nombre,
        Double price,
        Integer stock
) {
}
