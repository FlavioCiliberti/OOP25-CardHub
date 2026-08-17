package it.unibo.cardhub.model.api;

/**
 * Provides the domain constraints and default values for creating a new match.
 */
public interface CreateMatchModel {

    /**
     * @return the default hand size proposed to the user
     */
    int getDefaultHandSize();

    /**
     * @return the minimum allowed hand size
     */
    int getMinHandSize();

    /**
     * @return the maximum allowed hand size
     */
    int getMaxHandSize();

    /**
     * @return the default field size proposed to the user
     */
    int getDefaultFieldSize();

    /**
     * @return the minimum allowed field size
     */
    int getMinFieldSize();

    /**
     * @return the maximum allowed field size
     */
    int getMaxFieldSize();

}
