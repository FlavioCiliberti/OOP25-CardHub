package it.unibo.cardhub.view.components;

import java.awt.Color;

import javax.swing.JLabel;

/**
 * Custom label component used throughout the application.
 *
 * <p>
 * This class extends {@link JLabel} and provides predefined constructors
 * with default styling or custom color and alignment configuration.
 * </p>
 */
public final class CHLabel extends JLabel {
    private static final long serialVersionUID = 1L;

    /**
     * Creates a new custom label with the specified text.
     *
     * @param text the text displayed by the label
     */
    public CHLabel(final String text) {
        super(text);
        customize();
    }

    /**
     * Creates a new custom label with the specified color and horizontal alignment.
     *
     * @param color the foreground color of the label
     * @param horizontalAlignment the horizontal alignment constant defined in
     *     ({@link JLabel} (e.g. {@link JLabel#LEFT},
     *     {@link JLabel#CENTER}, {@link JLabel#RIGHT})
     */
    public CHLabel(final Color color, final int horizontalAlignment) {
        this.setForeground(color);
        this.setHorizontalAlignment(horizontalAlignment);
    }

    /**
     * Applies the default customization to this label.
     *
     * <p>
     * Sets the foreground color to the tertiary application color defined by
     * {@link CHColor#TERTIARY}.
     * </p>
     */
    private void customize() {
        this.setForeground(new Color(CHColor.TERTIARY.getCode()));
    }
}
