package it.unibo.cardhub.model.domain.exceptions;

/**
 * 
 * An exception for trying to add a card to a full hand
 */
public class HandFullException extends Exception {

    private static final long serialVersionUID = 42L;

    /**
     * Default exception constructor.
     */
    public HandFullException() {
        super("Cannot add a card to a full hand");
    }

    /**
     * Exception constructor with a custom message.
     * 
     * @param message exception message
     */
    public HandFullException(final String message) {
        super(message);
    }
}
