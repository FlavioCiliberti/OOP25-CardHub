package it.unibo.cardhub.view.home;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;

import it.unibo.cardhub.view.components.*;

public class HomeViewImpl extends ScreenView {
    final private JPanel north;
    final private JPanel central;
    final private JPanel south;

    final JButton newMatchBtn;
    final JButton loadMatchBtn;
    final JButton manageDecksBtn;
    final JButton exitBtn;

    public HomeViewImpl(){
        north = new CHPanel();
        JLabel title = new CHTitle("CardHub");
        north.add(title);
        title.setHorizontalAlignment(JLabel.CENTER);

        this.central = new CHPanel();
        central.setLayout(new BorderLayout());
        JPanel cLeft = new CHPanel();
        cLeft.setLayout(new BoxLayout(cLeft, BoxLayout.Y_AXIS));
        cLeft.setBorder(BorderFactory.createLineBorder(new Color(CHColor.TERTIARY.getCode())));
        cLeft.setBorder(BorderFactory.createCompoundBorder(
            this.getBorder(),
            BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        newMatchBtn = new CHButton("New Match");
        loadMatchBtn = new CHButton("Load Match");
        manageDecksBtn = new CHButton("Manage Decks");
        cLeft.add(newMatchBtn);
        cLeft.add(loadMatchBtn);
        cLeft.add(manageDecksBtn);

        

        JPanel cRight = new CHPanel();
        cRight.setBackground(Color.green);
        cRight.setPreferredSize(new Dimension(200,200));

        central.add(cLeft, BorderLayout.WEST);
        central.add(cRight, BorderLayout.EAST);


        south = new CHPanel();
        exitBtn = new CHButton("Exit to desktop");
        south.add(exitBtn);
        south.setBorder(BorderFactory.createCompoundBorder(
            this.getBorder(),
            BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));

        this.setLayout(new BorderLayout());
        this.add(north, BorderLayout.NORTH);
        this.add(central, BorderLayout.CENTER);
        this.add(south, BorderLayout.SOUTH);


    }


}
