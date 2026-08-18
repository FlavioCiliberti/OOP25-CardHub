package it.unibo.cardhub.view.impl;

import it.unibo.cardhub.model.domain.api.Card;
import it.unibo.cardhub.view.api.PlayfieldPanel;
import it.unibo.cardhub.view.components.CHButton;
import it.unibo.cardhub.view.components.CHLabel;
import it.unibo.cardhub.view.components.CHPanel;
import it.unibo.cardhub.view.components.CHStyles;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 * Implementation of PlayfieldPanel.
 */
final class PlayfieldPanelImpl extends CHPanel implements PlayfieldPanel {

    private static final long serialVersionUID = 1L;
    private final PlayfieldAreaPanel playfieldArea;
    private final DiscardPileAreaPanel playerOneDiscardPileArea;
    private final DiscardPileAreaPanel playerTwoDiscardPileArea;

    /**
     * Constructs a new playfield panel.
     */
    PlayfieldPanelImpl() {
        super(new BorderLayout());

        this.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(CHStyles.primaryColor()),
            BorderFactory.createEmptyBorder(CHStyles.PADDING_STANDARD, CHStyles.PADDING_STANDARD,
                                            CHStyles.PADDING_STANDARD, CHStyles.PADDING_STANDARD)));

        this.playfieldArea = new PlayfieldAreaPanel();
        this.playerOneDiscardPileArea = new DiscardPileAreaPanel(BorderLayout.SOUTH);
        this.playerTwoDiscardPileArea = new DiscardPileAreaPanel(BorderLayout.NORTH);

        this.add(this.playfieldArea, BorderLayout.CENTER);
        this.add(this.playerOneDiscardPileArea, BorderLayout.EAST);
        this.add(this.playerTwoDiscardPileArea, BorderLayout.WEST);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updatePlayerOneDiscardPile(final Optional<Card<?>> card) {
        this.playerOneDiscardPileArea.updateCard(Objects.requireNonNull(card, "Card cannot be null"));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updatePlayerTwoDiscardPile(final Optional<Card<?>> card) {
        this.playerTwoDiscardPileArea.updateCard(Objects.requireNonNull(card, "Card cannot be null"));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updatePlayfield(final List<Card<?>> cards, final int columns) {
        this.playfieldArea.update(Objects.requireNonNull(cards, "Cards list cannot be null"), columns);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addToPanel(final JPanel panel, final Object constraints) {
        panel.add(this, constraints);
    }

    private static final class PlayfieldAreaPanel extends CHPanel {
        private static final int ROWS = 2;

        private static final long serialVersionUID = 1L;

        PlayfieldAreaPanel() {
            super();

            this.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(CHStyles.primaryColor()),
                BorderFactory.createEmptyBorder(CHStyles.PADDING_STANDARD, CHStyles.PADDING_STANDARD,
                                                CHStyles.PADDING_STANDARD, CHStyles.PADDING_STANDARD)));
        }

        void update(final List<Card<?>> cards, final int columns) {
            if (columns <= 0) {
                throw new IllegalArgumentException("Rows and columns must be positive integers");
            }

            this.setLayout(new GridLayout(ROWS, columns, CHStyles.PADDING_STANDARD, CHStyles.PADDING_STANDARD));
            this.removeAll();

            cards.forEach(c -> this.add(new CHLabel(new ImageIcon(c.imagePath()))));

            this.revalidate();
            this.repaint();
        }
    }

    private static final class DiscardPileAreaPanel extends CHPanel {

        private static final long serialVersionUID = 1L;
        private final CHLabel pile;
        private final CHButton reshuffle;

        DiscardPileAreaPanel(final String position) {
            super(new BorderLayout());

            if (!Objects.equals(position, BorderLayout.NORTH) && !Objects.equals(position, BorderLayout.SOUTH)) {
                throw new IllegalArgumentException("Position must be either BorderLayout.NORTH or BorderLayout.SOUTH");
            }

            this.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(CHStyles.primaryColor()),
                BorderFactory.createEmptyBorder(CHStyles.PADDING_STANDARD, CHStyles.PADDING_STANDARD,
                                                CHStyles.PADDING_STANDARD, CHStyles.PADDING_STANDARD)));

            this.pile = new CHLabel();
            this.reshuffle = new CHButton("Reshuffle into deck");

            this.add(reshuffle, position);
            this.add(pile, BorderLayout.CENTER);
        }

        void updateCard(final Optional<Card<?>> card) {
            card.ifPresentOrElse(c -> this.pile.setIcon(new ImageIcon(c.imagePath())), () -> this.pile.setIcon(null));

            this.pile.revalidate();
            this.pile.repaint();
        }
    }
}
