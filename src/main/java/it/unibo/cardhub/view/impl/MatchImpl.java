package it.unibo.cardhub.view.impl;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.Timer;

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

    private final JButton exitButton;
    private final JButton endTurnButton;
    private final JButton concedeButton;
    private final int turnMaxTimer;
    private int timeRemaining;
    
    public MatchImpl() {
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
        topPanel.setBorder(BorderFactory.createEmptyBorder(PADDING_SMALL, PADDING_NONE, PADDING_NONE, PADDING_STANDARD));
        topPanel.add(exitButton);
        this.add(topPanel, BorderLayout.NORTH);

        this.manageCenterPanel(new CHPanel(CHStyles.primaryColor(), new BorderLayout()));

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
