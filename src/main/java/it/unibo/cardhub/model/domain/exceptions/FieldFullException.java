package it.unibo.cardhub.model.domain.exceptions;

/**
 * An exception for trying to add a card to a full CardCollection.
 */
public class FieldFullException extends Exception {

    private static final long serialVersionUID = 42L;

    /**
     * Default exception constructor.
     */
    public FieldFullException() {
        super("Cannot add a card to a full field");
    }

    /**
     * Exception constructor with a custom message.
     * 
     * @param message exception message
     */
    public FieldFullException(final String message) {
        super(message);
    }
}
