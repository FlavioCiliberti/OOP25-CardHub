package it.unibo.cardhub.model.logic.impl;

import java.util.EnumMap;
import java.util.Map;

import it.unibo.cardhub.model.domain.api.Card;
import it.unibo.cardhub.model.domain.api.MatchState;
import it.unibo.cardhub.model.domain.attributes.ECardEnum;
import it.unibo.cardhub.model.logic.api.CardAction;
import it.unibo.cardhub.model.logic.api.ComparisonWinner;
import it.unibo.cardhub.model.logic.api.PlayerEnum;
import it.unibo.cardhub.model.logic.api.PointTracker;

class ECardLogic extends AbstractMatchLogic implements PointTracker {
    private static final int NORMAL_WIN_POINTS = 1;
    private static final int SLAVE_WIN_POINTS = 3;

    private final Map<PlayerEnum, Integer> playerPoints;

    ECardLogic() {
        super(CardAction.TO_PILE, CardAction.TO_PILE);

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
                                        final MatchState matchState) {
        final ECardEnum firstCardType = ECardEnum.fromValue(firstPlayerCard.value());
        final ECardEnum secondCardType = ECardEnum.fromValue(secondPlayerCard.value());

        this.executeCardActions(firstPlayerCard, secondPlayerCard, matchState);

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

    private void executeCardActions(final Card<?> firstPlayerCard, final Card<?> secondPlayerCard,
                                    final MatchState matchState) {
        matchState.getPlayfield().removeCard(firstPlayerCard);
        matchState.getPlayer(PlayerEnum.PLAYER_ONE).putInPile(firstPlayerCard);

        matchState.getPlayfield().removeCard(secondPlayerCard);
        matchState.getPlayer(PlayerEnum.PLAYER_TWO).putInPile(secondPlayerCard);
    }

    private void addPoints(final PlayerEnum player, final int value) {
        playerPoints.put(player, playerPoints.get(player) + value);
    }

    @Override
    public int getPoints(final PlayerEnum player) {
        return playerPoints.get(player);
    }

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
}
