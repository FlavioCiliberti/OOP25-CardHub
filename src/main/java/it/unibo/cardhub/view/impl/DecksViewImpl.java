package it.unibo.cardhub.view.impl;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;

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
 * Implementation of DeckView.
 */
public class DecksViewImpl extends ScreenView implements DecksView {
    
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

        createNewButton = new CHButton("Create new");
        backButton = new CHButton("<");

        scrollPane = new JScrollPane(deckListPanel);

        manageTitlePanel();
        manageDeckListPanel();
        manageCreatePanel();
        loadDummyDecks();

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

    @Override
    public void addDeck(final String deckName, final int numberOfCards) {
        CHEntryPanel entry = new CHEntryPanel(deckName, String.valueOf(numberOfCards));
        entry.addButton(new CHButton("Edit"));
        entry.addButton(new CHButton("Delete"));

        deckListPanel.add(entry);

        deckListPanel.revalidate();
        deckListPanel.repaint();
    }

    private void loadDummyDecks() {
        CHEntryPanel deck1 = new CHEntryPanel("Standard Deck", "32 cards");
        deck1.addButton(new CHButton("Edit"));
        deck1.addButton(new CHButton("Delete"));
        deckListPanel.add(deck1);

        CHEntryPanel deck2 = new CHEntryPanel("Pokemon", "60 cards");
        deck2.addButton(new CHButton("Edit"));
        deck2.addButton(new CHButton("Delete"));
        deckListPanel.add(deck2);

        CHEntryPanel deck3 = new CHEntryPanel("Yu-Gi-Oh!", "40 cards");
        deck3.addButton(new CHButton("Edit"));
        deck3.addButton(new CHButton("Delete"));
        deckListPanel.add(deck3);

        deckListPanel.revalidate();
        deckListPanel.repaint();
    }

    @Override
    public void clearDecks() {
        deckListPanel.removeAll();
        deckListPanel.revalidate();
        deckListPanel.repaint();
    }

    @Override
    public void addCreateListener(ActionListener listener) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addCreateListener'");
    }

    @Override
    public void addBackListener(ActionListener listener) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addBackListener'");
    }
}
