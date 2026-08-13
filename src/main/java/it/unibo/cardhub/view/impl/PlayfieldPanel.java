package it.unibo.cardhub.view.impl;

import it.unibo.cardhub.model.domain.api.Card;

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

/**
 * Represents the playfield area of the match view, including the central playfield and the discard piles for both players.
 */
public final class PlayfieldPanel extends CHPanel {

    private static final long serialVersionUID = 1L;
    private final PlayfieldAreaPanel playfieldArea;
    private final DiscardPileAreaPanel playerDiscardPileArea;
    private final DiscardPileAreaPanel opponentDiscardPileArea;

    /**
     * Constructs a new playfield panel.
     */
    public PlayfieldPanel() {
        super(new BorderLayout());

        this.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(CHStyles.primaryColor()),
            BorderFactory.createEmptyBorder(CHStyles.PADDING_STANDARD, CHStyles.PADDING_STANDARD, CHStyles.PADDING_STANDARD, CHStyles.PADDING_STANDARD)));

        this.playfieldArea = new PlayfieldAreaPanel();
        this.playerDiscardPileArea = new DiscardPileAreaPanel(BorderLayout.SOUTH);
        this.opponentDiscardPileArea = new DiscardPileAreaPanel(BorderLayout.NORTH);

        this.add(this.playfieldArea, BorderLayout.CENTER);
        this.add(this.playerDiscardPileArea, BorderLayout.EAST);
        this.add(this.opponentDiscardPileArea, BorderLayout.WEST);
    }

    /**
     * Updates the player's discard pile with the specified card.
     *
     * @param card the card to display in the discard pile
     */
    public void updatePlayerDiscardPile(final Optional<Card> card) {
        this.playerDiscardPileArea.updateCard(Objects.requireNonNull(card, "Card cannot be null"));
    }

    /**
     * Updates the opponent's discard pile with the specified card.
     *
     * @param card the card to display in the discard pile
     */
    public void updateOpponentDiscardPile(final Optional<Card> card) {
        this.opponentDiscardPileArea.updateCard(Objects.requireNonNull(card, "Card cannot be null"));
    }

    /**
     * Updates the playfield with the specified list of cards.
     *
     * @param cards the list of cards to display on the playfield
     * @param rows the number of rows in the playfield
     * @param columns the number of columns in the playfield
     */
    public void updatePlayfield(final List<Card> cards, final int rows, final int columns) {
        this.playfieldArea.update(Objects.requireNonNull(cards, "Cards list cannot be null"), rows, columns);
    }

    private static final class PlayfieldAreaPanel extends CHPanel {

        private static final long serialVersionUID = 1L;

        PlayfieldAreaPanel() {
            super();

            this.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(CHStyles.primaryColor()),
                BorderFactory.createEmptyBorder(CHStyles.PADDING_STANDARD, CHStyles.PADDING_STANDARD, CHStyles.PADDING_STANDARD, CHStyles.PADDING_STANDARD)));
        }

        void update(final List<Card> cards, final int rows, final int columns) {
            if (rows <= 0 || columns <= 0) {
                throw new IllegalArgumentException("Rows and columns must be positive integers");
            }

            this.setLayout(new GridLayout(rows, columns, CHStyles.PADDING_STANDARD, CHStyles.PADDING_STANDARD));
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
                BorderFactory.createEmptyBorder(CHStyles.PADDING_STANDARD, CHStyles.PADDING_STANDARD, CHStyles.PADDING_STANDARD, CHStyles.PADDING_STANDARD)));

            this.pile = new CHLabel();
            this.reshuffle = new CHButton("Reshuffle into deck");

            this.add(reshuffle, position);
            this.add(pile, BorderLayout.CENTER);
        }

        void updateCard(final Optional<Card> card) {
            card.ifPresentOrElse(c -> this.pile.setIcon(new ImageIcon(c.imagePath())), () -> this.pile.setIcon(null));

            this.pile.revalidate();
            this.pile.repaint();
        }
    }
}
