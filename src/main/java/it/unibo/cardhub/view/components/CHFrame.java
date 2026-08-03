package it.unibo.cardhub.view.components;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import it.unibo.cardhub.controller.api.CreateMatchController;
import it.unibo.cardhub.view.impl.CreateMatchImpl;

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

            }

            @Override
            public JComponent getView() {
                // TODO Auto-generated method stub
                throw new UnsupportedOperationException("Unimplemented method 'getView'");
            }

            @Override
            public Map<Integer, String> getDecks() {
                int first = 1;
                int second = 2;
                String test1 = "test 1";
                String test2 = "test 2";

                Map<Integer, String> map = new HashMap<>();
                map.put(1, test1);
                map.put(2, test2);

                return map;
            }

            @Override
            public void createFreeGame(String player1Name, int player1DeckId, String player2Name, int player2DeckId) {

            }

            @Override
            public void createFullGame(String player1Name, String player2Name) {

            }

            @Override
            public void createCustomGame(String player1Name, int player1DeckId, String player2Name, int player2DeckId,
                    int maxHandSize, int StartingHandSize, int playerFieldSize, boolean autoDraw) {

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
