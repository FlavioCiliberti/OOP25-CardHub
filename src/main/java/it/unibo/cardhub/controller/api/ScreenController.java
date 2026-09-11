package it.unibo.cardhub.controller.api;

/**
 * Contract for every screen controller.
 */
@FunctionalInterface
public interface ScreenController {
    /**
     * Shows the screen view associated to this controller.
     */
    void showScreen();
}
