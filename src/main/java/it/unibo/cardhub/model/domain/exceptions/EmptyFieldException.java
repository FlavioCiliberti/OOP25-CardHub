package it.unibo.cardhub.model.domain.exceptions;

/**
 * An exception for empty GUI fields.
 */
public class EmptyFieldException extends RuntimeException {

    private static final long serialVersionUID = 42L;

    /**
     * Default exception constructor.
     */
    public EmptyFieldException() {
        super("Must fill all fields.");
    }

    /**
     * Exception constructor with a custom message.
     * 
     * @param message exception message
     */
    public EmptyFieldException(final String message) {
        super(message);
    }
}

