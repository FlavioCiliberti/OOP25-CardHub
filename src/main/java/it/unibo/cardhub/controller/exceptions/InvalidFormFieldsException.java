package it.unibo.cardhub.controller.exceptions;

/**
 * Exception for invalid form fields.
 */
public class InvalidFormFieldsException extends Exception {
    private static final long serialVersionUID = 42L;

    /**
     * Default exception constructor.
     */
    public InvalidFormFieldsException() {
        super("Invalid form: At least one invalid field.");
    }

    /**
     * Exception constructor with a custom message.
     * 
     * @param message exception message
     */
    public InvalidFormFieldsException(final String message) {
        super(message);
    }

}
