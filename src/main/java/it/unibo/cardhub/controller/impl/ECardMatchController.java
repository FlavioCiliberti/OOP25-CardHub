package it.unibo.cardhub.controller.impl;

import it.unibo.cardhub.controller.api.MatchController;
import it.unibo.cardhub.controller.api.Navigator;
import it.unibo.cardhub.model.domain.api.MatchState;
import it.unibo.cardhub.model.logic.api.MatchLogic;
import it.unibo.cardhub.view.api.ScoredMatchView;
import it.unibo.cardhub.view.impl.ScoredMatchViewImpl;

/**
 * Implementation of {@link MatchController} for the ECard full game.
 */
public class ECardMatchController extends AbstractMatchController<ScoredMatchView> {

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
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'onEndTurn'");
    }

}
