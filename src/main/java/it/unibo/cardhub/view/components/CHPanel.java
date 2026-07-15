package it.unibo.cardhub.view.components;

import java.awt.Color;
import javax.swing.JPanel;

/**
 * Custom panel component used throughout the application.
 * 
 * <p>
 * This class extends {@link JPanel} and applies the default application
 * styling to panels.
 * </p>
 */
public final class CHPanel extends JPanel {
    private static final long serialVersionUID = 1L;

    /**
     * Creates a new custom panel with the default configuration.
     */
    public CHPanel() {
        customize();
    }

    /**
     * Applies the default customization to this panel.
     * 
     * <p>
     * Sets the background color according to the application color palette.
     * </p>
     */
    private void customize() {
        this.setBackground(new Color(CHColor.SECONDARY.getCode()));
    }
}
