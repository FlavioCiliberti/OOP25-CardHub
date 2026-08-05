package it.unibo.cardhub.view.components;

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
    private static final long serialVersionUID = 1L;

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
        super.setBackground(CHStyles.primaryColor());
        super.setForeground(CHStyles.secondaryColor());
        super.setFocusable(false);
    }
}
