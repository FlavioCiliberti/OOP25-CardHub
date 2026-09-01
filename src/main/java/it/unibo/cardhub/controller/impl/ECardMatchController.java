package it.unibo.cardhub.controller.impl;

import java.util.List;

import it.unibo.cardhub.controller.api.MatchController;
import it.unibo.cardhub.controller.api.Navigator;
import it.unibo.cardhub.model.domain.api.Card;
import it.unibo.cardhub.model.domain.api.MatchState;
import it.unibo.cardhub.model.domain.api.PlayerEnum;
import it.unibo.cardhub.model.logic.api.MatchLogic;
import it.unibo.cardhub.view.api.ScoredMatchView;
import it.unibo.cardhub.view.impl.ScoredMatchViewImpl;

/**
 * Implementation of {@link MatchController} for the ECard full game.
 */
public class ECardMatchController extends AbstractMatchController<ScoredMatchView> {
    private static final int FREEZED_TIME_IN_MS = 5000;

    /**
     * Constructor for the controller.
     * 
     * @param state match state
     * @param logic match logic
     * @param navigator screen navigator
     */
    public ECardMatchController(final MatchState state, final MatchLogic logic, final Navigator navigator) {
        super(state, logic, navigator);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected ScoredMatchView createView() {
        return new ScoredMatchViewImpl(this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onEndTurn() {
        if (getTurnPlayer() == PlayerEnum.PLAYER_ONE) {
            getMatchView().updateHiddenPlayfield(getTurnPlayer(), 
                                getPlayerPlayedCards(PlayerEnum.PLAYER_ONE).size());
        } else {
            getMatchView().updatePlayfield(PlayerEnum.PLAYER_ONE, getPlayerPlayedCards(PlayerEnum.PLAYER_ONE));

            freeze(); // time given for players to see their played cards

            super.compareCard(getPlayerPlayedCards(PlayerEnum.PLAYER_ONE).getLast(), 
                                getPlayerPlayedCards(PlayerEnum.PLAYER_TWO).getLast());
            // getMatchView().updateScore(1,2);
        }

        getLogic().changeTurn();
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
        try {
            Thread.sleep(FREEZED_TIME_IN_MS);
        } catch (final InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

}
