package it.unibo.cardhub.view.impl;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Optional;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.border.EtchedBorder;

import it.unibo.cardhub.view.components.CHButton;
import it.unibo.cardhub.view.components.CHLabel;
import it.unibo.cardhub.view.components.CHPanel;
import it.unibo.cardhub.view.components.CHStyles;
import it.unibo.cardhub.view.components.ScreenView;

/**
 * Builds the match view.
 */
public final class MatchViewImpl extends ScreenView {
    private static final int PADDING_LARGE = 20;
    private static final int PADDING_STANDARD = 10;
    private static final int PADDING_SMALL = 4;
    private static final int PADDING_NONE = 0;
    private static final int PADDING_ROW = 13;
    private static final int CARD_WIDTH = 66;
    private static final int CARD_HEIGHT = 96;

    private static final long serialVersionUID = 1L;

    //test fields
    private static final int DECK_SIZE = 40;

    private final JPanel firstPlayerPanel;
    private final JPanel firstPlayerHandPanel;
    private final JPanel playField;
    private final JPanel secondPlayerPanel;
    private final JPanel secondPlayerHandPanel;

    private final JLabel firstPlayerDeckLabel;
    private final JLabel secondPlayerDeckLabel;
    private final JButton firstPlayerShuffleDeckButton;
    private final JButton secondPlayerShuffleDeckButton;
    private final JButton exitButton;
    private final JButton endTurnButton;
    private final JButton concedeButton;
    private final int turnMaxTimer;
    private transient Optional<JLabel> selectedCard;
    private int timeRemaining;

    /**
     * Constructor for MatchViewImpl.
     */
    public MatchViewImpl() {
        firstPlayerPanel = new CHPanel();
        firstPlayerHandPanel = new CHPanel(new FlowLayout(FlowLayout.LEFT, PADDING_STANDARD, PADDING_NONE));
        secondPlayerPanel = new CHPanel();
        secondPlayerHandPanel = new CHPanel(new FlowLayout(FlowLayout.RIGHT, PADDING_STANDARD, PADDING_NONE));
        playField = new CHPanel(CHStyles.tertiaryColor(), new BorderLayout());

        firstPlayerDeckLabel = new CHLabel(String.valueOf(DECK_SIZE), CHStyles.primaryColor());
        secondPlayerDeckLabel = new CHLabel(String.valueOf(DECK_SIZE), CHStyles.primaryColor());
        firstPlayerShuffleDeckButton = new CHButton("Shuffle");
        secondPlayerShuffleDeckButton = new CHButton("Shuffle");
        exitButton = new CHButton("Exit");
        endTurnButton = new CHButton("End Turn");
        concedeButton = new CHButton("Concede");
        turnMaxTimer = 10; //temporary for testing purposes
        timeRemaining = turnMaxTimer;
        this.selectedCard = Optional.empty();

        this.manageContentPane();
    }

    //sets up the content pane
    private void manageContentPane() {
        this.setLayout(new BorderLayout());

        final JPanel topPanel = new CHPanel(new FlowLayout(FlowLayout.RIGHT));
        topPanel.setBorder(BorderFactory.createEmptyBorder(PADDING_SMALL, PADDING_NONE, PADDING_NONE, PADDING_SMALL));
        topPanel.add(exitButton);
        this.add(topPanel, BorderLayout.NORTH);

        this.managePlayField();

        this.manageBottomPanel();
    }

    //sets up the bottomPanel and adds it to the content pane
    private void manageBottomPanel() {
        final JPanel bottomPanel = new CHPanel(new FlowLayout(FlowLayout.CENTER, PADDING_LARGE, PADDING_STANDARD));

        final JLabel timerLabel = new CHLabel("Time Remaining: " + timeRemaining, SwingConstants.CENTER);
        this.startTimer(timerLabel);
        bottomPanel.add(timerLabel);

        bottomPanel.add(endTurnButton);

        bottomPanel.add(concedeButton);

        this.add(bottomPanel, BorderLayout.SOUTH);
    }

    //sets up playField
    private void managePlayField() {
        final JLabel firstPlayerLabel = new CHLabel("Giocatore 1", SwingConstants.CENTER);
        final JLabel secondPlayerLabel = new CHLabel("Giocatore 2", SwingConstants.CENTER);

        this.add(playField, BorderLayout.CENTER);
        playField.setBorder(BorderFactory.createMatteBorder(PADDING_SMALL, PADDING_LARGE,
                                                                PADDING_NONE, PADDING_LARGE,
                                                                CHStyles.secondaryColor()));
        this.managePlayerPanel(firstPlayerPanel, firstPlayerHandPanel,
                                firstPlayerShuffleDeckButton, firstPlayerLabel,
                                firstPlayerDeckLabel, true);
        playField.add(firstPlayerPanel, BorderLayout.SOUTH);
        this.managePlayerPanel(secondPlayerPanel, secondPlayerHandPanel,
                                secondPlayerShuffleDeckButton, secondPlayerLabel,
                                secondPlayerDeckLabel, false);
        playField.add(secondPlayerPanel, BorderLayout.NORTH);
        playField.add(new PlayfieldPanel(), BorderLayout.CENTER);
    }

    private void managePlayerPanel(final JPanel playerPanel, final JPanel handPanel,
                                    final JButton shuffleButton, final JLabel nameLabel,
                                    final JLabel deckLabel, final boolean mirrored) {

        final int maxHandSize = 5;
        final int cardValue = 10;
        final String cardName = "Exodia il Proibito";

        final JPanel firstRow = new CHPanel(new BorderLayout(PADDING_STANDARD, 0));
        final JPanel secondRow = new CHPanel(new BorderLayout());
        final JPanel handPanelWrapper = new CHPanel(new GridBagLayout());

        final JLabel cardInfoLabel = new CHLabel("", SwingConstants.CENTER);
        final ImageIcon deckIcon = new ImageIcon(getClass().getResource("/it/unibo/cardhub/view/Back.png"));

        playerPanel.setLayout(new BoxLayout(playerPanel, BoxLayout.Y_AXIS));
        playerPanel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED,
                                                                CHStyles.secondaryColor(), CHStyles.primaryColor()));

        firstRow.setBorder(BorderFactory.createEmptyBorder(PADDING_STANDARD, PADDING_ROW,
                                                            PADDING_STANDARD, PADDING_ROW));
        firstRow.add(cardInfoLabel, BorderLayout.CENTER);

        for (int i = 0; i < maxHandSize; i++) {
            final JLabel cardLabel = new CHLabel("");
            final ImageIcon image = new ImageIcon(getClass().getResource("/it/unibo/cardhub/io/Exodia.png"));
            cardLabel.setPreferredSize(new Dimension(CARD_WIDTH, CARD_HEIGHT));
            cardLabel.setIcon(image);
            cardLabel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(final MouseEvent e) {
                    cardInfoLabel.setText("<html>"
                                            + cardName
                                            + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Value: "
                                            + cardValue + "<br>"
                                            + "Lorem ipsum dolor sit amet" + "</html>");
                }

                @Override
                public void mouseExited(final MouseEvent e) {
                    cardInfoLabel.setText("");
                }

                @Override
                public void mouseClicked(final MouseEvent e) {
                    //removes highlight from the last selected card
                    selectedCard.ifPresent(previous ->
                        previous.setBorder(BorderFactory.createEmptyBorder())
                    );

                    //stores selected card
                    cardLabel.setBorder(BorderFactory.createLineBorder(CHStyles.tertiaryColor(), 2));

                    //highlights clicked card
                    selectedCard = Optional.of(cardLabel);
                }
            });
            handPanel.add(cardLabel);
        }

        handPanelWrapper.add(handPanel);
        firstRow.add(handPanelWrapper, mirrored ? BorderLayout.WEST : BorderLayout.EAST);

        deckLabel.setIcon(new ImageIcon(deckIcon.getImage().getScaledInstance(CARD_WIDTH, CARD_HEIGHT,
                                                                                Image.SCALE_SMOOTH)));
        deckLabel.setPreferredSize(new Dimension(CARD_WIDTH, CARD_HEIGHT));
        deckLabel.setHorizontalTextPosition(SwingConstants.CENTER);
        deckLabel.setVerticalTextPosition(SwingConstants.CENTER);
        firstRow.add(deckLabel, mirrored ? BorderLayout.EAST : BorderLayout.WEST);

        secondRow.setBorder(BorderFactory.createEmptyBorder(PADDING_STANDARD, PADDING_STANDARD,
                                                            PADDING_STANDARD, PADDING_STANDARD));
        secondRow.add(nameLabel, BorderLayout.CENTER);
        secondRow.add(shuffleButton, mirrored ? BorderLayout.EAST : BorderLayout.WEST);

        playerPanel.add(mirrored ? firstRow : secondRow);
        playerPanel.add(mirrored ? secondRow : firstRow);
    }

    private void startTimer(final JLabel timerLabel) {
        final Timer timer = new Timer(1000, e -> {
            timeRemaining--;
            timerLabel.setText("Time Remaining: " + timeRemaining);

            if (timeRemaining <= 0) {
                ((Timer) e.getSource()).stop();
            }
        });

        timer.start();
    }
}
