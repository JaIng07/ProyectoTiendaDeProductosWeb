package com.pweb.tiendaonline.exceptions;

public class ItemPedidoNotFoundException extends RuntimeException {

    public ItemPedidoNotFoundException() {

    }

    public ItemPedidoNotFoundException(String message) {
        super(message);
    }

    public ItemPedidoNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public ItemPedidoNotFoundException(Throwable cause) {
        super(cause);
    }

    public ItemPedidoNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
