package it.unibo.cardhub.view.impl;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;

import it.unibo.cardhub.controller.api.CreateMatchController;
import it.unibo.cardhub.model.domain.exceptions.EmptyFieldException;
import it.unibo.cardhub.model.logic.GameMode;
import it.unibo.cardhub.model.logic.api.CardAction;
import it.unibo.cardhub.view.api.CreateMatchView;
import it.unibo.cardhub.view.components.CHButton;
import it.unibo.cardhub.view.components.CHLabel;
import it.unibo.cardhub.view.components.CHPanel;
import it.unibo.cardhub.view.components.CHStyles;
import it.unibo.cardhub.view.components.CHTextField;
import it.unibo.cardhub.view.components.CHTitle;
import it.unibo.cardhub.view.components.ScreenView;

/**
 * Create Match View, showing the setting needed to create the match.
 */
public final class CreateMatchViewImpl extends ScreenView implements CreateMatchView {
    public static final int WIDTH = 470;
    public static final int HEIGHT = 710;

    private static final long serialVersionUID = 42L;

    private static final int SPINNER_STEP_SIZE = 1;

    //GUI padding and panel size
    private static final int TOP_PANEL_PADDING = 7;
    private static final int RADIO_BUTTON_PADDING = 27;
    private static final int CENTER_PANEL_Y_PADDING = 15;
    private static final int CENTER_PANEL_X_PADDING = 65;
    private static final int CENTER_PANEL_PADDING_BETWEEN = 25;
    private static final int GRID_DEFAULT = 0;
    private static final double GRID_WEIGHT_DEFAULT = 1.0;
    private static final double TOP_PANEL_RATIO = 0.06;
    private static final double PLAYERS_PANEL_RATIO = 0.35;
    private static final double GAME_MODES_PANEL_RATIO = 0.15;
    private static final double SETTINGS_PANEL_RATIO = 0.29;

    //Panels
    private final JPanel playersPanel;
    private final JPanel firstPlayerPanel;
    private final JPanel secondPlayerPanel;
    private final JPanel gameModesPanel;
    private final JPanel settingsPanel;

    //Content pane items
    private final JButton backButton;
    private final JButton playButton;

    //playersPanel items
    private final JTextField firstPlayerNameField;
    private final JComboBox<DeckBoxItem<Integer, String>> firstPlayerDeckBox;
    private final JTextField secondPlayerNameField;
    private final JComboBox<DeckBoxItem<Integer, String>> secondPlayerDeckBox;

    //gameModesPanel items
    private final JRadioButton freePlayRadioButton;
    private final JRadioButton customRulesRadioButton;
    private final JRadioButton fullGameRadioButton;
    private final ButtonGroup gameModesGroup;

    //settingsPanel items
    private final JSpinner handSizeSpinner;
    private final SpinnerNumberModel handSizeModel;
    private final JSpinner fieldSizeSpinner;
    private final SpinnerNumberModel fieldSizeModel;
    private final JSpinner startingHandSpinner;
    private final SpinnerNumberModel startingHandModel;
    private final JCheckBox autoDrawCheckBox;
    private final JRadioButton winPileRadioButton;
    private final JRadioButton winHandRadioButton;
    private final JRadioButton winNoneRadioButton;
    private final ButtonGroup winnerActionGroup;
    private final JRadioButton losePileRadioButton;
    private final JRadioButton loseHandRadioButton;
    private final JRadioButton loseNoneRadioButton;
    private final ButtonGroup loserActionGroup;

    //controller
    private final transient CreateMatchController controller;
    private final Map<Integer, String> decks;
    private GameMode selectedGameMode;
    private CardAction selectedWinnerCardAction;
    private CardAction selectedLoserCardAction;

    /**
     * Builds the create match view,
     * calling ManageContentPane to start laying the components.
     * 
     * @param controller the controller for the current screen
     */
    public CreateMatchViewImpl(final CreateMatchController controller) {
        playersPanel = new CHPanel(CHStyles.primaryColor(), new BorderLayout());
        firstPlayerPanel = new CHPanel(new GridBagLayout());
        secondPlayerPanel = new CHPanel(new GridBagLayout());
        gameModesPanel = new CHPanel(CHStyles.primaryColor());
        settingsPanel = new CHPanel(CHStyles.primaryColor());

        backButton = new CHButton("<");
        playButton = new CHButton("Play");

        firstPlayerNameField = new CHTextField();
        firstPlayerDeckBox = new JComboBox<>();
        secondPlayerNameField = new CHTextField();
        secondPlayerDeckBox = new JComboBox<>();

        freePlayRadioButton = new JRadioButton("Free Play");
        customRulesRadioButton = new JRadioButton("Custom Rules");
        fullGameRadioButton = new JRadioButton("Full Game");
        gameModesGroup = new ButtonGroup();

        handSizeModel = new SpinnerNumberModel(controller.getDefaultHandSize(), controller.getMaxHandSize(), 
                                                controller.getMaxHandSize(), SPINNER_STEP_SIZE);
        handSizeSpinner = new JSpinner(handSizeModel);
        fieldSizeModel = new SpinnerNumberModel(controller.getDefaultFieldSize(), controller.getMinFieldSize(), 
                                                controller.getMaxFieldSize(), SPINNER_STEP_SIZE);
        fieldSizeSpinner = new JSpinner(fieldSizeModel);
        //Starting hand size needs to always be lower or equal to max hand size
        startingHandModel = new SpinnerNumberModel(controller.getDefaultHandSize(), controller.getMaxHandSize(),
                                                    ((Integer) handSizeSpinner.getValue()).intValue(), SPINNER_STEP_SIZE);
        startingHandSpinner = new JSpinner(startingHandModel);
        autoDrawCheckBox = new JCheckBox("Auto Draw on Turn Start");
        winPileRadioButton = new JRadioButton("To Pile");
        winHandRadioButton = new JRadioButton("To Loser's Pile");
        winNoneRadioButton = new JRadioButton("None");
        winnerActionGroup = new ButtonGroup();
        losePileRadioButton = new JRadioButton("To Pile");
        loseHandRadioButton = new JRadioButton("To Winner's Pile");
        loseNoneRadioButton = new JRadioButton("None");
        loserActionGroup = new ButtonGroup();

        this.controller = controller;
        decks = controller.getDecks();

        //default values
        freePlayRadioButton.setSelected(true);
        selectedGameMode = GameMode.FREE_PLAY;

        winPileRadioButton.setSelected(true);
        selectedWinnerCardAction = CardAction.TO_PILE;

        losePileRadioButton.setSelected(true);
        selectedLoserCardAction = CardAction.TO_PILE;

        this.manageContentPane();
    }

    //sets up the content pane
    private void manageContentPane() {
        this.setLayout(new BorderLayout());

        final JPanel topPanel = new CHPanel(new BorderLayout());
        this.add(topPanel, BorderLayout.NORTH);
        this.manageTopPanel(topPanel);

        final JPanel bottomPanel = new CHPanel();
        bottomPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        bottomPanel.add(playButton);
        playButton.addActionListener(e -> {
            this.startGame();
        });
        this.add(bottomPanel, BorderLayout.SOUTH);

        final JPanel centerPanel = new CHPanel();
        this.add(centerPanel, BorderLayout.CENTER);
        this.manageCenterPanel(centerPanel);
    }

    //sets up topPanel
    private void manageTopPanel(final JPanel topPanel) {
        topPanel.setPreferredSize(new Dimension(WIDTH, (int) (HEIGHT * TOP_PANEL_RATIO)));
        topPanel.setBorder(BorderFactory.createEmptyBorder(TOP_PANEL_PADDING, TOP_PANEL_PADDING,
                                                            TOP_PANEL_PADDING, TOP_PANEL_PADDING));

        backButton.addActionListener(e -> {
            controller.goBack();
        });
        topPanel.add(backButton, BorderLayout.WEST);

        final JLabel title = new CHTitle("Create Match");
        title.setHorizontalAlignment(SwingConstants.CENTER);
        topPanel.add(title, BorderLayout.CENTER);

        //empty panel to center title
        final JPanel emptyPanel = new CHPanel();
        emptyPanel.setPreferredSize(backButton.getPreferredSize());
        emptyPanel.setMaximumSize(backButton.getPreferredSize());
        topPanel.add(emptyPanel, BorderLayout.EAST);
    }

    //sets up centerPanel
    private void manageCenterPanel(final JPanel centerPanel) {
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(CENTER_PANEL_Y_PADDING, CENTER_PANEL_X_PADDING,
                                                                CENTER_PANEL_Y_PADDING, CENTER_PANEL_X_PADDING));

        centerPanel.add(playersPanel);
        this.managePlayersPanel();

        //encapsulates gameModesPanel for better size management
        final JPanel gameModesPanelContainer = new CHPanel(new BorderLayout());
        gameModesPanelContainer.setPreferredSize(new Dimension(WIDTH, (int) (HEIGHT * GAME_MODES_PANEL_RATIO)));
        centerPanel.add(gameModesPanelContainer);
        gameModesPanelContainer.add(gameModesPanel, BorderLayout.CENTER);
        this.manageGameModesPanel();

        //encapsulates settingsPanel so that when it's not visible the other panels don't get resized
        final JPanel settingsPanelContainer = new CHPanel(new BorderLayout());
        settingsPanelContainer.setPreferredSize(new Dimension(WIDTH, (int) (HEIGHT * SETTINGS_PANEL_RATIO)));
        centerPanel.add(settingsPanelContainer);
        settingsPanelContainer.add(settingsPanel, BorderLayout.CENTER);
        this.manageSettingsPanel();
    }

    //sets up playersPanel
    private void managePlayersPanel() {
        playersPanel.setPreferredSize(new Dimension(WIDTH, (int) (HEIGHT * PLAYERS_PANEL_RATIO)));
        playersPanel.setBorder(BorderFactory.createMatteBorder(CHStyles.PADDING_NONE, CHStyles.PADDING_NONE,
                                                                CENTER_PANEL_PADDING_BETWEEN, CHStyles.PADDING_NONE,
                                                                CHStyles.secondaryColor()));

        final JLabel playersLabel = new CHLabel("Players", CHStyles.secondaryColor(), SwingConstants.CENTER);
        playersPanel.add(playersLabel, BorderLayout.NORTH);

        //contains both players' panels
        final JPanel playersContainer = new CHPanel(CHStyles.primaryColor());
        playersContainer.setLayout(new BoxLayout(playersContainer, BoxLayout.Y_AXIS));
        playersContainer.setBorder(BorderFactory.createEmptyBorder(CHStyles.PADDING_NONE, CHStyles.PADDING_STANDARD,
                                                                    CHStyles.PADDING_STANDARD, CHStyles.PADDING_STANDARD));
        playersPanel.add(playersContainer, BorderLayout.CENTER);

        //Player 1 Panel
        firstPlayerPanel.setBorder(BorderFactory.createMatteBorder(CHStyles.PADDING_NONE, CHStyles.PADDING_NONE,
                                                                    CHStyles.PADDING_STANDARD, CHStyles.PADDING_NONE,
                                                                    CHStyles.primaryColor()));
        managePlayerPanel(firstPlayerPanel, playersContainer, new CHLabel("Player 1", SwingConstants.CENTER), 
                            new CHLabel("Name"), new CHLabel("Deck"), firstPlayerNameField, firstPlayerDeckBox);

        //Player 2 Panel
        managePlayerPanel(secondPlayerPanel, playersContainer, new CHLabel("Player 2", SwingConstants.CENTER), 
                            new CHLabel("Name"), new CHLabel("Deck"), secondPlayerNameField, secondPlayerDeckBox);

    }

    //sets up firstPlayerPanel and secondPlayerPanel
    private void managePlayerPanel(final JPanel panel, final JPanel container, final JLabel title, final JLabel nameLabel,
                                    final JLabel deckLabel, final JTextField nameTextField,
                                    final JComboBox<DeckBoxItem<Integer, String>> deckBox) {
        final GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.BOTH;
        constraints.weightx = GRID_WEIGHT_DEFAULT;

        constraints.gridy = GRID_DEFAULT;
        constraints.weighty = GRID_WEIGHT_DEFAULT;
        panel.add(title, constraints);

        constraints.gridy++;
        final JPanel namePanel = new CHPanel();
        namePanel.setLayout(new BoxLayout(namePanel, BoxLayout.X_AXIS));
        namePanel.setBorder(BorderFactory.createEmptyBorder(CHStyles.PADDING_NONE, CHStyles.PADDING_STANDARD,
                                                            CHStyles.PADDING_NONE, CHStyles.PADDING_LARGE));
        nameLabel.setBorder(BorderFactory.createMatteBorder(CHStyles.PADDING_NONE, CHStyles.PADDING_NONE,
                                                            CHStyles.PADDING_NONE, CHStyles.PADDING_STANDARD,
                                                            CHStyles.secondaryColor()));
        namePanel.add(nameLabel);
        namePanel.add(nameTextField);
        panel.add(namePanel, constraints);

        constraints.gridy++;
        final JPanel deckPanel = new CHPanel();
        deckPanel.setLayout(new BoxLayout(deckPanel, BoxLayout.X_AXIS));
        deckLabel.setBorder(BorderFactory.createMatteBorder(CHStyles.PADDING_NONE, CHStyles.PADDING_NONE,
                                                            CHStyles.PADDING_NONE, CHStyles.PADDING_SMALL,
                                                            CHStyles.secondaryColor()));
        deckPanel.add(deckLabel);
        deckBox.setBorder(BorderFactory.createMatteBorder(CHStyles.PADDING_NONE, CHStyles.PADDING_STANDARD,
                                                            CHStyles.PADDING_NONE, CHStyles.PADDING_STANDARD,
                                                            CHStyles.secondaryColor()));
        for (final Map.Entry<Integer, String> entry : decks.entrySet()) {
            deckBox.addItem(new DeckBoxItem<>(entry.getKey(), entry.getValue()));
        }
        deckPanel.add(deckBox);
        deckPanel.setBorder(BorderFactory.createMatteBorder(CHStyles.PADDING_STANDARD, CHStyles.PADDING_STANDARD,
                                                            CHStyles.PADDING_STANDARD, CHStyles.PADDING_STANDARD,
                                                            CHStyles.secondaryColor()));
        panel.add(deckPanel, constraints);

        container.add(panel);
    }

    //Sets up gameModesPanel
    private void manageGameModesPanel() {
        gameModesPanel.setPreferredSize(new Dimension(WIDTH, (int) (HEIGHT * GAME_MODES_PANEL_RATIO)));
        gameModesPanel.setLayout(new BoxLayout(gameModesPanel, BoxLayout.Y_AXIS));

        final JLabel gameModeLabel = new CHLabel("Game Mode", CHStyles.secondaryColor(), SwingConstants.CENTER);
        gameModesPanel.add(gameModeLabel);
        gameModeLabel.setAlignmentX(CENTER_ALIGNMENT);

        this.manageRadioButton(gameModesGroup, freePlayRadioButton, customRulesRadioButton, fullGameRadioButton);

        freePlayRadioButton.addActionListener(e -> {
            selectedGameMode = GameMode.FREE_PLAY;

            //Makes the settings panel invisible
            settingsPanel.setVisible(false);
        });
        this.createRow(gameModesPanel, freePlayRadioButton);
        freePlayRadioButton.setAlignmentX(CENTER_ALIGNMENT);

        customRulesRadioButton.addActionListener(e -> {
            selectedGameMode = GameMode.CUSTOM;

            //Makes the settings panel visible
            settingsPanel.setVisible(true);
        });
        this.createRow(gameModesPanel, customRulesRadioButton);
        customRulesRadioButton.setAlignmentX(CENTER_ALIGNMENT);
        customRulesRadioButton.setBorder(BorderFactory.createEmptyBorder(CHStyles.PADDING_NONE, RADIO_BUTTON_PADDING,
                                                                            CHStyles.PADDING_SMALL, CHStyles.PADDING_NONE));

        fullGameRadioButton.addActionListener(e -> {
            selectedGameMode = GameMode.FULL_GAME;

            //Makes the settings panel invisible
            settingsPanel.setVisible(false);
        });
        fullGameRadioButton.addItemListener(e -> {
            //Disables deck selecting when full game is selected
            firstPlayerDeckBox.setEnabled(!fullGameRadioButton.isSelected());
            secondPlayerDeckBox.setEnabled(!fullGameRadioButton.isSelected());
        });
        this.createRow(gameModesPanel, fullGameRadioButton);
        fullGameRadioButton.setAlignmentX(CENTER_ALIGNMENT);
        fullGameRadioButton.setBorder(BorderFactory.createEmptyBorder(CHStyles.PADDING_NONE, CHStyles.PADDING_SMALL,
                                                                        CHStyles.PADDING_NONE, CHStyles.PADDING_NONE));
    }

    //Sets up settingsPanel
    private void manageSettingsPanel() {
        settingsPanel.setVisible(false);
        settingsPanel.setLayout(new BoxLayout(settingsPanel, BoxLayout.Y_AXIS));
        settingsPanel.setBorder(BorderFactory.createMatteBorder(CENTER_PANEL_PADDING_BETWEEN, CHStyles.PADDING_NONE,
                                                                CHStyles.PADDING_NONE, CHStyles.PADDING_NONE,
                                                                CHStyles.secondaryColor()));

        final JLabel settingsLabel = new CHLabel("Custom Settings", CHStyles.secondaryColor(), SwingConstants.CENTER);
        settingsLabel.setAlignmentX(CENTER_ALIGNMENT);
        settingsPanel.add(settingsLabel);

        final JLabel handSizeLabel = new CHLabel("Max Hand Size", CHStyles.secondaryColor(), SwingConstants.CENTER);
        final JLabel fieldSizeLabel = new CHLabel("Field Size per Player", CHStyles.secondaryColor(), SwingConstants.CENTER);
        this.createRow(settingsPanel, handSizeLabel, handSizeSpinner, fieldSizeLabel, fieldSizeSpinner);

        handSizeSpinner.addChangeListener(e -> {
            //Forces starting hand size to be lower or equal to max hand size
            final int maxHandSize = (Integer) handSizeSpinner.getValue();

            startingHandModel.setMaximum(maxHandSize);
            if ((Integer) startingHandSpinner.getValue() > maxHandSize) {
                startingHandSpinner.setValue(maxHandSize);
            }
        });

        autoDrawCheckBox.setBackground(CHStyles.primaryColor());

        final JLabel startingHandLabel = new CHLabel("Starting Hand Size", CHStyles.secondaryColor(), SwingConstants.LEFT);
        this.createRow(settingsPanel, startingHandLabel, startingHandSpinner, autoDrawCheckBox);

        final JLabel winnerActionLabel = new CHLabel("Winner Card Action", CHStyles.secondaryColor(), SwingConstants.CENTER);
        winnerActionLabel.setAlignmentX(CENTER_ALIGNMENT);
        settingsPanel.add(winnerActionLabel);

        winPileRadioButton.addItemListener(e -> {
            selectedWinnerCardAction = CardAction.TO_PILE;
        });
        winHandRadioButton.addItemListener(e -> {
            selectedWinnerCardAction = CardAction.TO_HAND;
        });
        winNoneRadioButton.addItemListener(e -> {
            selectedWinnerCardAction = CardAction.NONE;
        });
        this.manageRadioButton(winnerActionGroup, winPileRadioButton, winHandRadioButton, winNoneRadioButton);
        this.createRow(settingsPanel, winPileRadioButton, winHandRadioButton, winNoneRadioButton);

        final JLabel loserActionLabel = new CHLabel("Loser Card Action", CHStyles.secondaryColor(), SwingConstants.CENTER);
        loserActionLabel.setAlignmentX(CENTER_ALIGNMENT);
        settingsPanel.add(loserActionLabel);

        losePileRadioButton.addItemListener(e -> {
            selectedLoserCardAction = CardAction.TO_PILE;
        });
        loseHandRadioButton.addItemListener(e -> {
            selectedLoserCardAction = CardAction.TO_HAND;
        });
        loseNoneRadioButton.addItemListener(e -> {
            selectedLoserCardAction = CardAction.NONE;
        });
        this.manageRadioButton(loserActionGroup, losePileRadioButton, loseHandRadioButton, loseNoneRadioButton);
        this.createRow(settingsPanel, losePileRadioButton, loseHandRadioButton, loseNoneRadioButton);
    }

    //creates a horizontal jPanel with the given components
    private void createRow(final JPanel panel, final JComponent... components) {
        final JPanel row = new CHPanel(CHStyles.primaryColor());
        for (final JComponent component : components) {
            row.add(component);
        }
        panel.add(row);
    }

    //adds all the radioButtons to the same group, and changes their background color
    private void manageRadioButton(final ButtonGroup group, final JRadioButton... buttons) {
        for (final JRadioButton button : buttons) {
            button.setBackground(CHStyles.primaryColor());
            group.add(button);
        }
    }

    //tries to pass all the parameters to the controller
    private void startGame() {
        final String firstPlayerName = firstPlayerNameField.getText();
        final String secondPlayerName = secondPlayerNameField.getText();
        @SuppressWarnings("unchecked")
        final DeckBoxItem<Integer, String> player1Deck =
                (DeckBoxItem<Integer, String>) firstPlayerDeckBox.getSelectedItem();
        @SuppressWarnings("unchecked")
        final DeckBoxItem<Integer, String> player2Deck =
                (DeckBoxItem<Integer, String>) secondPlayerDeckBox.getSelectedItem();

        controller.tryCreatingMatch(firstPlayerName, player1Deck.key(),
                                            secondPlayerName, player2Deck.key(),
                                            (Integer) handSizeSpinner.getValue(), (Integer) startingHandSpinner.getValue(),
                                            (Integer) fieldSizeSpinner.getValue(), autoDrawCheckBox.isSelected(),
                                            selectedWinnerCardAction, selectedLoserCardAction, selectedGameMode);

    }

    //a record for the items to populate deckBox
    private record DeckBoxItem<K, V>(K key, V value) {
        @Override
        public String toString() {
            return value.toString();
        }
    }

    @Override
    public void showInvalidForm(final String message) {
        JOptionPane.showMessageDialog(this, message, "Must fill all fields!", JOptionPane.ERROR_MESSAGE);
    }
}
