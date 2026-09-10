package it.unibo.cardhub.model.domain.api;

import it.unibo.cardhub.model.logic.api.ComparisonWinner;

/**
 * An enumeration for player1 and player2.
 */
public enum PlayerEnum {
    PLAYER_ONE,
    PLAYER_TWO;

    /**
     * Converts PlayerEnum to the corresponding ComparisonWinner.
     * 
     * @return the corresponding ComparisonWinner
     */
    public ComparisonWinner toComparisonWinner() {
        return switch (this) {
            case PLAYER_ONE -> ComparisonWinner.PLAYER_1;
            case PLAYER_TWO -> ComparisonWinner.PLAYER_2;
        };
    }
}
