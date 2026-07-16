package it.unibo.cardhub.view.components;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JTextField;

/**
 * Custom text field component used throughout the application.
 * 
 * <p>
 * This class extends {@link JTextField} and applies the default application
 * styling, including colors, preferred size, border, and internal padding.
 * </p>
 */
public class CHTextField extends JTextField {
    private static final long serialVersionUID = 1L;

    private static final int PADDING_LEFT = 10;
    private static final int PADDING_RIGHT = 10;
    private static final int PADDING_TOP = 5;
    private static final int PADDING_BOTTOM = 5;
    private static final int HEIGHT = 26;
    private static final int WIDTH = 100;

    /**
     * Creates a new custom text field with the default configuration.
     */
    public CHTextField() {
        customize();
    }

    /**
     * Applies the default customization to this text field.
     * 
     * <p>
     * Sets the background color using {@link CHColor#SECONDARY}, the text
     * and caret colors using {@link CHColor#TERTIARY}, defines the preferred
     * size, and adds a custom border with internal padding.
     * </p>
     */
    private void customize() {
        super.setBackground(new Color(CHColor.SECONDARY.getCode()));
        super.setForeground(new Color(CHColor.TERTIARY.getCode()));
        super.setCaretColor(new Color(CHColor.TERTIARY.getCode()));
        super.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        super.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(CHColor.TERTIARY.getCode())),
            BorderFactory.createEmptyBorder(PADDING_TOP, PADDING_LEFT, PADDING_BOTTOM, PADDING_RIGHT)
        ));
    }
}
