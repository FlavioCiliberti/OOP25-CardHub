package it.unibo.cardhub.view.impl;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;

import it.unibo.cardhub.view.components.CHButton;
import it.unibo.cardhub.view.components.CHLabel;
import it.unibo.cardhub.view.components.CHPanel;
import it.unibo.cardhub.view.components.CHStyles;
import it.unibo.cardhub.view.components.ScreenView;

public final class MatchImpl extends ScreenView {
    private static final int PADDING_LARGE = 20;
    private static final int PADDING_STANDARD = 10;
    private static final int PADDING_SMALL = 4;
    private static final int PADDING_NONE = 0;

    private final JPanel firstPlayerPanel;
    private final JPanel firstPlayerHandPanel;

    private final JLabel firstPlayerDeckLabel;
    private final JButton firstPlayerShuffleDeckButton;
    private final JButton exitButton;
    private final JButton endTurnButton;
    private final JButton concedeButton;
    private final int turnMaxTimer;
    private int timeRemaining;
    
    public MatchImpl() {
        firstPlayerPanel = new CHPanel(CHStyles.primaryColor());
        firstPlayerHandPanel = new CHPanel(new FlowLayout(FlowLayout.LEFT, PADDING_STANDARD, PADDING_NONE));

        firstPlayerDeckLabel = new CHLabel("Deck");
        firstPlayerShuffleDeckButton = new CHButton("Shuffle");
        exitButton = new CHButton("Exit");
        endTurnButton = new CHButton("End Turn");
        concedeButton = new CHButton("Concede");
        turnMaxTimer = 10; //temporary for testing purposes
        timeRemaining = turnMaxTimer;

        this.manageContentPane();
    }

    //sets up the content pane
    private void manageContentPane() {
        this.setLayout(new BorderLayout());

        JPanel topPanel = new CHPanel(new FlowLayout(FlowLayout.RIGHT));
        topPanel.setBorder(BorderFactory.createEmptyBorder(PADDING_SMALL, PADDING_NONE, PADDING_NONE, PADDING_SMALL));
        topPanel.add(exitButton);
        this.add(topPanel, BorderLayout.NORTH);

        this.manageCenterPanel(new CHPanel(new BorderLayout()));

        this.manageBottomPanel();
    }

    //sets up the bottomPanel and adds it to the content pane
    private void manageBottomPanel() {
        JPanel bottomPanel = new CHPanel(new FlowLayout(FlowLayout.CENTER, PADDING_LARGE, PADDING_STANDARD));

        JLabel timerLabel = new CHLabel("Time Remaining: " + String.valueOf(timeRemaining), SwingConstants.CENTER);
        this.startTimer(timerLabel);
        bottomPanel.add(timerLabel);

        bottomPanel.add(endTurnButton);

        bottomPanel.add(concedeButton);

        this.add(bottomPanel, BorderLayout.SOUTH);
    }

    //sets up the center panel
    private void manageCenterPanel(JPanel centerPanel) {
        this.add(centerPanel, BorderLayout.CENTER);
        centerPanel.setBorder(BorderFactory.createMatteBorder(PADDING_SMALL, PADDING_LARGE,
                                                                PADDING_NONE, PADDING_LARGE,
                                                                CHStyles.secondaryColor()));
        this.managePlayerPanel(firstPlayerPanel, firstPlayerHandPanel, firstPlayerShuffleDeckButton, new CHLabel("Giocatore 1", SwingConstants.CENTER), firstPlayerDeckLabel);
        centerPanel.add(firstPlayerPanel, BorderLayout.SOUTH);
    }

    private void managePlayerPanel(JPanel playerPanel, JPanel handPanel, JButton shuffleButton, JLabel nameLabel, JLabel deckLabel) {
        final int maxHandSize = 5;
        final int cardValue = 10;
        final String cardName = "Exodia il Proibito";

        playerPanel.setLayout(new BoxLayout(playerPanel, BoxLayout.Y_AXIS));
        playerPanel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED, CHStyles.secondaryColor(), CHStyles.primaryColor()));

        JPanel firstRow = new CHPanel(new BorderLayout());
        firstRow.setBorder(BorderFactory.createEmptyBorder(PADDING_STANDARD, PADDING_NONE, PADDING_NONE, 13));
        JLabel cardInfoLabel = new CHLabel("", SwingConstants.CENTER);
        firstRow.add(cardInfoLabel, BorderLayout.CENTER);

        for (int i = 0; i < maxHandSize; i++) {
            JLabel card = new CHLabel("");
            ImageIcon image = new ImageIcon(getClass().getResource("/it/unibo/cardhub/io/Exodia.png"));
            card.setPreferredSize(new Dimension(66, 96));
            card.setIcon(image);
            card.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    cardInfoLabel.setText("<html>" + cardName + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Value: " + String.valueOf(cardValue) + "<br>" + "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vestibulum consequat mi quis pretium semper. Proin luctus orci ac neque venenatis, quis commodo dolor posuere. Curabitur dignissim sapien quis cursus egestas. Donec blandit auctor arcu, nec pellentesque eros molestie eget. In consectetur aliquam hendrerit. Sed cursus mauris vitae ligula pellentesque, non pellentesque urna aliquet. Fusce placerat mauris enim, nec rutrum purus semper vel. Praesent tincidunt neque eu pellentesque pharetra. Fusce pellentesque est orci" + "</html>");
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    cardInfoLabel.setText("");
                }
            });
            handPanel.add(card);
            System.out.println(image.getIconWidth());
            System.out.println(image.getIconHeight());
            System.out.println(getClass().getResource("/it/unibo/cardhub/io/Exodia.png"));
        }

        JPanel handPanelWrapper = new CHPanel(new GridBagLayout());
        handPanelWrapper.add(handPanel);
        firstRow.add(handPanelWrapper, BorderLayout.WEST);

        ImageIcon deckIcon = new ImageIcon(getClass().getResource("/it/unibo/cardhub/view/Back.png"));
        JLabel deck = new JLabel(new ImageIcon(deckIcon.getImage().getScaledInstance(66, 100, Image.SCALE_SMOOTH)));
        deck.setPreferredSize(new Dimension(66, 100));

        firstRow.add(deck, BorderLayout.EAST);

        JPanel secondRow = new CHPanel(new BorderLayout());
        secondRow.setBorder(BorderFactory.createEmptyBorder(PADDING_STANDARD, PADDING_NONE, PADDING_NONE, PADDING_STANDARD));
        secondRow.add(nameLabel, BorderLayout.CENTER);
        secondRow.add(shuffleButton, BorderLayout.EAST);

        playerPanel.add(firstRow);
        playerPanel.add(secondRow);
    }

    private void startTimer(JLabel timerLabel) {
        Timer timer = new Timer(1000, e -> {
            timeRemaining--;
            timerLabel.setText("Time Remaining: " + String.valueOf(timeRemaining));

            if (timeRemaining <= 0) {
                ((Timer) e.getSource()).stop();
            }
        });

        timer.start();
    }
}
