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
import it.unibo.cardhub.view.components.CHListHeader;
import it.unibo.cardhub.view.components.CHPanel;
import it.unibo.cardhub.view.components.CHTitle;
import it.unibo.cardhub.view.components.ScreenView;

/**
 * Swing implementation of the decks view.
 */
public final class DecksViewImpl extends ScreenView implements DecksView {

    private static final long serialVersionUID = 1L;
    private final CHPanel deckListPanel;
    private final CHPanel titlePanel;
    private final CHPanel createPanel;
    private final CHPanel listContainer;
    private final CHButton createNewButton;
    private final CHButton backButton;
    private final JScrollPane scrollPane;
    private final CHListHeader listHeader;
    private ActionListener editListener;
    private ActionListener deleteListener;
    private int index;

    /**
     * Creates a new screen for visualization of decks.
     */
    public DecksViewImpl() {
        deckListPanel = new CHPanel();
        titlePanel = new CHPanel();
        createPanel = new CHPanel();
        listContainer = new CHPanel();

        createNewButton = new CHButton("Create new deck");
        backButton = new CHButton("<");

        listHeader = new CHListHeader("Deck name", "Number of cards");

        scrollPane = new JScrollPane(deckListPanel);

        manageTitlePanel();
        manageDeckListPanel();
        manageCreatePanel();

        listContainer.setLayout(new BorderLayout());
        listContainer.add(listHeader, BorderLayout.NORTH);
        listContainer.add(scrollPane, BorderLayout.CENTER);

        this.setLayout(new BorderLayout());
        this.add(titlePanel, BorderLayout.NORTH);
        this.add(listContainer, BorderLayout.CENTER);
        this.add(createPanel, BorderLayout.SOUTH);
    }

    private void manageTitlePanel() {
        final JLabel title = new CHTitle("Decks");
        titlePanel.setLayout(new BorderLayout());
        titlePanel.add(title, BorderLayout.CENTER);
        titlePanel.add(backButton, BorderLayout.WEST);
        title.setHorizontalAlignment(CHTitle.CENTER);
        titlePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    }

    private void manageDeckListPanel() {
        deckListPanel.setLayout(new BoxLayout(deckListPanel, BoxLayout.Y_AXIS));
        deckListPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    }

    private void manageCreatePanel() {
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
        final CHEntryPanel entry = new CHEntryPanel(deckName, String.valueOf(numberOfCards), "Edit", "Delete");
        index++;

        entry.getFirstButton().setActionCommand(String.valueOf(index));
        entry.getSecondButton().setActionCommand(String.valueOf(index));
        entry.getFirstButton().addActionListener(editListener);
        entry.getSecondButton().addActionListener(deleteListener);

        deckListPanel.add(entry);

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
        index = 0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addCreateDeckListener(final ActionListener listener) {
        createNewButton.addActionListener(listener);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addBackListener(final ActionListener listener) {
        backButton.addActionListener(listener);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addEditListener(final ActionListener listener) {
        this.editListener = listener;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addDeleteListener(final ActionListener listener) {
        this.deleteListener = listener;
    }
}
