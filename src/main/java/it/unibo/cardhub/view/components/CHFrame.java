package it.unibo.cardhub.view.components;

import java.awt.*;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import it.unibo.cardhub.controller.api.CreateMatchController;
import it.unibo.cardhub.view.creatematch.CreateMatchImpl;

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
        JPanel screen2 = new CreateMatchImpl(new CreateMatchController() {

            @Override
            public void goBack() {
                // TODO Auto-generated method stub
                throw new UnsupportedOperationException("Unimplemented method 'goBack'");
            }

            @Override
            public JComponent getView() {
                // TODO Auto-generated method stub
                throw new UnsupportedOperationException("Unimplemented method 'getView'");
            }

            @Override
            public Map<Integer, String> getDecks() {
                // TODO Auto-generated method stub
                throw new UnsupportedOperationException("Unimplemented method 'getDecks'");
            }

            @Override
            public void createFreeGame(String player1Name, int player1DeckId, String player2Name, int player2DeckId) {
                // TODO Auto-generated method stub
                throw new UnsupportedOperationException("Unimplemented method 'createFreeGame'");
            }

            @Override
            public void createFullGame(String player1Name, int player1DeckId, String player2Name, int player2DeckId) {
                // TODO Auto-generated method stub
                throw new UnsupportedOperationException("Unimplemented method 'createFullGame'");
            }

            @Override
            public void createCustomGame(String player1Name, int player1DeckId, String player2Name, int player2DeckId,
                    int maxHandSize, int playerFieldSize, boolean autoDraw) {
                // TODO Auto-generated method stub
                throw new UnsupportedOperationException("Unimplemented method 'createCustomGame'");
            }
            
        });

        container.add(screen1, "1");
        container.add(screen2, "2");

        this.add(container);
        layout.show(container, "2");
        //this.setSize(new Dimension(640, 400));
        this.setSize(new Dimension(470, 710));

        this.setVisible(true);
    }

}
