package it.unibo.cardhub.view.impl;

import java.awt.BorderLayout;
import java.awt.Component;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JScrollPane;

import it.unibo.cardhub.model.logic.GameMode;
import it.unibo.cardhub.view.api.LoadMatchView;
import it.unibo.cardhub.view.components.CHButton;
import it.unibo.cardhub.view.components.CHEntryPanel;
import it.unibo.cardhub.view.components.CHPanel;
import it.unibo.cardhub.view.components.CHTitle;
import it.unibo.cardhub.view.components.ScreenView;

/**
 * Swing implementation of the load match view.
 */
public final class LoadMatchViewImpl extends ScreenView implements LoadMatchView {
        
    private final CHPanel headerPanel;
    private final CHPanel listPanel;
    private final CHButton backButton;
    private final JScrollPane scrollPane;

    /**
     * Creates a new screen for visualization of matches.
     */
    public LoadMatchViewImpl() {
        headerPanel = new CHPanel();
        listPanel = new CHPanel();

        backButton = new CHButton("<");

        scrollPane = new JScrollPane(listPanel);

        manageHeaderPanel();
        manageListPanel();
        loadDummyMatches();

        setUpListeners();

        this.setLayout(new BorderLayout());
        this.add(headerPanel, BorderLayout.NORTH);
        this.add(scrollPane, BorderLayout.CENTER);
    }

    private void manageHeaderPanel() {
        JLabel title = new CHTitle("Load Match");
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

    @Override
    public void addMatch(final String date, final GameMode mode) {
        CHEntryPanel entry = new CHEntryPanel(date, mode.getDisplayName(), "Load", "Delete");

        listPanel.add(entry);

        listPanel.revalidate();
        listPanel.repaint();
    }

    private void loadDummyMatches() {
        addMatch("01/01/2026", GameMode.FREE_PLAY);
        addMatch("29/02/2020", GameMode.CUSTOM);
        addMatch("31/12/1498", GameMode.FULL_GAME);

        listPanel.revalidate();
        listPanel.repaint();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void clearMatches() {
        listPanel.removeAll();
        listPanel.revalidate();
        listPanel.repaint();
    }

    private void setUpListeners() {
        backButton.addActionListener(e -> goToHome());

        boolean first = true;
        for (final Component c : listPanel.getComponents()) {
            if (c instanceof CHButton && first) {
                CHButton button = (CHButton) c;
                button.addActionListener(e -> goToMatch());
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
    public void goToHome() {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void goToMatch() {
    }
}
