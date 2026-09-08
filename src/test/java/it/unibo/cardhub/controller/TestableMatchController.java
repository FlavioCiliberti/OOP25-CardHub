package it.unibo.cardhub.controller;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

import it.unibo.cardhub.controller.api.Navigator;
import it.unibo.cardhub.controller.impl.AbstractMatchController;
import it.unibo.cardhub.model.domain.api.MatchState;
import it.unibo.cardhub.model.logic.api.MatchLogic;
import it.unibo.cardhub.view.api.MatchView;

/**
 * Minimal concrete controller used only to test {@link
 * AbstractMatchController} without depending on the real, Swing/popup-based
 * match view.
 */
public final class TestableMatchController extends AbstractMatchController<MatchView, MatchLogic> {

    private SilentMatchView view;

    /**
     * Contstructor for this test controller.
     * 
     * @param state the match state
     * @param logic tha match logic
     * @param navigator the navigator
     */
    public TestableMatchController(final MatchState state, final MatchLogic logic, final Navigator navigator) {
        super(state, logic, navigator);
    }

    @Override
    protected MatchView createView() {
        view = new SilentMatchView();
        return view;
    }

    @Override
    protected void onEndTurn() {
        getLogic().changeTurn();
    }

    /**
     * Getter for the view created by this controller.
     *
     * @return the view
     */
    @SuppressFBWarnings(value = "EI", 
                    justification = "Exposes the test view instance only for test purposes")
    public SilentMatchView getView() {
        return view;
    }
}
