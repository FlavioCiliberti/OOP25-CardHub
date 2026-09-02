package it.unibo.cardhub.view.impl;

import javax.swing.JOptionPane;

import it.unibo.cardhub.controller.api.MatchController;
import it.unibo.cardhub.view.api.ECardMatchView;

/**
 * An extension of {@ScoredMatchViewImpl} with result pop-up.
 */
public class ECardMatchViewImpl extends ScoredMatchViewImpl implements ECardMatchView {

    private static final long serialVersionUID = 1L;

    /**
     * Default contructor.
     * 
     * @param controller the Match controller
     */
    public ECardMatchViewImpl(final MatchController controller) {
        super(controller);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void showComparisonResult(final String message) {
        this.showPopup(message, "Confrontation", JOptionPane.INFORMATION_MESSAGE);
    }

}
