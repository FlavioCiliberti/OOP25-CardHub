package it.unibo.cardhub.controller.api;

/**
 * A ScreenController that Supplies a method for navigating to a previous screen.
 */
public interface BackNavigableScreen extends ScreenController {
    /**
     * Navigates back to the previous view.
     */
    void goBack();
}
