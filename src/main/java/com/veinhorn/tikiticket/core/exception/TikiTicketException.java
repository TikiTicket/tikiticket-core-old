package com.veinhorn.tikiticket.core.exception;

/**
 * Created by veinhorn on 22.12.16.
 */
public class TikiTicketException extends Exception {
    private Throwable original;
    private String message;

    public TikiTicketException(String message) {
        super(message);
        this.message = message;
    }

    public TikiTicketException(String message, Throwable original) {
        super(message, original);
        this.original = original;
        this.message = message;
    }

    public Throwable getOriginal() {
        return original;
    }
}
