package com.pweb.tiendaonline.exceptions;

public class PagoNotFoundException extends RuntimeException {

    public PagoNotFoundException() {

    }

    public PagoNotFoundException(String message) {
        super(message);
    }

    public PagoNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public PagoNotFoundException(Throwable cause) {
        super(cause);
    }

    public PagoNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
