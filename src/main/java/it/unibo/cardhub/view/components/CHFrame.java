package it.unibo.cardhub.view.components;

import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class CHFrame extends JFrame {
    public CHFrame(){
        this.setTitle("Card Hub App");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel container = new JPanel();
        CardLayout layout = new CardLayout();
        container.setLayout(layout);

        JPanel screen1 = new JPanel();
        screen1.setBackground(Color.green);
        JPanel screen2 = new CHPanel();

        container.add(screen1, "1");
        container.add(screen2, "2");

        this.add(container);
        layout.show(container, "2");

        this.setSize(new Dimension(640, 400));
        this.setVisible(true);
    }

}
