package it.unibo.cardhub.view.creatematch;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

import it.unibo.cardhub.controller.api.CreateMatchController;
import it.unibo.cardhub.view.components.CHButton;
import it.unibo.cardhub.view.components.CHColor;
import it.unibo.cardhub.view.components.CHLabel;
import it.unibo.cardhub.view.components.CHPanel;
import it.unibo.cardhub.view.components.CHStyles;
import it.unibo.cardhub.view.components.CHTextField;
import it.unibo.cardhub.view.components.CHTitle;
import it.unibo.cardhub.view.components.ScreenView;

public class CreateMatchImpl extends ScreenView{
    private final static int MIN_HAND_SIZE = 1;
    private final static int MIN_FIELD_SIZE = 1;
    private final static int MAX_HAND_SIZE = 7;
    private final static int MAX_FIELD_SIZE = 6;

    //GUI padding and weights
    private final static int PADDING = 10;
    private final static int PADDING_NONE = 0;
    private final static int TOP_PANEL_PADDING = 7;
    private final static int CENTER_PANEL_Y_PADDING = 15;
    private final static int CENTER_PANEL_X_PADDING = 65;
    private final static int CENTER_PANEL_BETWEEN_PADDING = 25;
    private final static double PLAYERS_PANEL_WEIGHT = 45.0;
    private final static double GAME_MODES_PANEL_WEIGHT = 27.5;
    private final static double SETTINGS_PANEL_WEIGHT = 27.5;

    //Panels
    private final JPanel topPanel;
    private final JPanel centerPanel;
    private final JPanel bottomPanel;
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

    public CreateMatchImpl(CreateMatchController controller) {
        topPanel = new CHPanel();
        centerPanel = new CHPanel();
        bottomPanel = new CHPanel();
        playersPanel = new CHPanel();
        firstPlayerPanel = new CHPanel();
        secondPlayerPanel = new CHPanel();
        gameModesPanel = new CHPanel();
        settingsPanel = new CHPanel();

        back = new CHButton("<");
        title = new CHTitle("Create Match");
        play = new CHButton("Play");

        playersLabel = new CHLabel("Players");
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

        settingsLabel = new CHLabel("Custom Settings");
        handSizeLabel = new CHLabel("Max Hand Size");
        handSizeModel = new SpinnerNumberModel(4, MIN_HAND_SIZE, MAX_HAND_SIZE, 1);
        handSizeSpinner = new JSpinner(handSizeModel);
        fieldSizeLabel = new CHLabel("Field Size per Player");
        fieldSizeModel = new SpinnerNumberModel(3, MIN_FIELD_SIZE, MAX_FIELD_SIZE, 1);
        fieldSizeSpinner = new JSpinner(fieldSizeModel);
        startingHandLabel = new CHLabel("Starting Hand Size");
        //Starting hand size needs to always be lower or equal to max hand size
        startingHandModel = new SpinnerNumberModel(4, MIN_HAND_SIZE, ((Integer) handSizeSpinner.getValue()).intValue(), 1);
        startingHandSpinner = new JSpinner(startingHandModel);
        autoDrawCheckBox = new JCheckBox("Auto Draw on Turn Start");
        winnerActionLabel = new CHLabel("Winner Card Action");
        winPileRadioButton = new JRadioButton("To Pile");
        winLoserPileRadioButton = new JRadioButton("To Loser's Pile");
        winNoneRadioButton = new JRadioButton("None");
        winnerActionGroup = new ButtonGroup();
        loserActionLabel = new CHLabel("Loser Card Action");
        losePileRadioButton = new JRadioButton("To Pile");
        loseWinnerPileRadioButton = new JRadioButton("To Winner's Pile");
        loseNoneRadioButton = new JRadioButton("None");
        loserActionGroup = new ButtonGroup();

        this.setLayout(new BorderLayout());
        this.manageContentPane();
    }

    //sets up the content pane
    private void manageContentPane() {
        topPanel.setPreferredSize(new Dimension(470,40));
        this.add(topPanel, BorderLayout.NORTH);
        topPanel.setLayout(new BorderLayout());
        this.manageTopPanel();

        bottomPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        bottomPanel.add(play);
        this.add(bottomPanel, BorderLayout.SOUTH);

        centerPanel.setLayout(new GridBagLayout());
        this.add(centerPanel, BorderLayout.CENTER);
        this.manageCenterPanel();
    }

    //sets up topPanel
    private void manageTopPanel() {
        topPanel.setBorder(BorderFactory.createEmptyBorder(TOP_PANEL_PADDING, TOP_PANEL_PADDING, TOP_PANEL_PADDING, TOP_PANEL_PADDING));

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
    private void manageCenterPanel() {
        centerPanel.setBorder(BorderFactory.createEmptyBorder(CENTER_PANEL_Y_PADDING, CENTER_PANEL_X_PADDING, CENTER_PANEL_Y_PADDING, CENTER_PANEL_X_PADDING));

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.fill = GridBagConstraints.BOTH;
        constraints.weightx = 1.0;

        constraints.gridy = 0;
        constraints.weighty = PLAYERS_PANEL_WEIGHT;
        playersPanel.setBackground(CHStyles.primaryColor());
        playersPanel.setBorder(BorderFactory.createMatteBorder(PADDING_NONE, PADDING_NONE, CENTER_PANEL_BETWEEN_PADDING, PADDING_NONE, CHStyles.secondaryColor()));
        centerPanel.add(playersPanel, constraints);
        playersPanel.setLayout(null);
        this.managePlayersPanel();

        constraints.gridy++;
        constraints.weighty = GAME_MODES_PANEL_WEIGHT;
        gameModesPanel.setBackground(new Color(CHColor.PRIMARY.getCode()));
        gameModesPanel.setBorder(BorderFactory.createMatteBorder(PADDING_NONE, PADDING_NONE, CENTER_PANEL_BETWEEN_PADDING, PADDING_NONE, CHStyles.secondaryColor()));
        centerPanel.add(gameModesPanel, constraints);
        gameModesPanel.setLayout(null);
        this.manageGameModesPanel();

        constraints.gridy++;
        constraints.weighty = SETTINGS_PANEL_WEIGHT;
        
        //encapsulates settingsPanel so that when it's not visible the other panels don't get resized
        JPanel settingsPanelContainer = new CHPanel();
        settingsPanelContainer.setLayout(new BorderLayout());
        centerPanel.add(settingsPanelContainer, constraints);

        settingsPanel.setBackground(new Color(CHColor.PRIMARY.getCode()));
        settingsPanel.setVisible(false); 
        settingsPanelContainer.add(settingsPanel, BorderLayout.CENTER);
        settingsPanel.setLayout(null);
        this.manageSettingsPanel();
    }

    //sets up playersPanel
    private void managePlayersPanel() {
        playersLabel.setBounds(0, 3, 319, 14);
        playersLabel.setHorizontalAlignment(SwingConstants.CENTER);
        playersLabel.setForeground(new Color(CHColor.SECONDARY.getCode()));
        playersPanel.add(playersLabel);

        //Player 1 Panel
        firstPlayerPanel.setBounds(31, 21, 258, 90);
        playersPanel.add(firstPlayerPanel);
        firstPlayerPanel.setLayout(new GridBagLayout());
        managePlayerPanel(firstPlayerPanel, firstPlayerLabel, firstPlayerNameLabel, firstPlayerDeckLabel, firstPlayerNameField, firstPlayerDeckBox);

        //Player 2 Panel
        secondPlayerPanel.setBounds(31, 122, 258, 90);
        playersPanel.add(secondPlayerPanel);
        secondPlayerPanel.setLayout(new GridBagLayout());
        managePlayerPanel(secondPlayerPanel, secondPlayerLabel, secondPlayerNameLabel, secondPlayerDeckLabel, secondPlayerNameField, secondPlayerDeckBox);

    }

    //sets up firstPlayerPanel and secondPlayerPanel
    private void managePlayerPanel(JPanel panel, JLabel title, JLabel nameLabel, JLabel deckLabel, JTextField nameTextField, JComboBox deckBox) {
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
        namePanel.setBorder(BorderFactory.createEmptyBorder(PADDING_NONE, PADDING, PADDING_NONE, 20));
        nameLabel.setBorder(BorderFactory.createMatteBorder(PADDING_NONE, PADDING_NONE, PADDING_NONE, PADDING, CHStyles.secondaryColor()));
        namePanel.add(nameLabel);
        namePanel.add(nameTextField);
        panel.add(namePanel, constraints);

        constraints.gridy++;
        JPanel deckPanel = new CHPanel();
        deckPanel.setLayout(new BoxLayout(deckPanel, BoxLayout.X_AXIS));
        deckLabel.setBorder(BorderFactory.createMatteBorder(PADDING_NONE, PADDING_NONE, PADDING_NONE, 4, CHStyles.secondaryColor()));
        deckPanel.add(deckLabel);
        deckBox.addItem("test 1");
        deckBox.addItem("test 2");
        deckBox.setBorder(BorderFactory.createMatteBorder(PADDING_NONE, PADDING, PADDING_NONE, PADDING, CHStyles.secondaryColor()));
        deckPanel.add(deckBox);
        deckPanel.setBorder(BorderFactory.createMatteBorder(PADDING, PADDING, PADDING, PADDING, CHStyles.secondaryColor()));
        panel.add(deckPanel, constraints);
    }

    //Sets up gameModesPanel
    private void manageGameModesPanel() {
        gameModeLabel.setBounds(0, 3, 319, 14);
        gameModesPanel.add(gameModeLabel);

        freePlayRadioButton.setBackground(CHStyles.primaryColor());
        freePlayRadioButton.setBounds(118, 21, 107, 23);
        freePlayRadioButton.addActionListener(e -> {
            //Makes the settings panel invisible
            settingsPanel.setVisible(false);
        });
        gameModesGroup.add(freePlayRadioButton);
        gameModesPanel.add(freePlayRadioButton);

        customRulesRadioButton.setBackground(CHStyles.primaryColor());
        customRulesRadioButton.setBounds(118, 50, 107, 23);
        customRulesRadioButton.addActionListener(e -> {
            //Makes the settings panel visible
            settingsPanel.setVisible(true);
        });
        gameModesGroup.add(customRulesRadioButton);
        gameModesPanel.add(customRulesRadioButton);

        fullGameRadioButton.setBackground(CHStyles.primaryColor());
        fullGameRadioButton.setBounds(118, 78, 107, 23);
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
        gameModesPanel.add(fullGameRadioButton);
    }

    //Sets up settingsPanel
    private void manageSettingsPanel() {
        settingsLabel.setHorizontalAlignment(SwingConstants.CENTER);
        settingsLabel.setForeground(new Color(CHColor.SECONDARY.getCode()));
        settingsLabel.setBounds(0, 2, 319, 14);
        settingsPanel.add(settingsLabel);

        handSizeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        handSizeLabel.setForeground(new Color(CHColor.SECONDARY.getCode()));
        handSizeLabel.setBounds(10, 20, 85, 14);
        settingsPanel.add(handSizeLabel);

        handSizeSpinner.setBounds(95, 17, 30, 20);
        settingsPanel.add(handSizeSpinner);

        fieldSizeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        fieldSizeLabel.setForeground(new Color(CHColor.SECONDARY.getCode()));
        fieldSizeLabel.setBounds(155, 20, 119, 14);
        settingsPanel.add(fieldSizeLabel);

        fieldSizeSpinner.setBounds(275, 17, 30, 20);
        settingsPanel.add(fieldSizeSpinner);

        startingHandLabel.setHorizontalAlignment(SwingConstants.LEFT);
        startingHandLabel.setForeground(new Color(CHColor.SECONDARY.getCode()));
        startingHandLabel.setBounds(10, 47, 105, 14);
        settingsPanel.add(startingHandLabel);

        startingHandSpinner.setBounds(115, 44, 30, 20);
        settingsPanel.add(startingHandSpinner);
        handSizeSpinner.addChangeListener(e -> {
            //Forces starting hand size to be lower or equal to max hand size
            int maxHandSize = (Integer) handSizeSpinner.getValue();

            startingHandModel.setMaximum(maxHandSize);
            if ((Integer) startingHandSpinner.getValue() > maxHandSize) {
                startingHandSpinner.setValue(maxHandSize);
            }
        });

        autoDrawCheckBox.setBackground(new Color(CHColor.PRIMARY.getCode()));
        autoDrawCheckBox.setBounds(158, 43, 161, 23);
        settingsPanel.add(autoDrawCheckBox);

        winnerActionLabel.setHorizontalAlignment(SwingConstants.CENTER);
        winnerActionLabel.setForeground(new Color(CHColor.SECONDARY.getCode()));
        winnerActionLabel.setBounds(0, 70, 319, 14);
        settingsPanel.add(winnerActionLabel);

        winPileRadioButton.setBackground(new Color(CHColor.PRIMARY.getCode()));
        winPileRadioButton.setBounds(34, 88, 66, 23);
        winnerActionGroup.add(winPileRadioButton);
        settingsPanel.add(winPileRadioButton);

        winLoserPileRadioButton.setBackground(new Color(CHColor.PRIMARY.getCode()));
        winLoserPileRadioButton.setBounds(114, 88, 109, 23);
        winnerActionGroup.add(winLoserPileRadioButton);
        settingsPanel.add(winLoserPileRadioButton);

        winNoneRadioButton.setBackground(new Color(CHColor.PRIMARY.getCode()));
        winNoneRadioButton.setBounds(225, 88, 58, 23);
        winnerActionGroup.add(winNoneRadioButton);
        settingsPanel.add(winNoneRadioButton);

        loserActionLabel.setHorizontalAlignment(SwingConstants.CENTER);
        loserActionLabel.setForeground(new Color(CHColor.SECONDARY.getCode()));
        loserActionLabel.setBounds(0, 114, 319, 14);
        settingsPanel.add(loserActionLabel);

        losePileRadioButton.setBackground(new Color(CHColor.PRIMARY.getCode()));
        losePileRadioButton.setBounds(24, 130, 66, 23);
        loserActionGroup.add(losePileRadioButton);
        settingsPanel.add(losePileRadioButton);

        loseWinnerPileRadioButton.setBackground(new Color(CHColor.PRIMARY.getCode()));
        loseWinnerPileRadioButton.setBounds(104, 130, 120, 23);
        loserActionGroup.add(loseWinnerPileRadioButton);
        settingsPanel.add(loseWinnerPileRadioButton);

        loseNoneRadioButton.setBackground(new Color(CHColor.PRIMARY.getCode()));
        loseNoneRadioButton.setBounds(235, 130, 58, 23);
        loserActionGroup.add(loseNoneRadioButton);
        settingsPanel.add(loseNoneRadioButton);
    }
}
