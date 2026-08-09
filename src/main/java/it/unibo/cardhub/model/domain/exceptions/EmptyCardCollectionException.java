package it.unibo.cardhub.model.domain.exceptions;

/**
 * An exception for empty card collections.
 */
public class EmptyCardCollectionException extends RuntimeException {

    private static final long serialVersionUID = 42L;

    /**
     * Default exception constructor.
     */
    public EmptyCardCollectionException() {
        super("Cannot extract a card from an empty card collection.");
    }

    /**
     * Exception constructor with a custom message.
     * 
     * @param message exception message
     */
    public EmptyCardCollectionException(final String message) {
        super(message);
    }
}
