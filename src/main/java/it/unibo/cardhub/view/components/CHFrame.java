package it.unibo.cardhub.view.components;

import java.awt.*;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import it.unibo.cardhub.view.home.HomeViewImpl;

public class CHFrame extends JFrame {
    public CHFrame(){
        this.setTitle("Card Hub App");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel container = new JPanel();
        CardLayout layout = new CardLayout();
        container.setLayout(layout);

        JPanel screen1 = new JPanel();
        screen1.setBackground(Color.green);
        screen1.setBackground(new Color(CHColor.TERTIARY.getCode()));
        JPanel screen2 = new HomeViewImpl();

        container.add(screen1, "1");
        container.add(screen2, "2");

        this.add(container);
        layout.show(container, "2");

        this.setSize(new Dimension(640, 400));
        this.setVisible(true);
    }

}
