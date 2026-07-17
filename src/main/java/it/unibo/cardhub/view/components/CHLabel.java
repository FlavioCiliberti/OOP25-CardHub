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
public class CHLabel extends JLabel {
    private static final long serialVersionUID = 1L;

    /**
     * Creates a new custom label with the specified text and the default color.
     * Sets the foreground color to the tertiary application color defined by
     * {@link CHColor#TERTIARY}.
     * 
     * @param text the text displayed by the label
     */
    public CHLabel(final String text) {
        this(text, CHStyles.tertiaryColor());
    }

    /**
     * Creates a new custom label with the specified text and color.
     *
     * @param text the text displayed by the label
     * @param color the foreground color of the label
     */
    public CHLabel(final String text, final Color color) {
        super(text);
        super.setForeground(color);
    }

    /**
     * Creates a new custom label with the specified text and horizontal alignment.
     * Sets the foreground color to the tertiary application color defined by
     * {@link CHColor#TERTIARY}.
     *
     * @param text the text displayed by the label
     * @param horizontalAlignment the horizontal alignment constant defined in
     *     ({@link JLabel} (e.g. {@link JLabel#LEFT},
     *     {@link JLabel#CENTER}, {@link JLabel#RIGHT})
     */
    public CHLabel(final String text, final int horizontalAlignment) {
        this(text);
        super.setHorizontalAlignment(horizontalAlignment);
    }

    /**
     * Creates a new custom label with the specified text, color and horizontal alignment.
     *
     * @param text the text displayed by the label
     * @param color the foreground color of the label
     * @param horizontalAlignment the horizontal alignment constant defined in
     *     ({@link JLabel} (e.g. {@link JLabel#LEFT},
     *     {@link JLabel#CENTER}, {@link JLabel#RIGHT})
     */
    public CHLabel(final String text, final Color color, final int horizontalAlignment) {
        this(text, color);
        super.setHorizontalAlignment(horizontalAlignment);
    }
}
