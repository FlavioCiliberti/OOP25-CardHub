package it.unibo.cardhub.model.logic.api;

import java.util.Optional;

import it.unibo.cardhub.model.domain.api.PlayerEnum;

/**
 * An enumeration for the winner of a card comparison.
 */
public enum ComparisonWinner {
    PLAYER_1,
    PLAYER_2,
    TIE;

    /**
     * Converts ComparisonWinner to PlayerEnum if possible.
     * 
     * @return an optional containing the winner, empty in case of a tie
     */
    public Optional<PlayerEnum> toPlayerEnum() {
        return switch (this) {
            case PLAYER_1 -> Optional.of(PlayerEnum.PLAYER_ONE);
            case PLAYER_2 -> Optional.of(PlayerEnum.PLAYER_TWO);
            case TIE -> Optional.empty();
        };
    }
}
