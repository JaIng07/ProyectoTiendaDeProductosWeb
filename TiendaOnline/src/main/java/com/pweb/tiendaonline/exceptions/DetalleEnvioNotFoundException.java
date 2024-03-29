package com.pweb.tiendaonline.exceptions;

public class DetalleEnvioNotFoundException extends RuntimeException {

    public DetalleEnvioNotFoundException() {

    }

    public DetalleEnvioNotFoundException(String message) {
        super(message);
    }

    public DetalleEnvioNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public DetalleEnvioNotFoundException(Throwable cause) {
        super(cause);
    }

    public DetalleEnvioNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
