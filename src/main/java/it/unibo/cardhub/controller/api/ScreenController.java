package it.unibo.cardhub.controller.api;

import javax.swing.JComponent;

/**
 * Contract for every screen controller.
 */
@FunctionalInterface
public interface ScreenController {
    /**
     * Screen view getter.
     * 
     * @return the screen view
     */
    JComponent getView(); 
}
