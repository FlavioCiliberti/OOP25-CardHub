package it.unibo.cardhub.view.home;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import it.unibo.cardhub.view.components.*;

public class HomeViewImpl extends ScreenView {
    final private JPanel north;
    final private JPanel central;
    final private JPanel south;

    final private JButton newMatchBtn;
    final private JButton loadMatchBtn;
    final private JButton manageDecksBtn;
    final private JButton exitBtn;

    public HomeViewImpl(){
        north = new CHPanel();
        central = new CHPanel();
        south = new CHPanel();

        exitBtn = new CHButton("Exit to desktop");
        newMatchBtn = new CHButton("New Match");
        loadMatchBtn = new CHButton("Load Match");
        manageDecksBtn = new CHButton("Manage Decks");

        manageNorthPanel();
        manageCentralPanel();
        manageSouthPanel();

        this.setLayout(new BorderLayout());
        this.add(north, BorderLayout.NORTH);
        this.add(central, BorderLayout.CENTER);
        this.add(south, BorderLayout.SOUTH);
    }

    private void manageNorthPanel(){
        JLabel title = new CHTitle("CardHub");
        north.add(title);
        title.setHorizontalAlignment(JLabel.CENTER);
    }
    
    private void manageCentralPanel(){
        central.setLayout(new GridLayout(1, 2));

        JPanel menu = createMenu();
        JPanel heroWrapper = createHeroWrapper();

        central.add(menu);
        central.add(heroWrapper);
    }

    private void manageSouthPanel(){
        south.add(exitBtn);
        south.setBorder(BorderFactory.createCompoundBorder(
            this.getBorder(),
            BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));
    }

    private JPanel createMenu(){
        JPanel menu = new CHPanel();

        menu.setLayout(new BoxLayout(menu, BoxLayout.Y_AXIS));
        menu.setBorder(BorderFactory.createCompoundBorder(
            this.getBorder(),
            BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        menu.add(Box.createVerticalGlue());
        menu.add(newMatchBtn);
        menu.add(Box.createVerticalStrut(16));
        menu.add(loadMatchBtn);
        menu.add(Box.createVerticalStrut(16));
        menu.add(manageDecksBtn);
        menu.add(Box.createVerticalGlue());
        for (Component c : menu.getComponents()) {
            if (c instanceof JButton button) {
                button.setAlignmentX(Component.CENTER_ALIGNMENT);
            }
        }

        return menu;
    }

    private JPanel createHeroWrapper(){
        JPanel heroWrapper = new CHPanel();

        heroWrapper.setPreferredSize(new Dimension(200,200));
        JLabel hero = new JLabel();
        hero.setIcon(new ImageIcon(getClass().getResource("/it/unibo/cardhub/view/home_hero.jpg")));
        heroWrapper.add(hero);

        return heroWrapper;
    }
}
