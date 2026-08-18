package it.unibo.cardhub.view.impl;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.List;
import java.util.Optional;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import it.unibo.cardhub.controller.api.MatchController;
import it.unibo.cardhub.model.domain.api.Card;
import it.unibo.cardhub.model.domain.api.Player;
import it.unibo.cardhub.view.api.MatchView;
import it.unibo.cardhub.view.api.PlayerPanel;
import it.unibo.cardhub.view.api.PlayfieldPanel;
import it.unibo.cardhub.view.components.CHButton;
import it.unibo.cardhub.view.components.CHPanel;
import it.unibo.cardhub.view.components.CHStyles;
import it.unibo.cardhub.view.components.ScreenView;

/**
 * Builds the match view.
 */
public final class MatchViewImpl extends ScreenView implements MatchView {
    public static final int WIDTH = 1200;
    public static final int HEIGHT = 960;

    static final int CARD_WIDTH = 66;
    static final int CARD_HEIGHT = 96;

    private static final int HIGHLIGHT_BORDER = 2;

    private static final long serialVersionUID = 1L;

    private final JPanel matchAreaPanel;

    private final PlayerPanel firstPlayerPanel;
    private final PlayerPanel secondPlayerPanel;
    private final PlayfieldPanel playfield;

    private final JButton exitButton;
    private final JButton endTurnButton;
    private final JButton concedeButton;

    private final transient Player player1;
    private final transient Player player2;
    private final transient MatchController controller;

    private transient Optional<JLabel> selectedCard;

    /**
     * Constructor for MatchViewImpl.
     * 
     * @param controller the Match controller
     * @param player1 the first player
     * @param player2 the second player
     */
    public MatchViewImpl(final MatchController controller, final Player player1, final Player player2) {
        this.controller = controller;
        this.player1 = player1;
        this.player2 = player2;

        matchAreaPanel = new CHPanel(CHStyles.tertiaryColor(), new BorderLayout());

        firstPlayerPanel = new PlayerPanelImpl(controller, player1, true);
        secondPlayerPanel = new PlayerPanelImpl(controller, player2, false);
        playfield = new PlayfieldPanelImpl();

        exitButton = new CHButton("Exit");
        endTurnButton = new CHButton("End Turn");
        concedeButton = new CHButton("Concede");
        this.selectedCard = Optional.empty();

        this.manageContentPane();
    }

    //sets up the content pane
    private void manageContentPane() {
        this.setLayout(new BorderLayout());

        final JPanel topPanel = new CHPanel(new FlowLayout(FlowLayout.RIGHT));
        topPanel.setBorder(BorderFactory.createEmptyBorder(CHStyles.PADDING_SMALL, CHStyles.PADDING_NONE,
                                                            CHStyles.PADDING_NONE, CHStyles.PADDING_SMALL));
        topPanel.add(exitButton);
        this.add(topPanel, BorderLayout.NORTH);

        this.managePlayField();

        this.manageBottomPanel();
    }

    //sets up the bottomPanel and adds it to the content pane
    private void manageBottomPanel() {
        final JPanel bottomPanel = new CHPanel(new FlowLayout(FlowLayout.CENTER,
                                                CHStyles.PADDING_LARGE, CHStyles.PADDING_STANDARD));

        bottomPanel.add(endTurnButton);
        endTurnButton.addActionListener(e -> {
            controller.endTurn();
        });

        bottomPanel.add(concedeButton);
        concedeButton.addActionListener(e -> {
            controller.concede();
        });

        this.add(bottomPanel, BorderLayout.SOUTH);
    }

    //sets up playField
    private void managePlayField() {
        this.add(matchAreaPanel, BorderLayout.CENTER);
        matchAreaPanel.setBorder(BorderFactory.createMatteBorder(CHStyles.PADDING_SMALL, CHStyles.PADDING_LARGE,
                                                                CHStyles.PADDING_NONE, CHStyles.PADDING_LARGE,
                                                                CHStyles.secondaryColor()));
        firstPlayerPanel.addToPanel(matchAreaPanel, BorderLayout.SOUTH);
        secondPlayerPanel.addToPanel(matchAreaPanel, BorderLayout.NORTH);
        playfield.addToPanel(matchAreaPanel, BorderLayout.CENTER);
    }

    //selects the panel that belongs to given player
    private PlayerPanel selectPlayerPanel(final Player player) {
        if (player.equals(player1)) {
            return firstPlayerPanel;
        } else if (player.equals(player2)) {
            return secondPlayerPanel;
        }
        throw new IllegalStateException("Player does not exist");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void changeSelectedCard(final JLabel cardLabel) {
        //removes highlight from the last selected card
        this.selectedCard.ifPresent(previous ->
            previous.setBorder(BorderFactory.createEmptyBorder())
        );

        //stores selected card
        cardLabel.setBorder(BorderFactory.createLineBorder(CHStyles.tertiaryColor(), HIGHLIGHT_BORDER));

        //highlights clicked card
        selectedCard = Optional.of(cardLabel);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateHand(final Player player, final List<Card> cards) {
        this.selectPlayerPanel(player).updateHandPanel(cards);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updatePlayfield(final List<Card> cards, final int columns) {
        playfield.updatePlayfield(cards, columns);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateDiscardPile(final Player player, final Optional<Card> topCard) {
        if (player.equals(player1)) {
            playfield.updatePlayerOneDiscardPile(topCard);
        } else if (player.equals(player2)) {
            playfield.updatePlayerTwoDiscardPile(topCard);
        }
        throw new IllegalStateException("Player does not exist");
    }

    @Override
    public void updateDeck(final Player player, final int remainingCards) {
        this.selectPlayerPanel(player).updateDeck();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void showCurrentPlayer(final Player player) {
        JOptionPane.showMessageDialog(
            this,
            "It's" + player.getName() + "'s turn",
            "Turn Start",
            JOptionPane.INFORMATION_MESSAGE
        );
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void showMatchEnded(final Player winner) {
        JOptionPane.showMessageDialog(
            this,
            winner.getName() + " Wins!",
            "Match Over",
            JOptionPane.INFORMATION_MESSAGE
        );
    }
}
