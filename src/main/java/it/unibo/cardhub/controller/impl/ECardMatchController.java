package it.unibo.cardhub.controller.impl;

import java.util.List;

import it.unibo.cardhub.controller.api.MatchController;
import it.unibo.cardhub.controller.api.Navigator;
import it.unibo.cardhub.model.domain.api.Card;
import it.unibo.cardhub.model.domain.api.MatchState;
import it.unibo.cardhub.model.domain.api.PlayerEnum;
import it.unibo.cardhub.model.logic.impl.ECardLogic;
import it.unibo.cardhub.view.api.ECardMatchView;
import it.unibo.cardhub.view.impl.ECardMatchViewImpl;

/**
 * Implementation of {@link MatchController} for the ECard full game.
 */
public class ECardMatchController extends AbstractMatchController<ECardMatchView, ECardLogic> {

    /**
     * Constructor for the controller.
     * 
     * @param state match state
     * @param logic match logic
     * @param navigator screen navigator
     */
    public ECardMatchController(final MatchState state, final ECardLogic logic, final Navigator navigator) {
        super(state, logic, navigator);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected ECardMatchView createView() {
        return new ECardMatchViewImpl(this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onEndTurn() {
        final PlayerEnum currentPlayer = getTurnPlayer();

        if (currentPlayer == PlayerEnum.PLAYER_ONE) {
            showHiddenPlayfield(currentPlayer);
        } else {
            compareAndShowResult();
        }

        if (isMatchOver()) {
            endMatch();
        } else {
            getLogic().changeTurn();
        }
    }

    private void showHiddenPlayfield(final PlayerEnum player) {
        getMatchView().updateHiddenPlayfield(player, getPlayerPlayedCards(player).size());
    }

    private void compareAndShowResult() {
        final List<Card<?>> playerOneCards = getPlayerPlayedCards(PlayerEnum.PLAYER_ONE);
        final List<Card<?>> playerTwoCards = getPlayerPlayedCards(PlayerEnum.PLAYER_TWO);

        if (playerOneCards.isEmpty() || playerTwoCards.isEmpty()) {
            throw new IllegalStateException("Both players must have played at least one card before comparing.");
        }

        getMatchView().updatePlayfield(PlayerEnum.PLAYER_ONE, playerOneCards);

        freeze(); // time given for players to see their played cards

        super.compareCard(playerOneCards.getLast(), playerTwoCards.getLast());

        showUpdatedScore();
    }

    private boolean isMatchOver() {
        return getState().getPlayer(PlayerEnum.PLAYER_ONE).getHand().isEmpty()
            && getState().getPlayer(PlayerEnum.PLAYER_TWO).getHand().isEmpty();
    }

    private void endMatch() {
        getState().endMatch(getLogic().getWinningPlayer());
        getMatchView().showMatchEnded(getState().getWinner().get());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void playCard(final PlayerEnum owner, final Card<?> card) {
        super.playCard(owner, card);
        endTurn();
    }

    private List<Card<?>> getPlayerPlayedCards(final PlayerEnum player) {
        return getState().getPlayfield().getCards(player);
    }

    private void freeze() {
        getMatchView().showComparisonResult("Watch the result!");
    }

    private int getScore(final PlayerEnum player) {
        return getLogic().getPoints(player);
    }

    private void showUpdatedScore() {
        getMatchView().updateScore(getScore(PlayerEnum.PLAYER_ONE), 
                                    getScore(PlayerEnum.PLAYER_TWO));
    }
}
