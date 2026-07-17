package it.unibo.cardhub.view.impl;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JScrollPane;

import it.unibo.cardhub.view.api.DeckManagerView;
import it.unibo.cardhub.view.components.CHButton;
import it.unibo.cardhub.view.components.CHEntryPanel;
import it.unibo.cardhub.view.components.CHListHeader;
import it.unibo.cardhub.view.components.CHPanel;
import it.unibo.cardhub.view.components.CHTitle;
import it.unibo.cardhub.view.components.ScreenView;

/**
 * Swing implementation of the deck manager view.
 */
public final class DeckManagerViewImpl extends ScreenView implements DeckManagerView {
 
    private static final long serialVersionUID = 1L;
    private final CHPanel headerPanel;
    private final CHPanel listPanel;
    private final CHPanel footerPanel;
    private final CHPanel listContainer;
    private final CHButton createNewButton;
    private final CHButton backButton;
    private final JScrollPane scrollPane;
    private final CHListHeader listHeader;
    private ActionListener editListener;
    private ActionListener deleteListener;
    private int index;

    /**
     * Creates a new screen for visualization of cards.
     */
    public DeckManagerViewImpl() {
        headerPanel = new CHPanel();
        listPanel = new CHPanel();
        footerPanel = new CHPanel();
        listContainer = new CHPanel();

        createNewButton = new CHButton("Create new card");
        backButton = new CHButton("<");

        listHeader = new CHListHeader("Card name", "Value of the card");

        scrollPane = new JScrollPane(listPanel);

        manageHeaderPanel();
        manageListPanel();
        manageFooterPanel();

        listContainer.setLayout(new BorderLayout());
        listContainer.add(listHeader, BorderLayout.NORTH);
        listContainer.add(scrollPane, BorderLayout.CENTER);

        this.setLayout(new BorderLayout());
        this.add(headerPanel, BorderLayout.NORTH);
        this.add(scrollPane, BorderLayout.CENTER);
        this.add(footerPanel, BorderLayout.SOUTH);
    }

    private void manageHeaderPanel() {
        final JLabel title = new CHTitle("Deck manager");
        headerPanel.setLayout(new BorderLayout());
        headerPanel.add(title, BorderLayout.CENTER);
        headerPanel.add(backButton, BorderLayout.WEST);
        title.setHorizontalAlignment(CHTitle.CENTER);
        headerPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    }

    private void manageListPanel() {
        listPanel.setLayout(new BoxLayout(listPanel, BoxLayout.Y_AXIS));
        listPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    }

    private void manageFooterPanel() {
        footerPanel.add(createNewButton);
        footerPanel.setBorder(BorderFactory.createCompoundBorder(
            this.getBorder(),
            BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addCard(final String cardName, final int value) {
        final CHEntryPanel entry = new CHEntryPanel(cardName, String.valueOf(value), "Edit", "Delete");
        index++;

        entry.getFirstButton().setActionCommand(String.valueOf(index));
        entry.getSecondButton().setActionCommand(String.valueOf(index));
        entry.getFirstButton().addActionListener(editListener);
        entry.getSecondButton().addActionListener(deleteListener);

        listPanel.add(entry);

        listPanel.revalidate();
        listPanel.repaint();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void clearCards() {
        listPanel.removeAll();
        listPanel.revalidate();
        listPanel.repaint();
        index = 0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addCreateCardListener(final ActionListener listener) {
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
