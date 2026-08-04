package it.unibo.cardhub.controller.exceptions;

/**
 * Exception for invalid form fields.
 */
public class InvalidFormException extends RuntimeException {
    
    private static final long serialVersionUID = 42L;

    /**
     * Default exception constructor.
     */
    public InvalidFormException() {
        super("Invalid form: At least one invalid field.");
    }

    /**
     * Exception constructor with a custom message.
     * 
     * @param message exception message
     */
    public InvalidFormException(final String message) {
        super(message);
    }

}
