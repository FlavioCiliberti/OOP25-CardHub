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
        this.setSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.setMinimumSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.setVisible(true);
    }

}
