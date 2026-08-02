package it.unibo.cardhub.view.creatematch;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;

import it.unibo.cardhub.controller.api.CreateMatchController;
import it.unibo.cardhub.model.domain.exceptions.EmptyFieldException;
import it.unibo.cardhub.view.components.CHButton;
import it.unibo.cardhub.view.components.CHColor;
import it.unibo.cardhub.view.components.CHLabel;
import it.unibo.cardhub.view.components.CHPanel;
import it.unibo.cardhub.view.components.CHStyles;
import it.unibo.cardhub.view.components.CHTextField;
import it.unibo.cardhub.view.components.CHTitle;
import it.unibo.cardhub.view.components.ScreenView;

public class CreateMatchImpl extends ScreenView{
    public final int WIDTH = 470;
    public final int HEIGHT = 710;

    //logic constraints
    private final static int MIN_HAND_SIZE = 1;
    private final static int MIN_FIELD_SIZE = 1;
    private final static int MAX_HAND_SIZE = 7;
    private final static int MAX_FIELD_SIZE = 6;

    //GUI padding and panel size
    private final static int PADDING_STANDARD = 10;
    private final static int PADDING_NONE = 0;
    private final static int TOP_PANEL_PADDING = 7;
    private final static int CENTER_PANEL_Y_PADDING = 15;
    private final static int CENTER_PANEL_X_PADDING = 65;
    private final static int CENTER_PANEL_PADDING_BETWEEN = 25;
    private final static int PADDING_SMALL = 4;
    private final static double TOP_PANEL_RATIO = 0.06;
    private final static double PLAYERS_PANEL_RATIO = 0.35;
    private final static double GAME_MODES_PANEL_RATIO = 0.15;
    private final static double SETTINGS_PANEL_RATIO = 0.29;

    //Panels
    private final JPanel playersPanel;
    private final JPanel firstPlayerPanel;
    private final JPanel secondPlayerPanel;
    private final JPanel gameModesPanel;
    private final JPanel settingsPanel;

    //Content pane items
    private final JButton back;
    private final JLabel title;
    private final JButton play;

    //playersPanel items
    //TODO JComboBox wants a type, remember to add <Deck> in the final implementation
    private final JLabel playersLabel;
    private final JLabel firstPlayerLabel;
    private final JLabel firstPlayerNameLabel;
    private final JLabel firstPlayerDeckLabel;
    private final JTextField firstPlayerNameField;
    private final JComboBox firstPlayerDeckBox;
    private final JLabel secondPlayerLabel;
    private final JLabel secondPlayerNameLabel;
    private final JLabel secondPlayerDeckLabel;
    private final JTextField secondPlayerNameField;
    private final JComboBox secondPlayerDeckBox;

    //gameModesPanel items
    private final JLabel gameModeLabel;
    private final JRadioButton freePlayRadioButton;
    private final JRadioButton customRulesRadioButton;
    private final JRadioButton fullGameRadioButton;
    private final ButtonGroup gameModesGroup;

    //settingsPanel items
    private final JLabel settingsLabel;
    private final JLabel handSizeLabel;
    private final JSpinner handSizeSpinner;
    private final SpinnerNumberModel handSizeModel;
    private final JLabel fieldSizeLabel;
    private final JSpinner fieldSizeSpinner;
    private final SpinnerNumberModel fieldSizeModel;
    private final JLabel startingHandLabel;
    private final JSpinner startingHandSpinner;
    private final SpinnerNumberModel startingHandModel;
    private final JCheckBox autoDrawCheckBox;
    private final JLabel winnerActionLabel;
    private final JRadioButton winPileRadioButton;
    private final JRadioButton winLoserPileRadioButton;
    private final JRadioButton winNoneRadioButton;
    private final ButtonGroup winnerActionGroup;
    private final JLabel loserActionLabel;
    private final JRadioButton losePileRadioButton;
    private final JRadioButton loseWinnerPileRadioButton;
    private final JRadioButton loseNoneRadioButton;
    private final ButtonGroup loserActionGroup;

    //controller
    CreateMatchController controller;

    public CreateMatchImpl(CreateMatchController controller) {
        playersPanel = new CHPanel();
        firstPlayerPanel = new CHPanel();
        secondPlayerPanel = new CHPanel();
        gameModesPanel = new CHPanel();
        settingsPanel = new CHPanel();

        back = new CHButton("<");
        title = new CHTitle("Create Match");
        play = new CHButton("Play");

        playersLabel = new CHLabel("Players", CHStyles.secondaryColor(), SwingConstants.CENTER);
        firstPlayerLabel = new CHLabel("Player 1", SwingConstants.CENTER);
        firstPlayerNameLabel = new CHLabel("Name");
        firstPlayerDeckLabel = new CHLabel("Deck");
        firstPlayerNameField = new CHTextField();
        firstPlayerDeckBox = new JComboBox<>();
        secondPlayerLabel = new CHLabel("Player 2", SwingConstants.CENTER);
        secondPlayerNameLabel = new CHLabel("Name");
        secondPlayerDeckLabel = new CHLabel("Deck");
        secondPlayerNameField = new CHTextField();
        secondPlayerDeckBox = new JComboBox<>();

        gameModeLabel = new CHLabel("Game Mode", CHStyles.secondaryColor(), SwingConstants.CENTER);
        freePlayRadioButton = new JRadioButton("Free Play");
        customRulesRadioButton = new JRadioButton("Custom Rules");
        fullGameRadioButton = new JRadioButton("Full Game");
        gameModesGroup = new ButtonGroup();

        settingsLabel = new CHLabel("Custom Settings", CHStyles.secondaryColor(), SwingConstants.CENTER);
        handSizeLabel = new CHLabel("Max Hand Size", CHStyles.secondaryColor(), SwingConstants.CENTER);
        handSizeModel = new SpinnerNumberModel(4, MIN_HAND_SIZE, MAX_HAND_SIZE, 1);
        handSizeSpinner = new JSpinner(handSizeModel);
        fieldSizeLabel = new CHLabel("Field Size per Player", CHStyles.secondaryColor(), SwingConstants.CENTER);
        fieldSizeModel = new SpinnerNumberModel(3, MIN_FIELD_SIZE, MAX_FIELD_SIZE, 1);
        fieldSizeSpinner = new JSpinner(fieldSizeModel);
        startingHandLabel = new CHLabel("Starting Hand Size", CHStyles.secondaryColor(), SwingConstants.LEFT);
        //Starting hand size needs to always be lower or equal to max hand size
        startingHandModel = new SpinnerNumberModel(4, MIN_HAND_SIZE, ((Integer) handSizeSpinner.getValue()).intValue(), 1);
        startingHandSpinner = new JSpinner(startingHandModel);
        autoDrawCheckBox = new JCheckBox("Auto Draw on Turn Start");
        winnerActionLabel = new CHLabel("Winner Card Action", CHStyles.secondaryColor(), SwingConstants.CENTER);
        winPileRadioButton = new JRadioButton("To Pile");
        winLoserPileRadioButton = new JRadioButton("To Loser's Pile");
        winNoneRadioButton = new JRadioButton("None");
        winnerActionGroup = new ButtonGroup();
        loserActionLabel = new CHLabel("Loser Card Action", CHStyles.secondaryColor(), SwingConstants.CENTER);
        losePileRadioButton = new JRadioButton("To Pile");
        loseWinnerPileRadioButton = new JRadioButton("To Winner's Pile");
        loseNoneRadioButton = new JRadioButton("None");
        loserActionGroup = new ButtonGroup();

        this.controller = controller;

        this.manageContentPane();
    }

    //sets up the content pane
    private void manageContentPane() {
        this.setLayout(new BorderLayout());

        JPanel topPanel = new CHPanel();
        this.add(topPanel, BorderLayout.NORTH);
        this.manageTopPanel(topPanel);

        JPanel bottomPanel = new CHPanel();
        bottomPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        bottomPanel.add(play);
        play.addActionListener(e -> {
            this.startGame(controller);
        });
        this.add(bottomPanel, BorderLayout.SOUTH);

        JPanel centerPanel = new CHPanel();
        this.add(centerPanel, BorderLayout.CENTER);
        this.manageCenterPanel(centerPanel);
    }

    //sets up topPanel
    private void manageTopPanel(JPanel topPanel) {
        topPanel.setPreferredSize(new Dimension(WIDTH, (int) (HEIGHT * TOP_PANEL_RATIO)));

        topPanel.setLayout(new BorderLayout());

        topPanel.setBorder(BorderFactory.createEmptyBorder(TOP_PANEL_PADDING, TOP_PANEL_PADDING, TOP_PANEL_PADDING, TOP_PANEL_PADDING));

        back.addActionListener(e -> {
            controller.goBack();
        });
        topPanel.add(back, BorderLayout.WEST);

        title.setHorizontalAlignment(SwingConstants.CENTER);
        topPanel.add(title, BorderLayout.CENTER);

        //to center title
        JPanel dummy = new CHPanel();
        dummy.setPreferredSize(back.getPreferredSize());
        dummy.setMaximumSize(back.getPreferredSize());
        topPanel.add(dummy, BorderLayout.EAST);
    }

    //sets up centerPanel
    private void manageCenterPanel(JPanel centerPanel) {
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(CENTER_PANEL_Y_PADDING, CENTER_PANEL_X_PADDING, CENTER_PANEL_Y_PADDING, CENTER_PANEL_X_PADDING));

        centerPanel.add(playersPanel);
        this.managePlayersPanel();

        //encapsulates gameModesPanel for better size management
        JPanel gameModesPanelContainer = new CHPanel();
        gameModesPanelContainer.setLayout(new BorderLayout());
        gameModesPanelContainer.setPreferredSize(new Dimension(WIDTH, (int) (HEIGHT * GAME_MODES_PANEL_RATIO)));
        centerPanel.add(gameModesPanelContainer);
        gameModesPanelContainer.add(gameModesPanel, BorderLayout.CENTER);
        this.manageGameModesPanel();
        
        //encapsulates settingsPanel so that when it's not visible the other panels don't get resized
        JPanel settingsPanelContainer = new CHPanel();
        settingsPanelContainer.setLayout(new BorderLayout());
        settingsPanelContainer.setPreferredSize(new Dimension(WIDTH, (int) (HEIGHT * SETTINGS_PANEL_RATIO)));
        centerPanel.add(settingsPanelContainer);
        settingsPanelContainer.add(settingsPanel, BorderLayout.CENTER);
        this.manageSettingsPanel();
    }

    //sets up playersPanel
    private void managePlayersPanel() {
        playersPanel.setBackground(CHStyles.primaryColor());
        playersPanel.setPreferredSize(new Dimension(WIDTH, (int) (HEIGHT * PLAYERS_PANEL_RATIO)));
        playersPanel.setBorder(BorderFactory.createMatteBorder(PADDING_NONE, PADDING_NONE, CENTER_PANEL_PADDING_BETWEEN, PADDING_NONE, CHStyles.secondaryColor()));
        playersPanel.setLayout(new BorderLayout());

        playersPanel.add(playersLabel, BorderLayout.NORTH);

        //contains both players' panels
        JPanel playersContainer = new CHPanel();
        playersContainer.setLayout(new BoxLayout(playersContainer, BoxLayout.Y_AXIS));
        playersContainer.setBorder(BorderFactory.createEmptyBorder(PADDING_NONE, PADDING_STANDARD, PADDING_STANDARD, PADDING_STANDARD));
        playersContainer.setBackground(CHStyles.primaryColor());
        playersPanel.add(playersContainer, BorderLayout.CENTER);

        //Player 1 Panel
        firstPlayerPanel.setBorder(BorderFactory.createMatteBorder(PADDING_NONE, PADDING_NONE, PADDING_STANDARD, PADDING_NONE, CHStyles.primaryColor()));
        managePlayerPanel(firstPlayerPanel, firstPlayerLabel, firstPlayerNameLabel, firstPlayerDeckLabel, firstPlayerNameField, firstPlayerDeckBox);
        playersContainer.add(firstPlayerPanel);

        //Player 2 Panel
        managePlayerPanel(secondPlayerPanel, secondPlayerLabel, secondPlayerNameLabel, secondPlayerDeckLabel, secondPlayerNameField, secondPlayerDeckBox);
        playersContainer.add(secondPlayerPanel);

    }

    //sets up firstPlayerPanel and secondPlayerPanel
    private void managePlayerPanel(JPanel panel, JLabel title, JLabel nameLabel, JLabel deckLabel, JTextField nameTextField, JComboBox deckBox) {
        panel.setLayout(new GridBagLayout());

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.fill = GridBagConstraints.BOTH;
        constraints.weightx = 1.0;

        constraints.gridy = 0;
        constraints.weighty = 1.0;
        panel.add(title, constraints);

        constraints.gridy++;
        JPanel namePanel = new CHPanel();
        namePanel.setLayout(new BoxLayout(namePanel, BoxLayout.X_AXIS));
        namePanel.setBorder(BorderFactory.createEmptyBorder(PADDING_NONE, PADDING_STANDARD, PADDING_NONE, 20));
        nameLabel.setBorder(BorderFactory.createMatteBorder(PADDING_NONE, PADDING_NONE, PADDING_NONE, PADDING_STANDARD, CHStyles.secondaryColor()));
        namePanel.add(nameLabel);
        namePanel.add(nameTextField);
        panel.add(namePanel, constraints);

        constraints.gridy++;
        JPanel deckPanel = new CHPanel();
        deckPanel.setLayout(new BoxLayout(deckPanel, BoxLayout.X_AXIS));
        deckLabel.setBorder(BorderFactory.createMatteBorder(PADDING_NONE, PADDING_NONE, PADDING_NONE, PADDING_SMALL, CHStyles.secondaryColor()));
        deckPanel.add(deckLabel);
        deckBox.addItem("test 1");
        deckBox.addItem("test 2");
        deckBox.setBorder(BorderFactory.createMatteBorder(PADDING_NONE, PADDING_STANDARD, PADDING_NONE, PADDING_STANDARD, CHStyles.secondaryColor()));
        deckPanel.add(deckBox);
        deckPanel.setBorder(BorderFactory.createMatteBorder(PADDING_STANDARD, PADDING_STANDARD, PADDING_STANDARD, PADDING_STANDARD, CHStyles.secondaryColor()));
        panel.add(deckPanel, constraints);
    }

    //Sets up gameModesPanel
    private void manageGameModesPanel() {
        gameModesPanel.setBackground(new Color(CHColor.PRIMARY.getCode()));
        gameModesPanel.setPreferredSize(new Dimension(WIDTH, (int) (HEIGHT * GAME_MODES_PANEL_RATIO)));
        gameModesPanel.setLayout(new BoxLayout(gameModesPanel, BoxLayout.Y_AXIS));
        
        gameModesPanel.add(gameModeLabel);
        gameModeLabel.setAlignmentX(CENTER_ALIGNMENT);

        freePlayRadioButton.setBackground(CHStyles.primaryColor());
        freePlayRadioButton.addActionListener(e -> {
            //Makes the settings panel invisible
            settingsPanel.setVisible(false);
        });
        gameModesGroup.add(freePlayRadioButton);
        this.createRow(gameModesPanel, freePlayRadioButton);
        freePlayRadioButton.setAlignmentX(CENTER_ALIGNMENT);

        customRulesRadioButton.setBackground(CHStyles.primaryColor());
        customRulesRadioButton.addActionListener(e -> {
            //Makes the settings panel visible
            settingsPanel.setVisible(true);
        });
        gameModesGroup.add(customRulesRadioButton);
        this.createRow(gameModesPanel, customRulesRadioButton);
        customRulesRadioButton.setAlignmentX(CENTER_ALIGNMENT);
        customRulesRadioButton.setBorder(BorderFactory.createEmptyBorder(PADDING_NONE, 27, PADDING_SMALL, PADDING_NONE));

        fullGameRadioButton.setBackground(CHStyles.primaryColor());
        fullGameRadioButton.addActionListener(e -> {
            //Makes the settings panel invisible
            settingsPanel.setVisible(false);
        });
        fullGameRadioButton.addItemListener(e -> {
            //Disables deck selecting when full game is selected
            firstPlayerDeckBox.setEnabled(!fullGameRadioButton.isSelected());
            secondPlayerDeckBox.setEnabled(!fullGameRadioButton.isSelected());
        });
        gameModesGroup.add(fullGameRadioButton);
        this.createRow(gameModesPanel, fullGameRadioButton);
        fullGameRadioButton.setAlignmentX(CENTER_ALIGNMENT);
        fullGameRadioButton.setBorder(BorderFactory.createEmptyBorder(PADDING_NONE, PADDING_SMALL, PADDING_NONE, PADDING_NONE));
    }

    //Sets up settingsPanel
    private void manageSettingsPanel() {
        settingsPanel.setBackground(new Color(CHColor.PRIMARY.getCode()));
        settingsPanel.setVisible(false);
        settingsPanel.setLayout(new BoxLayout(settingsPanel, BoxLayout.Y_AXIS));
        settingsPanel.setBorder(BorderFactory.createMatteBorder(CENTER_PANEL_PADDING_BETWEEN, PADDING_NONE, PADDING_NONE, PADDING_NONE, CHStyles.secondaryColor()));
    
        settingsLabel.setAlignmentX(CENTER_ALIGNMENT);
        settingsPanel.add(settingsLabel);

        this.createRow(settingsPanel, handSizeLabel, handSizeSpinner, fieldSizeLabel, fieldSizeSpinner);

        handSizeSpinner.addChangeListener(e -> {
            //Forces starting hand size to be lower or equal to max hand size
            int maxHandSize = (Integer) handSizeSpinner.getValue();

            startingHandModel.setMaximum(maxHandSize);
            if ((Integer) startingHandSpinner.getValue() > maxHandSize) {
                startingHandSpinner.setValue(maxHandSize);
            }
        });

        autoDrawCheckBox.setBackground(new Color(CHColor.PRIMARY.getCode()));

        this.createRow(settingsPanel, startingHandLabel, startingHandSpinner, autoDrawCheckBox);

        winnerActionLabel.setAlignmentX(CENTER_ALIGNMENT);
        settingsPanel.add(winnerActionLabel);

        winPileRadioButton.setBackground(new Color(CHColor.PRIMARY.getCode()));
        winnerActionGroup.add(winPileRadioButton);

        winLoserPileRadioButton.setBackground(new Color(CHColor.PRIMARY.getCode()));
        winnerActionGroup.add(winLoserPileRadioButton);

        winNoneRadioButton.setBackground(new Color(CHColor.PRIMARY.getCode()));
        winnerActionGroup.add(winNoneRadioButton);

        this.createRow(settingsPanel, winPileRadioButton, winLoserPileRadioButton, winNoneRadioButton);

        loserActionLabel.setAlignmentX(CENTER_ALIGNMENT);
        settingsPanel.add(loserActionLabel);

        losePileRadioButton.setBackground(new Color(CHColor.PRIMARY.getCode()));
        loserActionGroup.add(losePileRadioButton);

        loseWinnerPileRadioButton.setBackground(new Color(CHColor.PRIMARY.getCode()));
        loserActionGroup.add(loseWinnerPileRadioButton);

        loseNoneRadioButton.setBackground(new Color(CHColor.PRIMARY.getCode()));
        loserActionGroup.add(loseNoneRadioButton);

        this.createRow(settingsPanel, losePileRadioButton, loseWinnerPileRadioButton, loseNoneRadioButton);
    }

    private void createRow(JPanel panel, JComponent... components) {
        JPanel row = new CHPanel();
        row.setBackground(CHStyles.primaryColor());
        for (JComponent component : components) {
            row.add(component);
        }
        panel.add(row);
    }

    private int getDeck(JComboBox deckBox) {
        throw new EmptyFieldException();
    }

    private void startGame(CreateMatchController controller) {
        String firstPlayerName = firstPlayerNameField.getText();
        String secondPlayerName = secondPlayerNameField.getText();

        if (!firstPlayerName.isEmpty() && !secondPlayerName.isEmpty()) {
            if (freePlayRadioButton.isSelected()) {
                controller.createFreeGame(firstPlayerName, this.getDeck(firstPlayerDeckBox), secondPlayerName, this.getDeck(secondPlayerDeckBox));
                return;
            } else if (fullGameRadioButton.isSelected()) {
                controller.createFullGame(firstPlayerName, secondPlayerName);
                return;
            } else if (customRulesRadioButton.isSelected()) {
                if (winnerActionGroup.getSelection() != null && loserActionGroup.getSelection() != null) {
                    controller.createCustomGame(firstPlayerName, this.getDeck(firstPlayerDeckBox), 
                                                secondPlayerName, this.getDeck(secondPlayerDeckBox), 
                                                (Integer) handSizeSpinner.getValue(), (Integer) startingHandSpinner.getValue(), 
                                                (Integer) fieldSizeSpinner.getValue(), autoDrawCheckBox.isSelected());
                    return;
                }
            }
        }
        throw new EmptyFieldException();
    }
}
