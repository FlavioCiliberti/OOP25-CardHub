package it.unibo.cardhub.view.impl;

import java.awt.FlowLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

import it.unibo.cardhub.controller.api.MatchController;
import it.unibo.cardhub.view.api.MatchView;
import it.unibo.cardhub.view.api.ScoredMatchView;
import it.unibo.cardhub.view.components.CHPanel;

/**
 * implementation of {@link MatchView}.
 */
public final class ScoredMatchViewImpl extends MatchViewImpl implements ScoredMatchView {

    private static final long serialVersionUID = 1L;

    private final JLabel p1ScoreLabel;
    private final JLabel p2ScoreLabel;

    /**
     * default constructor.
     * 
     * @param controller the Match controller
     */
    public ScoredMatchViewImpl(final MatchController controller) {
        super(controller);

        p1ScoreLabel = new JLabel("0");
        p2ScoreLabel = new JLabel("0");

        final JPanel scorePanel = new CHPanel(new FlowLayout(FlowLayout.LEFT));
        scorePanel.add(new JLabel("P1:"));
        scorePanel.add(p1ScoreLabel);
        scorePanel.add(new JLabel("P2:"));
        scorePanel.add(p2ScoreLabel);

        getTopPanel().add(scorePanel);
        getTopPanel().revalidate();
        getTopPanel().repaint();
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
