package it.unibo.cardhub.controller.impl;

import it.unibo.cardhub.controller.api.MatchController;
import it.unibo.cardhub.controller.api.Navigator;
import it.unibo.cardhub.model.domain.api.MatchState;
import it.unibo.cardhub.model.logic.api.MatchLogic;
import it.unibo.cardhub.view.api.MatchView;
import it.unibo.cardhub.view.impl.MatchViewImpl;

/**
 * Base implementation of {@link MatchController}.
 */
public class MatchControllerImpl extends AbstractMatchController<MatchView, MatchLogic> {

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
    protected MatchView createView() {
        return new MatchViewImpl(this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onEndTurn() {
        getLogic().changeTurn();
    }

}
