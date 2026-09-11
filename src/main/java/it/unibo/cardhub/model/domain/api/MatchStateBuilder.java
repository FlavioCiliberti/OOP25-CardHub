package it.unibo.cardhub.model.domain.api;

/**
 * Builds a MatchState.
 */
public interface MatchStateBuilder {

    /**
     * Sets the max hand size.
     * 
     * @param maxHandSizeValue max hand size
     * @return the Builder
     */
    MatchStateBuilder maxHandSize(int maxHandSizeValue);

    /**
     * Sets the starting hand size.
     * 
     * @param startingHandSizeValue starting hand size
     * @return the Builder
     */
    MatchStateBuilder startingHandSize(int startingHandSizeValue);

    /**
     * Sets the playfield size.
     * 
     * @param playfieldSizeValue playfield size
     * @return the Builder
     */
    MatchStateBuilder playfieldSize(int playfieldSizeValue);

    /**
     * Builds the Match state.
     * 
     * @return the desired MatchState
     */
    MatchState build();
}
