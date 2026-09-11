package it.unibo.cardhub.view.api;

/**
 * Represents th view for the create match screen.
 */
@FunctionalInterface
public interface CreateMatchView {
    /**
     * Signals that the creating match form has invalid fields.
     * 
     * @param message the message explaining the invalid field. 
     */
    void showInvalidForm(String message);
}
