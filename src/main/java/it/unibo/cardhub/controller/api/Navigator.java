package it.unibo.cardhub.controller.api;

import javax.swing.JComponent;

import it.unibo.cardhub.controller.ScreenId;

/**
 * Interface for the application's navigator, responsible
 * for managing navigation between the different
 * screen views.
 */
public interface Navigator {

    /**
     * Getter for the container of the application views.
     * 
     * @return the container of all the views.
     */
    JComponent getRootView();

    /**
     * Navigates to a given screen view.
     * 
     * @param id the screen identifier
     * @param view the view component to navigate to
     */
    void show(ScreenId id, JComponent view);

    /**
     * Navigates to a specific screen view: the Home screen.
     */
    void goHome();
}
