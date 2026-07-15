package it.unibo.cardhub.view.components;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JTextField;

/**
 * Custom text field component used throughout the application.
 * <p>
 * This class extends {@link JTextField} and applies the default application
 * styling, including colors, preferred size, border, and internal padding.
 * </p>
 */
public class CHTextField extends JTextField{

    /**
     * Creates a new custom text field with the default configuration.
     */
    public CHTextField(){
        customize();
    }

    /**
     * Applies the default customization to this text field.
     * <p>
     * Sets the background color using {@link CHColor#SECONDARY}, the text
     * and caret colors using {@link CHColor#TERTIARY}, defines the preferred
     * size, and adds a custom border with internal padding.
     * </p>
     */
    private void customize(){
        this.setBackground(new Color(CHColor.SECONDARY.getCode()));
        this.setForeground(new Color(CHColor.TERTIARY.getCode()));
        this.setCaretColor(new Color(CHColor.TERTIARY.getCode()));
        this.setPreferredSize(new Dimension(100, 26));
        this.setBorder(BorderFactory.createLineBorder(new Color(CHColor.TERTIARY.getCode())));
        this.setBorder(BorderFactory.createCompoundBorder(
            this.getBorder(),
            BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
    }
}
