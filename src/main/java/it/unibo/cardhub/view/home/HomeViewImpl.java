package it.unibo.cardhub.view.home;

import java.awt.BorderLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.BorderFactory;
import javax.swing.JButton;

import it.unibo.cardhub.view.components.*;

public class HomeViewImpl extends ScreenView {
    final private JPanel north;
    final private JPanel central;
    final private JPanel south;

    public HomeViewImpl(){
        this.north = new CHPanel();
        JLabel title = new CHTitle("CardHub");
        north.add(title);
        title.setHorizontalAlignment(JLabel.CENTER);

        this.central = new CHPanel();
        

        this.south = new CHPanel();
        JButton exitBtn = new CHButton("Exit to desktop");
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
