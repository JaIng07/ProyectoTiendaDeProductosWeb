package com.pweb.tiendaonline.entities;

public enum PagoMetodo {
    EFECTIVO,
    TARJETA_CREDITO,
    PAYPAL,
    NEQUI,
    DAVIPLATA,
    PSE;

    public static PagoMetodo fromString(String value) {
        return switch (value.toUpperCase()) {
            case "EFECTIVO" -> EFECTIVO;
            case "TARJETA_CREDITO" -> TARJETA_CREDITO;
            case "PAYPAL" -> PAYPAL;
            case "NEQUI" -> NEQUI;
            case "DAVIPLATA" -> DAVIPLATA;
            case "PSE" -> PSE;
            default -> throw new IllegalArgumentException("Metodo de pago invalido: " + value);
        };
    }

}
