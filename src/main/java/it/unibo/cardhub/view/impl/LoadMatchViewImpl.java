package it.unibo.cardhub.view.impl;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JScrollPane;

import it.unibo.cardhub.model.logic.GameMode;
import it.unibo.cardhub.view.api.LoadMatchView;
import it.unibo.cardhub.view.components.CHButton;
import it.unibo.cardhub.view.components.CHEntryPanel;
import it.unibo.cardhub.view.components.CHListHeader;
import it.unibo.cardhub.view.components.CHPanel;
import it.unibo.cardhub.view.components.CHTitle;
import it.unibo.cardhub.view.components.ScreenView;

/**
 * Swing implementation of the load match view.
 */
public final class LoadMatchViewImpl extends ScreenView implements LoadMatchView {

    private static final long serialVersionUID = 1L;
    private final CHPanel headerPanel;
    private final CHPanel listPanel;
    private final CHPanel listContainer;
    private final CHButton backButton;
    private final JScrollPane scrollPane;
    private final CHListHeader listHeader;
    private ActionListener loadListener;
    private ActionListener deleteListener;
    private int index;

    /**
     * Creates a new screen for visualization of matches.
     */
    public LoadMatchViewImpl() {
        headerPanel = new CHPanel();
        listPanel = new CHPanel();
        listContainer = new CHPanel();

        backButton = new CHButton("<");

        listHeader = new CHListHeader("Match date", "Game mode");

        scrollPane = new JScrollPane(listPanel);

        manageHeaderPanel();
        manageListPanel();

        listContainer.setLayout(new BorderLayout());
        listContainer.add(listHeader, BorderLayout.NORTH);
        listContainer.add(scrollPane, BorderLayout.CENTER);

        this.setLayout(new BorderLayout());
        this.add(headerPanel, BorderLayout.NORTH);
        this.add(scrollPane, BorderLayout.CENTER);
    }

    private void manageHeaderPanel() {
        final JLabel title = new CHTitle("Load Match");
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

    /**
     * {@inheritDoc}
     */
    @Override
    public void addMatch(final String date, final GameMode mode) {
        final CHEntryPanel entry = new CHEntryPanel(date, mode.getDisplayName(), "Load", "Delete");
        index++;

        entry.getFirstButton().setActionCommand(String.valueOf(index));
        entry.getSecondButton().setActionCommand(String.valueOf(index));
        entry.getFirstButton().addActionListener(loadListener);
        entry.getSecondButton().addActionListener(deleteListener);

        listPanel.add(entry);

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
        index = 0;
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
    public void addLoadListener(final ActionListener listener) {
        this.loadListener = listener;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addDeleteListener(final ActionListener listener) {
        this.deleteListener = listener;
    }
}
