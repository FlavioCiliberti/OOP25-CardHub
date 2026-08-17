package it.unibo.cardhub.model.domain.exceptions;

/**
 * An exception for empty card collections.
 */
public class NoSuchCardsException extends RuntimeException {

    private static final long serialVersionUID = 42L;

    /**
     * Default exception constructor.
     */
    public NoSuchCardsException() {
        super("No such cards.");
    }

    /**
     * Exception constructor with a custom message.
     * 
     * @param message exception message
     */
    public NoSuchCardsException(final String message) {
        super(message);
    }
}
