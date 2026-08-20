package it.unibo.cardhub.model.domain.attributes;

/**
 * An Enum for the E-Card types.
 */
public enum ECardEnum {
    SLAVE(0),
    CITIZEN(1),
    EMPEROR(2);

    private final int value;

    /**
     * Creates an E-Card type.
     * 
     * @param value the value corresponding to the type
     */
    ECardEnum(final int value) {
        this.value = value;
    }

    /**
     * Returns an E-Card type from a value.
     * 
     * @param value the value
     * @return the type corresponding to the given value
     */
    public static ECardEnum fromValue(final int value) {
        for (final ECardEnum type : values()) {
            if (type.value == value) {
                return type;
            }
        }
        throw new IllegalArgumentException("Unknown Value");
    }
}
