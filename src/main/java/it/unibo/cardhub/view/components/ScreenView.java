package it.unibo.cardhub.view.components;

import java.awt.Color;

import javax.swing.JPanel;

/**
 * Base panel component used as a view container in the application.
 * <p>
 * This class extends {@link JPanel} and provides the default styling
 * configuration for application screens.
 * </p>
 */
public class ScreenView extends JPanel{
    /**
     * Creates a new screen view with the default configuration.
     */
    public ScreenView(){
        customize();
    }

    /**
     * Applies the default customization to this panel.
     * <p>
     * Sets the background color according to the application color palette.
     * </p>
     */
    private void customize(){
        this.setBackground(new Color(CHColor.SECONDARY.getCode()));
    }
}
