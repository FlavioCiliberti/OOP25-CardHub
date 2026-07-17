package it.unibo.cardhub.view.impl;

import java.awt.BorderLayout;
import java.awt.Component;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JScrollPane;

import it.unibo.cardhub.view.api.DeckManagerView;
import it.unibo.cardhub.view.components.CHButton;
import it.unibo.cardhub.view.components.CHEntryPanel;
import it.unibo.cardhub.view.components.CHPanel;
import it.unibo.cardhub.view.components.CHTitle;
import it.unibo.cardhub.view.components.ScreenView;

/**
 * Swing implementation of the deck manager view.
 */
public final class DeckManagerViewImpl extends ScreenView implements DeckManagerView {
        
    private final CHPanel headerPanel;
    private final CHPanel listPanel;
    private final CHPanel footerPanel;
    private final CHButton createNewButton;
    private final CHButton backButton;
    private final JScrollPane scrollPane;

    /**
     * Creates a new screen for visualization of cards.
     */
    public DeckManagerViewImpl() {
        headerPanel = new CHPanel();
        listPanel = new CHPanel();
        footerPanel = new CHPanel();

        createNewButton = new CHButton("Create new card");
        backButton = new CHButton("<");

        scrollPane = new JScrollPane(listPanel);

        manageHeaderPanel();
        manageListPanel();
        manageFooterPanel();
        loadDummyCards();

        setUpListeners();

        this.setLayout(new BorderLayout());
        this.add(headerPanel, BorderLayout.NORTH);
        this.add(scrollPane, BorderLayout.CENTER);
        this.add(footerPanel, BorderLayout.SOUTH);
    }

    private void manageHeaderPanel() {
        JLabel title = new CHTitle("Deck manager");
        headerPanel.setLayout(new BorderLayout());
        headerPanel.add(title, BorderLayout.CENTER);
        headerPanel.add(backButton, BorderLayout.WEST);
        title.setHorizontalAlignment(CHTitle.CENTER);
        headerPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    }

    private void manageListPanel(){
        listPanel.setLayout(new BoxLayout(listPanel, BoxLayout.Y_AXIS));
        listPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    }

    private void manageFooterPanel(){
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
        CHEntryPanel entry = new CHEntryPanel(cardName, String.valueOf(value), "Edit", "Delete");

        listPanel.add(entry);

        listPanel.revalidate();
        listPanel.repaint();
    }

    private void loadDummyCards() {
        addCard("Coppe", 4);
        addCard("Gengar", 220);
        addCard("Drago Magno", 1400);

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
    }

    private void setUpListeners() {
        createNewButton.addActionListener(e -> goToCardManager());
        backButton.addActionListener(e -> goToDecks());

        boolean first = true;
        for (final Component c : listPanel.getComponents()) {
            if (c instanceof CHButton && first) {
                CHButton button = (CHButton) c;
                button.addActionListener(e -> goToCardManager());
                first = false;
            }
            if (c instanceof CHButton && !first) {
                CHButton button = (CHButton) c;
                button.addActionListener(e -> {
                    for (final Component d : listPanel.getComponents()) {
                        if (d instanceof CHEntryPanel) {
                            CHEntryPanel entry = (CHEntryPanel) d;
                            if (entry.getSecondButton().equals(button)) {
                                listPanel.remove(entry);
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
    public void goToCardManager() {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void goToDecks() {
    }
}
