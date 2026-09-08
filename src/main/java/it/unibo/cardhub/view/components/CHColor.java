package it.unibo.cardhub.view.components;

/**
 * Enumeration containing the color palette used by the CardHub application.
 * 
 * <p>
 * Each color is represented by its hexadecimal RGB code, which can be used
 * to create {@link java.awt.Color} instances.
 * </p>
 */
public enum CHColor {

    /**
     * Primary application color.
     */
    PRIMARY(0xba8441),
    // PRIMARY(0xd49d4c),
    // PRIMARY(0x38b03d),

    /**
     * Secondary application color.
     */
    SECONDARY(0x000000),

    /**
     * Secondary application color.
     */
    TERTIARY(0xffffff);

    private final int colorCode;

    /**
     * Creates a new color entry with the specified RGB hexadecimal code.
     *
     * @param colorCode the RGB color code represented as an integer
     */
    CHColor(final int colorCode) {
        this.colorCode = colorCode;
    } 

    /**
     * Returns the RGB code associated with this color.
     *
     * @return the hexadecimal RGB color code
     */
    public int getCode() {
        return colorCode;
    }
}
