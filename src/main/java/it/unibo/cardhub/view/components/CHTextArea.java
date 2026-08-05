package it.unibo.cardhub.view.components;

import javax.swing.BorderFactory;
import javax.swing.JTextArea;

/**
 * Custom text area component used throughout the application.
 * 
 * <p>
 * This class extends {@link JTextArea} and applies the default application
 * styling, including background color, text color, caret color, and padding.
 * </p>
 */
public class CHTextArea extends JTextArea {
    private static final long serialVersionUID = 1L;

    private static final int PADDING_LEFT = 10;
    private static final int PADDING_RIGHT = 10;
    private static final int PADDING_TOP = 5;
    private static final int PADDING_BOTTOM = 5;

    /**
     * Creates a new custom text area with the default configuration.
     */
    public CHTextArea() {
        customize();
    }

    /**
     * Applies the default customization to this text area.
     * 
     * <p>
     * Sets the background color using {@link CHColor#SECONDARY}, the text
     * and caret colors using {@link CHColor#TERTIARY}, and adds a custom
     * border with internal padding.
     * </p>
     */
    private void customize() {
        super.setBackground(CHStyles.secondaryColor());
        super.setForeground(CHStyles.tertiaryColor());
        super.setCaretColor(CHStyles.tertiaryColor());
        super.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(CHStyles.tertiaryColor()),
            BorderFactory.createEmptyBorder(PADDING_TOP, PADDING_LEFT, PADDING_BOTTOM, PADDING_RIGHT)
        ));
    }
}

