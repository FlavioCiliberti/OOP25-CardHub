package it.unibo.cardhub.view.impl;

import java.awt.BorderLayout;
import java.awt.Component;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JScrollPane;

import it.unibo.cardhub.view.api.DecksView;
import it.unibo.cardhub.view.components.CHButton;
import it.unibo.cardhub.view.components.CHEntryPanel;
import it.unibo.cardhub.view.components.CHPanel;
import it.unibo.cardhub.view.components.CHTitle;
import it.unibo.cardhub.view.components.ScreenView;

/**
 * Swing implementation of the decks view.
 */
public final class DecksViewImpl extends ScreenView implements DecksView {
    
    private final CHPanel deckListPanel;
    private final CHPanel titlePanel;
    private final CHPanel createPanel;
    private final CHButton createNewButton;
    private final CHButton backButton;
    private final JScrollPane scrollPane;

    /**
     * Creates a new screen for visualization of decks.
     */
    public DecksViewImpl() {
        deckListPanel = new CHPanel();
        titlePanel = new CHPanel();
        createPanel = new CHPanel();

        createNewButton = new CHButton("Create new deck");
        backButton = new CHButton("<");

        scrollPane = new JScrollPane(deckListPanel);

        manageTitlePanel();
        manageDeckListPanel();
        manageCreatePanel();
        loadDummyDecks();

        setUpListeners();

        this.setLayout(new BorderLayout());
        this.add(titlePanel, BorderLayout.NORTH);
        this.add(scrollPane, BorderLayout.CENTER);
        this.add(createPanel, BorderLayout.SOUTH);
    }

    private void manageTitlePanel() {
        JLabel title = new CHTitle("Decks");
        titlePanel.setLayout(new BorderLayout());
        titlePanel.add(title, BorderLayout.CENTER);
        titlePanel.add(backButton, BorderLayout.WEST);
        title.setHorizontalAlignment(CHTitle.CENTER);
        titlePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    }

    private void manageDeckListPanel(){
        deckListPanel.setLayout(new BoxLayout(deckListPanel, BoxLayout.Y_AXIS));
        deckListPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    }

    private void manageCreatePanel(){
        createPanel.add(createNewButton);
        createPanel.setBorder(BorderFactory.createCompoundBorder(
            this.getBorder(),
            BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addDeck(final String deckName, final int numberOfCards) {
        CHEntryPanel entry = new CHEntryPanel(deckName, String.valueOf(numberOfCards), "Edit", "Delete");

        deckListPanel.add(entry);

        deckListPanel.revalidate();
        deckListPanel.repaint();
    }

    private void loadDummyDecks() {
        addDeck("Standard deck", 32);
        addDeck("Pokemon", 60);
        addDeck("Yu-Gi-Oh!", 40);

        deckListPanel.revalidate();
        deckListPanel.repaint();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void clearDecks() {
        deckListPanel.removeAll();
        deckListPanel.revalidate();
        deckListPanel.repaint();
    }

    private void setUpListeners() {
        createNewButton.addActionListener(e -> goToDeckManager());
        backButton.addActionListener(e -> goToHome());

        // Question: should I also iterate though single entries to find their two buttons or does it iterate through each entry components recursively?
        boolean first = true;
        for (final Component c : deckListPanel.getComponents()) {
            if (c instanceof CHButton && first) {
                CHButton button = (CHButton) c;
                button.addActionListener(e -> goToDeckManager());
                first = false;
            }
            if (c instanceof CHButton && !first) {
                CHButton button = (CHButton) c;
                button.addActionListener(e -> {
                    for (final Component d : deckListPanel.getComponents()) {
                        if (d instanceof CHEntryPanel) {
                            CHEntryPanel entry = (CHEntryPanel) d;
                            if (entry.getSecondButton().equals(button)) {
                                deckListPanel.remove(entry);
                            }
                        }
                    }
                });
                first = true;
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void goToDeckManager() {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void goToHome() {
    }
}
