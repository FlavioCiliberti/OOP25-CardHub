package it.unibo.cardhub.view.components;

import java.awt.Color;

/**
 * GUI styles shared throughout the application. 
 */
public final class CHStyles {
    public static final int PADDING_LARGE = 20;
    public static final int PADDING_STANDARD = 10;
    public static final int PADDING_SMALL = 4;
    public static final int PADDING_NONE = 0;

    // Suppresses default constructor, ensuring non-instantiability.
    private CHStyles() { }

    /**
     * Creates the primary color.
     * 
     * @return a {@link Color}
     */
    public static Color primaryColor() {
        return new Color(CHColor.PRIMARY.getCode());
    }

    /**
     * Creates the secondary color.
     * 
     * @return a {@link Color}
     */
    public static Color secondaryColor() {
        return new Color(CHColor.SECONDARY.getCode());
    }

    /**
     * Creates the tertiary color.
     * 
     * @return a {@link Color}
     */
    public static Color tertiaryColor() {
        return new Color(CHColor.TERTIARY.getCode());
    }
}
