package it.unibo.cardhub.view.impl;

import java.awt.FlowLayout;

import javax.swing.JPanel;

import it.unibo.cardhub.controller.api.MatchController;
import it.unibo.cardhub.view.api.MatchView;
import it.unibo.cardhub.view.api.ScoredMatchView;
import it.unibo.cardhub.view.components.CHLabel;
import it.unibo.cardhub.view.components.CHPanel;

/**
 * implementation of {@link MatchView}.
 */
public class ScoredMatchViewImpl extends MatchViewImpl implements ScoredMatchView {

    private static final long serialVersionUID = 1L;

    private final CHLabel p1ScoreLabel;
    private final CHLabel p2ScoreLabel;

    /**
     * default constructor.
     * 
     * @param controller the Match controller
     */
    public ScoredMatchViewImpl(final MatchController controller) {
        super(controller);

        p1ScoreLabel = new CHLabel("0");
        p2ScoreLabel = new CHLabel("0");

        final JPanel scorePanel = new CHPanel(new FlowLayout(FlowLayout.LEFT));
        scorePanel.add(new CHLabel("P1:"));
        scorePanel.add(p1ScoreLabel);
        scorePanel.add(new CHLabel("P2:"));
        scorePanel.add(p2ScoreLabel);

        super.getTopPanel().add(scorePanel);
        super.getTopPanel().revalidate();
        super.getTopPanel().repaint();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateScore(final int p1Score, final int p2Score) {
        p1ScoreLabel.setText(String.valueOf(p1Score));
        p2ScoreLabel.setText(String.valueOf(p2Score));
    }
}
