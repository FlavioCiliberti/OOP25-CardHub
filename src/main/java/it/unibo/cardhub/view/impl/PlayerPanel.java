package it.unibo.cardhub.view.impl;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;

import it.unibo.cardhub.model.domain.api.Card;
import it.unibo.cardhub.view.api.MatchView;
import it.unibo.cardhub.view.components.CHLabel;
import it.unibo.cardhub.view.components.CHPanel;
import it.unibo.cardhub.view.components.CHStyles;

/**
 * Represents the player area of the match view, containing the hand, the deck and the card descriptions.
 */
class PlayerPanel extends CHPanel {
    private static final int PADDING_ROW = 13;

    private final MatchView matchView;

    private final JPanel handPanel;
    private final JLabel nameLabel;
    private final JLabel deckLabel;
    private final JLabel deckSizeLabel;
    private final JLabel cardInfoLabel;
    private final boolean mirrored;

    /**
     * Constructor for PlayerPanel.
     * 
     * @param matchView the view this panle belongs to
     * @param playerName the name of the player
     * @param mirrored if true, the elements are going to be arranged in reverse order to create a mirrored version
     */
    public PlayerPanel(final MatchView matchView, final String playerName, final int deckSize, final boolean mirrored) {
        this.matchView = matchView;

        handPanel = new CHPanel(new FlowLayout(FlowLayout.LEFT, CHStyles.PADDING_STANDARD, CHStyles.PADDING_NONE));
        nameLabel = new CHLabel(playerName, SwingConstants.CENTER);
        deckLabel = new CHLabel(new ImageIcon(getClass().getResource("/it/unibo/cardhub/view/Back.png")));
        deckSizeLabel = new CHLabel(String.valueOf(deckSize), SwingConstants.CENTER);
        cardInfoLabel = new CHLabel("", SwingConstants.CENTER);
        this.mirrored = mirrored;

        this.managePanel();
    }

    //sets up the panel
    private void managePanel() {
        final JPanel firstRow = new CHPanel(new BorderLayout(CHStyles.PADDING_STANDARD, CHStyles.PADDING_NONE));
        final JPanel secondRow = new CHPanel(new BorderLayout());
        final JPanel handPanelWrapper = new CHPanel(new GridBagLayout());
        final JPanel deckWrapper = new CHPanel();

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED,
                                                        CHStyles.secondaryColor(), CHStyles.primaryColor()));

        firstRow.setBorder(BorderFactory.createEmptyBorder(CHStyles.PADDING_STANDARD, PADDING_ROW,
                                                            CHStyles.PADDING_STANDARD, PADDING_ROW));
        firstRow.add(cardInfoLabel, BorderLayout.CENTER);
        handPanelWrapper.add(handPanel);
        firstRow.add(handPanelWrapper, mirrored ? BorderLayout.WEST : BorderLayout.EAST);

        deckWrapper.add(deckLabel);
        deckLabel.setPreferredSize(new Dimension(MatchViewImpl.CARD_WIDTH, MatchViewImpl.CARD_HEIGHT));
        firstRow.add(deckWrapper, mirrored ? BorderLayout.EAST : BorderLayout.WEST);

        secondRow.setBorder(BorderFactory.createEmptyBorder(CHStyles.PADDING_STANDARD, CHStyles.PADDING_STANDARD,
                                                            CHStyles.PADDING_STANDARD, CHStyles.PADDING_STANDARD));
        secondRow.add(nameLabel, BorderLayout.CENTER);
        secondRow.add(deckSizeLabel, mirrored ? BorderLayout.EAST : BorderLayout.WEST);

        this.add(mirrored ? firstRow : secondRow);
        this.add(mirrored ? secondRow : firstRow);
    }

    /**
     * Repaints the hand.
     * 
     * @param cards the list of cards in the hand
     */
    public void updateHandPanel(final List<Card> cards) {
        for(Card card : cards) {
            final JLabel cardLabel = new CHLabel(new ImageIcon(getClass().getResource("/it/unibo/cardhub/io/Exodia.png")));
            cardLabel.setPreferredSize(new Dimension(MatchViewImpl.CARD_WIDTH, MatchViewImpl.CARD_HEIGHT));
            cardLabel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(final MouseEvent e) {
                    cardInfoLabel.setText("<html>"
                                            + "name" //placeholder for card name
                                            + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Value: "
                                            + card.value() + "<br>"
                                            + card.desc() + "</html>");
                }

                @Override
                public void mouseExited(final MouseEvent e) {
                    cardInfoLabel.setText("");
                }

                @Override
                public void mouseClicked(final MouseEvent e) {
                    matchView.changeSelectedCard(cardLabel);
                }
            });
            handPanel.add(cardLabel);
        }

        this.validate();
        this.repaint();
    }

    /**
     * Updates the deckSizeLabel and sets the deck to invisible if it's empty.
     * 
     * @param deckSize the current size of the deck
     */
    public void updateDeck(final int deckSize) {
        deckSizeLabel.setText(String.valueOf(deckSize));
        if (deckSize == 0) {
            deckLabel.setVisible(false);
        }
    }
}
