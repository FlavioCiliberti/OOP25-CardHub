package it.unibo.cardhub.view.impl;

import it.unibo.cardhub.model.domain.api.Card;

import it.unibo.cardhub.view.components.CHButton;
import it.unibo.cardhub.view.components.CHPanel;
import it.unibo.cardhub.view.components.CHStyles;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.List;
import java.util.Optional;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;

/**
 * Represents the playfield area of the match view, including the central playfield and the discard piles for both players.
 */
public final class PlayfieldPanel extends CHPanel {

    private static final long serialVersionUID = 1L;
    private final PlayfieldAreaPanel playfieldArea;
    private final PlayerDiscardPileAreaPanel playerDiscardPileArea;
    private final OpponentDiscardPileAreaPanel opponentDiscardPileArea;

    /**
     * Constructs a new playfield panel.
     */
    public PlayfieldPanel() {
        super(new BorderLayout());

        this.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(CHStyles.primaryColor()),
            BorderFactory.createEmptyBorder(10, 10, 10, 10)));

        this.playfieldArea = new PlayfieldAreaPanel();
        this.playerDiscardPileArea = new PlayerDiscardPileAreaPanel();
        this.opponentDiscardPileArea = new OpponentDiscardPileAreaPanel();

        this.add(this.playfieldArea, BorderLayout.CENTER);
        this.add(this.playerDiscardPileArea, BorderLayout.EAST);
        this.add(this.opponentDiscardPileArea, BorderLayout.WEST);
    }

    /**
     * Updates the player's discard pile with the specified card.
     *
     * @param card the card to display in the discard pile
     */
    public final void updatePlayerDiscardPile(final Optional<Card> card) {
        this.playerDiscardPileArea.updateCard(card);
    }

    /**
     * Updates the opponent's discard pile with the specified card.
     *
     * @param card the card to display in the discard pile
     */
    public final void updateOpponentDiscardPile(final Optional<Card> card) {
        this.opponentDiscardPileArea.updateCard(card);
    }

    /**
     * Updates the playfield with the specified list of cards.
     *
     * @param cards the list of cards to display on the playfield
     */
    public void updatePlayfield(final List<Card> cards) {
        this.playfieldArea.update(cards);
    }

    private static class PlayfieldAreaPanel extends CHPanel {

        public PlayfieldAreaPanel() {
            super(new FlowLayout());

            this.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(CHStyles.primaryColor()),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)));
        }

        public void update(final List<Card> cards) {
            this.removeAll();

            cards.forEach(c -> this.add(new CHButton(new ImageIcon(c.imagePath()))));

            this.revalidate();
            this.repaint();
        }
    }

    private abstract static class AbstractDiscardPileAreaPanel extends CHPanel {

        private final CHButton pile;
        private final CHButton reshuffle;

        public AbstractDiscardPileAreaPanel(final String position) {
            super(new BorderLayout());

            this.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(CHStyles.primaryColor()),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)));

            this.pile = new CHButton();
            this.reshuffle = new CHButton("Reshuffle into deck");

            this.add(reshuffle, position);
            this.add(pile, BorderLayout.CENTER);
        }

        public void updateCard(final Optional<Card> card) {
            this.pile.setIcon(card.map(c -> new ImageIcon(c.imagePath())).orElse(null));
            this.pile.setText(card.isPresent() ? "" : "Empty");

            this.pile.revalidate();
            this.pile.repaint();
        }
    }

    private static class PlayerDiscardPileAreaPanel extends AbstractDiscardPileAreaPanel {
        public PlayerDiscardPileAreaPanel() {
            super(BorderLayout.SOUTH);
        }
    }

    private static class OpponentDiscardPileAreaPanel extends AbstractDiscardPileAreaPanel {
        public OpponentDiscardPileAreaPanel() {
            super(BorderLayout.NORTH);
        }
    }
}

