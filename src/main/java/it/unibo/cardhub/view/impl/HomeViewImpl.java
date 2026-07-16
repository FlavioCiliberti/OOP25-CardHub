package it.unibo.cardhub.view.impl;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

import it.unibo.cardhub.view.api.HomeView;
import it.unibo.cardhub.view.components.CHButton;
import it.unibo.cardhub.view.components.CHPanel;
import it.unibo.cardhub.view.components.CHTitle;
import it.unibo.cardhub.view.components.ScreenView;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
 * Home screen view, showing the CardHub title, the main menu and a hero image.
 */
public final class HomeViewImpl extends ScreenView implements HomeView {
    private static final long serialVersionUID = 1L;

    private static final int PADDING = 10;

    private static final int COLS = 2;
    private static final int ROWS = 1;
    private static final int HERO_WRAPPER_WIDTH = 200;
    private static final int HERO_WRAPPER_HEIGHT = 200;
    private static final int STRUT_HEIGHT = 16;

    private final JPanel north;
    private final JPanel central;
    private final JPanel south;

    private final JButton newMatchBtn;
    private final JButton loadMatchBtn;
    private final JButton manageDecksBtn;
    private final JButton exitBtn;

    /**
     * Builds the home view, laying out the title, the menu, the hero image and the exit button.
     */
    public HomeViewImpl() {
        north = new CHPanel();
        central = new CHPanel();
        south = new CHPanel();

        exitBtn = new CHButton("Exit to desktop");
        newMatchBtn = new CHButton("New Match");
        loadMatchBtn = new CHButton("Load Match");
        manageDecksBtn = new CHButton("Manage Decks");

        setUpListeners();

        manageNorthPanel();
        manageCentralPanel();
        manageSouthPanel();

        this.setLayout(new BorderLayout());
        this.add(north, BorderLayout.NORTH);
        this.add(central, BorderLayout.CENTER);
        this.add(south, BorderLayout.SOUTH);
    }

    private void manageNorthPanel() {
        final JLabel title = new CHTitle("CardHub");
        north.add(title);
        title.setHorizontalAlignment(JLabel.CENTER);
    }

    private void manageCentralPanel() {
        central.setLayout(new GridLayout(ROWS, COLS));

        final JPanel menu = createMenu();
        final JPanel heroWrapper = createHeroWrapper();

        central.add(menu);
        central.add(heroWrapper);
    }

    private void manageSouthPanel() {
        south.add(exitBtn);
        south.setBorder(BorderFactory.createCompoundBorder(
            this.getBorder(),
            BorderFactory.createEmptyBorder(PADDING, PADDING, PADDING, PADDING)
        ));
    }

    private JPanel createMenu() {
        final JPanel menu = new CHPanel();

        menu.setLayout(new BoxLayout(menu, BoxLayout.Y_AXIS));
        menu.setBorder(BorderFactory.createCompoundBorder(
            this.getBorder(),
            BorderFactory.createEmptyBorder(PADDING, PADDING, PADDING, PADDING)
        ));
        menu.add(Box.createVerticalGlue());
        menu.add(newMatchBtn);
        menu.add(Box.createVerticalStrut(STRUT_HEIGHT));
        menu.add(loadMatchBtn);
        menu.add(Box.createVerticalStrut(STRUT_HEIGHT));
        menu.add(manageDecksBtn);
        menu.add(Box.createVerticalGlue());
        for (final Component c : menu.getComponents()) {
            if (c instanceof JButton button) {
                button.setAlignmentX(CENTER_ALIGNMENT);
            }
        }

        return menu;
    }

    private JPanel createHeroWrapper() {
        final JPanel heroWrapper = new CHPanel();

        heroWrapper.setPreferredSize(new Dimension(HERO_WRAPPER_WIDTH, HERO_WRAPPER_HEIGHT));
        final JLabel hero = new JLabel();
        hero.setIcon(new ImageIcon(getClass().getResource("/it/unibo/cardhub/view/home_hero.jpg")));
        heroWrapper.add(hero);

        return heroWrapper;
    }

    private void setUpListeners() {
        newMatchBtn.addActionListener(e -> goToNewMatch());
        loadMatchBtn.addActionListener(e -> goToLoadMatch());
        manageDecksBtn.addActionListener(e -> goToManageDecks());
        exitBtn.addActionListener(e -> exit());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void goToNewMatch() {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void goToLoadMatch() {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void goToManageDecks() {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void exit() {
    }
}
