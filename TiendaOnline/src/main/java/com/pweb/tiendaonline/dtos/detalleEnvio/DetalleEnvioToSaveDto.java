package com.pweb.tiendaonline.dtos.detalleEnvio;

public record DetalleEnvioToSaveDto(
        Long id,
        String direccion,
        String transportadora,
        String numeroGuia
) {
}
