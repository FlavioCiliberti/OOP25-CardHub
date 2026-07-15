package it.unibo.cardhub.view.components;

import java.awt.Color;

import javax.swing.JButton;

/**
 * Custom button component used throughout the application.
 * 
 * <p>
 * This class extends {@link JButton} and applies the application's
 * default button style, including background color, foreground color,
 * and focus behavior.
 * </p>
 */
public class CHButton extends JButton {

    /**
     * Creates a new custom button with the specified text.
     *
     * @param text the text displayed on the button
     */
    public CHButton(final String text) {
        super(text);
        customize();
    }

    /**
     * Applies the default styling configuration to this button.
     * 
     * <p>
     * The customization includes setting the background color using
     * {@link CHColor#PRIMARY}, the foreground color using
     * {@link CHColor#SECONDARY}, and disabling the focus border.
     * </p>
     */
    private void customize() {
        this.setBackground(new Color(CHColor.PRIMARY.getCode()));
        this.setForeground(new Color(CHColor.SECONDARY.getCode()));
        this.setFocusable(false);
    }
}
