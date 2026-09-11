package it.unibo.cardhub.view.components;

import java.awt.Dimension;

import javax.swing.JFrame;

//IMPORTANT; SERVES FOR UI TESTING PURPUSES ONLY (FOR NOW)
/**
 * Custom Frame component used as the the application GUI.
 */
public final class CHFrame extends JFrame {
    private static final long serialVersionUID = 1L;
    private static final int SCREEN_WIDTH = 900;
    private static final int SCREEN_HEIGHT = 800;

    /**
     * 
     */
    public CHFrame() {
        customize();
    }

    private void customize() {
        super.setTitle("Card Hub App");
        super.setDefaultCloseOperation(EXIT_ON_CLOSE);
        super.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        super.setMinimumSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        super.pack();
        super.setVisible(true);
    }

}
