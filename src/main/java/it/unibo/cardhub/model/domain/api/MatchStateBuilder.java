package it.unibo.cardhub.model.domain.api;

public interface MatchStateBuilder {

    /**
     * Sets the max hand size.
     * 
     * @param maxHandSizeValue max hand size
     * @return the Builder
     */
    MatchStateBuilder maxHandSize(final int maxHandSizeValue);

    /**
     * Sets the starting hand size.
     * 
     * @param startingHandSizeValue starting hand size
     * @return the Builder
     */
    MatchStateBuilder startingHandSize(final int startingHandSizeValue);

    /**
     * Sets the playfield size.
     * 
     * @param playfieldSizeValue playfield size
     * @return the Builder
     */
    MatchStateBuilder playfieldSize(final int playfieldSizeValue);

    /**
     * Builds the Match state.
     * 
     * @return the desired MatchState
     */
    MatchState build();
}
