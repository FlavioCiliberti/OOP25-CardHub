package it.unibo.cardhub.view.impl;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JScrollPane;

import it.unibo.cardhub.controller.api.ManageDecksController;
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
    private final CHPanel listPanel;
    private final CHPanel headerPanel;
    private final CHPanel listContainer;
    private final CHButton backButton;
    private final JScrollPane scrollPane;
    private final CHListHeader listHeader;
    private ActionListener selectListener;
    private ActionListener viewListener;
    private final ManageDecksController controller;
    private int index;

    /**
     * Creates a new screen for visualization of decks.
     */
    public DecksViewImpl(final ManageDecksController controller) {
        super();
        this.controller = controller;
        listPanel = new CHPanel();
        headerPanel = new CHPanel();
        listContainer = new CHPanel();

        backButton = new CHButton("<");

        listHeader = new CHListHeader("Deck name", "Number of cards");

        scrollPane = new JScrollPane(listPanel);

        manageTitlePanel();
        manageDeckListPanel();

        setUpListeners();

        listContainer.setLayout(new BorderLayout());
        listContainer.add(listHeader, BorderLayout.NORTH);
        listContainer.add(scrollPane, BorderLayout.CENTER);

        this.setLayout(new BorderLayout());
        this.add(headerPanel, BorderLayout.NORTH);
        this.add(listContainer, BorderLayout.CENTER);
    }

    private void manageTitlePanel() {
        final JLabel title = new CHTitle("Decks");
        headerPanel.setLayout(new BorderLayout());
        headerPanel.add(title, BorderLayout.CENTER);
        headerPanel.add(backButton, BorderLayout.WEST);
        title.setHorizontalAlignment(CHTitle.CENTER);
        headerPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    }

    private void manageDeckListPanel() {
        listPanel.setLayout(new BoxLayout(listPanel, BoxLayout.Y_AXIS));
        listPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addDeck(final String deckName, final int numberOfCards) {
        final CHEntryPanel entry = new CHEntryPanel(deckName, String.valueOf(numberOfCards), "Select", "View");
        index++;

        entry.getFirstButton().setActionCommand(String.valueOf(index));
        entry.getSecondButton().setActionCommand(String.valueOf(index));
        entry.getFirstButton().addActionListener(selectListener);
        entry.getSecondButton().addActionListener(viewListener);

        listPanel.add(entry);

        listPanel.revalidate();
        listPanel.repaint();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void clearDecks() {
        listPanel.removeAll();
        listPanel.revalidate();
        listPanel.repaint();
        index = 0;
    }

    private void setUpListeners() {
        backButton.addActionListener(e -> controller.);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addBackListener(final ActionListener listener) {
        backButton.addActionListener(e -> controller.goBack());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addSelectListener(final ActionListener listener) {
        this.selectListener = listener;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addViewListener(final ActionListener listener) {
        this.viewListener = listener;
    }
}
