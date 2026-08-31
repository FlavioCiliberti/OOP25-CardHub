package it.unibo.cardhub.controller.impl;

import it.unibo.cardhub.controller.api.MatchController;
import it.unibo.cardhub.controller.api.Navigator;
import it.unibo.cardhub.model.domain.api.MatchState;
import it.unibo.cardhub.model.logic.api.MatchLogic;

/**
 * implementation of {@link MatchController}.
 */
public class MatchControllerImpl extends AbstractMatchController {

    /**
     * Constructor for the controller.
     * 
     * @param state match state
     * @param logic match logic
     * @param navigator screen navigator
     */
    public MatchControllerImpl(final MatchState state, final MatchLogic logic, final Navigator navigator) {
        super(state, logic, navigator);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void endTurn() {
        getMatchView().updateHiddenHand(getTurnPlayer(), 
                            getState().getPlayer(getTurnPlayer()).getHand().size());
        getLogic().changeTurn();
        getMatchView().showCurrentPlayer(getTurnPlayer());
    }

}
