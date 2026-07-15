package it.unibo.cardhub.view.components;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JTextArea;

/**
 * Custom text area component used throughout the application.
 * <p>
 * This class extends {@link JTextArea} and applies the default application
 * styling, including background color, text color, caret color, and padding.
 * </p>
 */
public class CHTextArea extends JTextArea {

    /**
     * Creates a new custom text area with the default configuration.
     */
    public CHTextArea(){
        customize();
    }

    /**
     * Applies the default customization to this text area.
     * <p>
     * Sets the background color using {@link CHColor#SECONDARY}, the text
     * and caret colors using {@link CHColor#TERTIARY}, and adds a custom
     * border with internal padding.
     * </p>
     */
    private void customize(){
        this.setBackground(new Color(CHColor.SECONDARY.getCode()));
        this.setForeground(new Color(CHColor.TERTIARY.getCode()));
        this.setCaretColor(new Color(CHColor.TERTIARY.getCode()));
        this.setBorder(BorderFactory.createLineBorder(new Color(CHColor.TERTIARY.getCode())));
        this.setBorder(BorderFactory.createCompoundBorder(
            this.getBorder(),
            BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
    }

}
