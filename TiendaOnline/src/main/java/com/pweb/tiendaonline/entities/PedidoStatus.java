package com.pweb.tiendaonline.entities;

public enum PedidoStatus {
    PENDIENTE,
    ENVIADO,
    ENTREGADO;

    public static PedidoStatus fromString(String value) {
        return switch (value.toUpperCase()) {
            case "PENDIENTE" -> PENDIENTE;
            case "ENVIADO" -> ENVIADO;
            case "ENTREGADO" -> ENTREGADO;
            default -> throw new IllegalArgumentException("Estado no válido: " + value);
        };
    }

}
