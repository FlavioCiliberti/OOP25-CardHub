package it.unibo.cardhub.view.components;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;

/**
 * Custom title component used throughout the application.
 * 
 * <p>
 * This class extends {@link JLabel} and provides a predefined style for
 * displaying titles, including custom font and color settings.
 * </p>
 */
public class CHTitle extends JLabel {
    private static final int FONT_SIZE = 24;

    /**
     * Creates a new custom title label with the specified text.
     *
     * @param text the text displayed by the title
     */
    public CHTitle(final String text) {
        super(text);
        customize();
    }

    /**
     * Applies the default customization to this title.
     * 
     * <p>
     * Sets the foreground color using {@link CHColor#PRIMARY} and configures
     * a bold SansSerif font with size 24.
     * </p>
     */
    private void customize() {
        this.setForeground(new Color(CHColor.PRIMARY.getCode()));
        this.setFont(new Font("SansSerif", Font.BOLD, FONT_SIZE));
    }
}
