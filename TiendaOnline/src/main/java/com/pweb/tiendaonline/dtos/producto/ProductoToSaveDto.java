package com.pweb.tiendaonline.dtos.producto;

public record ProductoToSaveDto(
        Long id,
        String nombre,
        Double precio,
        Integer stock
) {
}
