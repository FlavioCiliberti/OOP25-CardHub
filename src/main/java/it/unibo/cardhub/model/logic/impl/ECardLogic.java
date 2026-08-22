package it.unibo.cardhub.model.logic.impl;

import it.unibo.cardhub.model.domain.api.Card;
import it.unibo.cardhub.model.domain.api.MatchState;
import it.unibo.cardhub.model.domain.attributes.ECardEnum;
import it.unibo.cardhub.model.logic.api.CardAction;
import it.unibo.cardhub.model.logic.api.ComparisonWinner;
import it.unibo.cardhub.model.logic.api.PlayerEnum;

class ECardLogic extends AbstractMatchLogic {
    private static final int NORMAL_WIN_POINTS = 1;
    private static final int SLAVE_WIN_POINTS = 3;

    ECardLogic() {
        super(CardAction.TO_PILE, CardAction.TO_PILE);
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

        switch (firstCardType) {
            case SLAVE:
                switch (secondCardType) {
                    case CITIZEN:
                        matchState.getPlayer(PlayerEnum.PLAYER_TWO).addPoints(NORMAL_WIN_POINTS);
                        return ComparisonWinner.PLAYER_2;
                    case EMPEROR:
                        matchState.getPlayer(PlayerEnum.PLAYER_ONE).addPoints(SLAVE_WIN_POINTS);
                        return ComparisonWinner.PLAYER_1;
                    default:
                        return ComparisonWinner.TIE;
                }
            case CITIZEN:
                switch (secondCardType) {
                    case SLAVE:
                        matchState.getPlayer(PlayerEnum.PLAYER_ONE).addPoints(NORMAL_WIN_POINTS);
                        return ComparisonWinner.PLAYER_1;
                    case EMPEROR:
                        matchState.getPlayer(PlayerEnum.PLAYER_TWO).addPoints(NORMAL_WIN_POINTS);
                        return ComparisonWinner.PLAYER_2;
                    default:
                        return ComparisonWinner.TIE;
                }
            case EMPEROR:
                switch (secondCardType) {
                    case SLAVE:
                        matchState.getPlayer(PlayerEnum.PLAYER_TWO).addPoints(SLAVE_WIN_POINTS);
                        return ComparisonWinner.PLAYER_2;
                    case CITIZEN:
                        matchState.getPlayer(PlayerEnum.PLAYER_ONE).addPoints(NORMAL_WIN_POINTS);
                        return ComparisonWinner.PLAYER_1;
                    default:
                        return ComparisonWinner.TIE;
                }
        }

        throw new IllegalStateException("ECard values error");
    }

    private void executeCardActions(final Card<?> firstPlayerCard, final Card<?> secondPlayerCard,
                                    final MatchState matchState) {
        matchState.getPlayfield().removeCard(firstPlayerCard);
        matchState.getPlayer(PlayerEnum.PLAYER_ONE).putInPile(firstPlayerCard);

        matchState.getPlayfield().removeCard(secondPlayerCard);
        matchState.getPlayer(PlayerEnum.PLAYER_TWO).putInPile(secondPlayerCard);
    }
}
