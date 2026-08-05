package it.unibo.cardhub.view.components;

import java.awt.Dimension;

// import java.awt.*;
// import javax.swing.JPanel;
// import it.unibo.cardhub.view.impl.HomeViewImpl;

import javax.swing.JFrame;

//IMPORTANT; SERVES FOR UI TESTING PURPUSES ONLY (FOR NOW)
/**
 * Custom Frame component used as the the application GUI.
 */
public final class CHFrame extends JFrame {
    private static final long serialVersionUID = 1L;
    private static final int SCREEN_WIDTH = 640;
    private static final int SCREEN_HEIGHT = 400;

    /**
     * 
     */
    public CHFrame() {
        this.setTitle("Card Hub App");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        // final JPanel container = new JPanel();
        // final CardLayout layout = new CardLayout();
        // container.setLayout(layout);

        // final JPanel screen1 = new JPanel();
        // screen1.setBackground(Color.green);
        // screen1.setBackground(new Color(CHColor.PRIMARY.getCode()));
        // final JPanel screen2 = new HomeViewImpl();

        // container.add(screen1, "1");
        // container.add(screen2, "2");

        // this.add(container);
        // layout.show(container, "1");

        this.setSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.setMinimumSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.setVisible(true);
    }

}
