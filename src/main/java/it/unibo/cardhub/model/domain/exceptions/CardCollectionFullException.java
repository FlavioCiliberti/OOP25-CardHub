package it.unibo.cardhub.model.domain.exceptions;

/**
 * 
 * An exception for trying to add a card to a full CardCollection
 */
public class CardCollectionFullException extends Exception {

    private static final long serialVersionUID = 42L;

    /**
     * Default exception constructor.
     */
    public CardCollectionFullException() {
        super("Cannot add a card to a full hand");
    }

    /**
     * Exception constructor with a custom message.
     * 
     * @param message exception message
     */
    public CardCollectionFullException(final String message) {
        super(message);
    }
}
