package it.unibo.cardhub.view.impl;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.Objects;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import it.unibo.cardhub.controller.api.HomeController;
import it.unibo.cardhub.view.api.HomeView;
import it.unibo.cardhub.view.components.CHButton;
import it.unibo.cardhub.view.components.CHPanel;
import it.unibo.cardhub.view.components.CHTitle;
import it.unibo.cardhub.view.components.ScreenView;
import it.unibo.cardhub.view.components.CHStyles;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
 * Home screen view, showing the CardHub title, the main menu and a hero image.
 */
public final class HomeViewImpl extends ScreenView implements HomeView {
    public static final int WIDTH = 1200;
    public static final int HEIGHT = 1000;

    private static final long serialVersionUID = 1L;

    private static final String TITLE = "CardHub"; 

    private static final int COLS = 2;
    private static final int ROWS = 1;
    private static final int HERO_WRAPPER_WIDTH = 200;
    private static final int HERO_WRAPPER_HEIGHT = 200;
    private static final int STRUT_HEIGHT = 16;

    private final transient HomeController controller;

    private final JButton newMatchBtn;
    private final JButton manageDecksBtn;
    private final JButton exitBtn;

    /**
     * Builds the home view, laying out the title, the menu, the hero image and the exit button.
     * 
     * @param controller the controller for the current screen
     */
    public HomeViewImpl(final HomeController controller) {
        this.controller = Objects.requireNonNull(controller);

        exitBtn = new CHButton("Exit to desktop");
        newMatchBtn = new CHButton("New Match");
        manageDecksBtn = new CHButton("Manage Decks");

        setUpListeners();

        this.setLayout(new BorderLayout());
        this.add(new NorthPanel(), BorderLayout.NORTH);
        this.add(new CentralPanel(newMatchBtn, manageDecksBtn), BorderLayout.CENTER);
        this.add(new SouthPanel(exitBtn), BorderLayout.SOUTH);
    }

    private void setUpListeners() {
        newMatchBtn.addActionListener(e -> onNewMatch());
        manageDecksBtn.addActionListener(e -> onManageDecks());
        exitBtn.addActionListener(e -> onExit());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onNewMatch() {
        controller.newMatch();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onManageDecks() {
        // controller.manageDecks();
        JOptionPane.showMessageDialog(
            this,
            "Support us on patreon if you want us to develop this and more",
            "Functionality temporarily unavailable",
            JOptionPane.INFORMATION_MESSAGE
        );
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onExit() {
        if (confirmDialog("Are you sure you want to exit?", "Exit")) {
            controller.exit();
        }
    }

    private boolean confirmDialog(final String question, final String name) {
        return JOptionPane.showConfirmDialog(this, question, name, JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;
    }

    private static class NorthPanel extends CHPanel {
        private static final long serialVersionUID = 1L;
        private final JLabel title;

        NorthPanel() {
            title = new CHTitle(TITLE);
            super.add(title);
            title.setHorizontalAlignment(JLabel.CENTER);
        }
    }

    private static class CentralPanel extends CHPanel {
        private static final long serialVersionUID = 1L;

        CentralPanel(final JButton newMatchBtn, final JButton manageDecksBtn) {
            super.setLayout(new GridLayout(ROWS, COLS));
            super.add(new MenuPanel(newMatchBtn, manageDecksBtn));
            super.add(new HeroPanel());
        }
    }

    private static class SouthPanel extends CHPanel {
        private static final long serialVersionUID = 1L;

        SouthPanel(final JButton exitBtn) {
            super.add(exitBtn);
            super.setBorder(BorderFactory.createEmptyBorder(
                CHStyles.PADDING_STANDARD, CHStyles.PADDING_STANDARD, CHStyles.PADDING_STANDARD, CHStyles.PADDING_STANDARD
            ));
        }
    }

    private static class MenuPanel extends CHPanel {
        private static final long serialVersionUID = 1L;

        MenuPanel(final JButton newMatchBtn, final JButton manageDecksBtn) {
            super.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
            super.setBorder(BorderFactory.createEmptyBorder(
                CHStyles.PADDING_STANDARD, CHStyles.PADDING_STANDARD, CHStyles.PADDING_STANDARD, CHStyles.PADDING_STANDARD
            ));
            super.add(Box.createVerticalGlue());
            super.add(newMatchBtn);
            super.add(Box.createVerticalStrut(STRUT_HEIGHT));
            super.add(Box.createVerticalStrut(STRUT_HEIGHT));
            super.add(manageDecksBtn);
            super.add(Box.createVerticalGlue());
            for (final Component c : super.getComponents()) {
                if (c instanceof JButton button) {
                    button.setAlignmentX(CENTER_ALIGNMENT);
                }
            }
        }
    }

    private static class HeroPanel extends CHPanel {
        private static final long serialVersionUID = 1L;

        HeroPanel() {
            super.setPreferredSize(new Dimension(HERO_WRAPPER_WIDTH, HERO_WRAPPER_HEIGHT));
            final JLabel hero = new JLabel();
            hero.setIcon(new ImageIcon(HeroPanel.class.getResource("/it/unibo/cardhub/view/home_hero.jpg")));
            super.add(hero);
        }
    }

}
