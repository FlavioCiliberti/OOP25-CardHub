package it.unibo.cardhub.model.logic.impl;

import java.util.EnumMap;
import java.util.Map;
import java.util.Objects;

import it.unibo.cardhub.model.domain.api.Card;
import it.unibo.cardhub.model.domain.api.MatchState;
import it.unibo.cardhub.model.domain.api.PlayerEnum;
import it.unibo.cardhub.model.domain.attributes.ECardEnum;
import it.unibo.cardhub.model.logic.api.CardAction;
import it.unibo.cardhub.model.logic.api.ComparisonWinner;
import it.unibo.cardhub.model.logic.api.PointTracker;

/**
 * Match logic for an E-Card game.
 */
public class ECardLogic extends AbstractMatchLogic implements PointTracker {
    private static final int NORMAL_WIN_POINTS = 1;
    private static final int SLAVE_WIN_POINTS = 3;

    private final Map<PlayerEnum, Integer> playerPoints;

    /**
     * ECardLogic constructor.
     */
    public ECardLogic() {
        super(CardAction.TO_PILE, CardAction.TO_PILE, false);

        playerPoints = new EnumMap<>(PlayerEnum.class);
        for (final PlayerEnum player : PlayerEnum.values()) {
            playerPoints.put(player, 0);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ComparisonWinner compareCard(final Card<?> firstPlayerCard, final Card<?> secondPlayerCard,
                                        final MatchState state) {
        Objects.requireNonNull(firstPlayerCard, "card can't be null");
        Objects.requireNonNull(secondPlayerCard, "card can't be null");
        Objects.requireNonNull(state, "Match State can't be null");

        final ECardEnum firstCardType = ECardEnum.fromValue(firstPlayerCard.value());
        final ECardEnum secondCardType = ECardEnum.fromValue(secondPlayerCard.value());

        this.executeCardActions(firstPlayerCard, secondPlayerCard, state);

        if (firstCardType == secondCardType) {
            return ComparisonWinner.TIE;
        }

        if (firstCardType.beats(secondCardType)) {
            this.addPoints(PlayerEnum.PLAYER_ONE,
                            firstCardType == ECardEnum.SLAVE ? SLAVE_WIN_POINTS : NORMAL_WIN_POINTS);
            return ComparisonWinner.PLAYER_1;
        }

        this.addPoints(PlayerEnum.PLAYER_TWO,
                        secondCardType == ECardEnum.SLAVE ? SLAVE_WIN_POINTS : NORMAL_WIN_POINTS);
        return ComparisonWinner.PLAYER_2;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getPoints(final PlayerEnum player) {
        Objects.requireNonNull(player, "player can't be null");

        return playerPoints.get(player);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ComparisonWinner getWinningPlayer() {
        final int firstPlayerPoints = playerPoints.get(PlayerEnum.PLAYER_ONE);
        final int secondPlayerPoints = playerPoints.get(PlayerEnum.PLAYER_TWO);

        if (firstPlayerPoints > secondPlayerPoints) {
            return ComparisonWinner.PLAYER_1;
        } else if (firstPlayerPoints < secondPlayerPoints) {
            return ComparisonWinner.PLAYER_2;
        } else {
            return ComparisonWinner.TIE;
        }
    }

    private void executeCardActions(final Card<?> firstPlayerCard, final Card<?> secondPlayerCard,
                                    final MatchState state) {
        state.moveCardFromFieldToPile(firstPlayerCard, PlayerEnum.PLAYER_ONE);
        state.moveCardFromFieldToPile(secondPlayerCard, PlayerEnum.PLAYER_TWO);
    }

    private void addPoints(final PlayerEnum player, final int value) {
        Objects.requireNonNull(player, "player can't be null");

        playerPoints.put(player, playerPoints.get(player) + value);
    }
}
