package it.unibo.cardhub.view.impl;

import it.unibo.cardhub.controller.api.MatchController;
import it.unibo.cardhub.model.domain.api.Card;
import it.unibo.cardhub.model.logic.api.PlayerEnum;
import it.unibo.cardhub.view.api.PlayfieldPanel;
import it.unibo.cardhub.view.components.CHButton;
import it.unibo.cardhub.view.components.CHLabel;
import it.unibo.cardhub.view.components.CHPanel;
import it.unibo.cardhub.view.components.CHStyles;
import it.unibo.cardhub.view.util.ImageResolver;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

/**
 * Implementation of PlayfieldPanel.
 */
final class PlayfieldPanelImpl extends CHPanel implements PlayfieldPanel {

    private static final long serialVersionUID = 1L;
    private final PlayfieldAreaPanel bottomArea;
    private final PlayfieldAreaPanel topArea;
    private final List<PlayfieldAreaPanel> playfieldAreas;

    private final DiscardPileAreaPanel playerOneDiscardPileArea;
    private final DiscardPileAreaPanel playerTwoDiscardPileArea;

    /**
     * Constructs a new playfield panel.
     * 
     * @param controller the controller that can provide the players
     */
    PlayfieldPanelImpl(final MatchController controller) {
        super(new BorderLayout());
        Objects.requireNonNull(controller, "no such controller");

        this.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(CHStyles.primaryColor()),
            BorderFactory.createEmptyBorder(CHStyles.PADDING_STANDARD, CHStyles.PADDING_STANDARD,
                                            CHStyles.PADDING_STANDARD, CHStyles.PADDING_STANDARD)));

        this.bottomArea = new PlayfieldAreaPanel(controller);
        this.topArea = new PlayfieldAreaPanel(controller);
        this.playfieldAreas = new ArrayList<>();
        this.playfieldAreas.add(bottomArea);
        this.playfieldAreas.add(topArea);
        final JPanel centralArea = new JPanel(
            new GridLayout(2, 1, CHStyles.PADDING_STANDARD, CHStyles.PADDING_STANDARD));
        centralArea.add(this.topArea);
        centralArea.add(this.bottomArea);

        this.playerOneDiscardPileArea = new DiscardPileAreaPanel(BorderLayout.SOUTH);
        this.playerTwoDiscardPileArea = new DiscardPileAreaPanel(BorderLayout.NORTH);

        this.add(centralArea, BorderLayout.CENTER);
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
    public void updatePlayfield(final PlayerEnum player, final List<Card<?>> cards) {
        Objects.requireNonNull(player, "Player cannot be null");
        Objects.requireNonNull(cards, "Cards list cannot be null");

        final PlayfieldAreaPanel area = this.playfieldAreas.get(player.getIndex());
        if (area == null) {
            throw new IllegalArgumentException("Unknown player: " + player);
        }
        area.update(cards);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addToPanel(final JPanel panel, final Object constraints) {
        panel.add(this, constraints);
    }

    private static final class PlayfieldAreaPanel extends CHPanel {
        private static final int ROWS = 1;

        private static final long serialVersionUID = 1L;

        PlayfieldAreaPanel(final MatchController controller) {
            super(new GridLayout(ROWS, controller.getPlayFieldSize(), CHStyles.PADDING_STANDARD, CHStyles.PADDING_STANDARD));

            this.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(CHStyles.primaryColor()),
                BorderFactory.createEmptyBorder(CHStyles.PADDING_STANDARD, CHStyles.PADDING_STANDARD,
                                                CHStyles.PADDING_STANDARD, CHStyles.PADDING_STANDARD)));
        }

        void update(final List<Card<?>> cards) {
            this.removeAll();

            cards.forEach(c -> this.add(new CHLabel(ImageResolver.resolve(c))));

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
            card.ifPresentOrElse(c -> this.pile.setIcon(ImageResolver.resolve(c)), () -> this.pile.setIcon(null));

            this.pile.revalidate();
            this.pile.repaint();
        }
    }
}
