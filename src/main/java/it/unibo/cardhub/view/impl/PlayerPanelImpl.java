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

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.cardhub.controller.api.MatchController;
import it.unibo.cardhub.model.domain.api.Card;
import it.unibo.cardhub.model.logic.api.PlayerEnum;
import it.unibo.cardhub.view.api.PlayerPanel;
import it.unibo.cardhub.view.components.CHLabel;
import it.unibo.cardhub.view.components.CHPanel;
import it.unibo.cardhub.view.components.CHStyles;

/**
 * Implementation of PlayerPanel.
 */
@SuppressFBWarnings(
    value = "SE_TRANSIENT_FIELD_NOT_RESTORED",
    justification = "Questa classe non viene mai salvata su file o trasmessa: è solo "
        + "una view Swing. È 'Serializable' solo perché lo eredita da JPanel, non "
        + "perché ci serve davvero."
)
final class PlayerPanelImpl extends CHPanel implements PlayerPanel {
    private static final int PADDING_ROW = 13;
    private static final int PADDING_DECK_SIZE = 30;

    private static final long serialVersionUID = 1L;

    private final transient MatchController controller;
    private final transient PlayerEnum player;

    private final JPanel handPanel;
    private final JLabel nameLabel;
    private final JLabel deckLabel;
    private final JLabel deckSizeLabel;
    private final JLabel cardInfoLabel;
    private final boolean mirrored;

    /**
     * Constructor for PlayerPanel.
     * 
     * @param controller the Match controller
     * @param player the player this panel belongs to
     * @param mirrored if true, the elements are going to be arranged in reverse order to create a mirrored version
     */
    PlayerPanelImpl(final MatchController controller, final PlayerEnum player, final boolean mirrored) {
        this.controller = controller;
        this.player = player;

        handPanel = new CHPanel(new FlowLayout(FlowLayout.LEFT, CHStyles.PADDING_STANDARD, CHStyles.PADDING_NONE));
        nameLabel = new CHLabel(controller.getPlayerName(player), SwingConstants.CENTER);
        deckLabel = new CHLabel(new ImageIcon(getClass().getResource("/it/unibo/cardhub/view/Back.png")));
        deckSizeLabel = new CHLabel(String.valueOf(controller.getDeckCount(player)), CHStyles.primaryColor());
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
        deckLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(final MouseEvent e) {
                controller.drawFromDeck(player);
            }
        });
        firstRow.add(deckWrapper, mirrored ? BorderLayout.EAST : BorderLayout.WEST);

        secondRow.setBorder(BorderFactory.createEmptyBorder(CHStyles.PADDING_STANDARD, CHStyles.PADDING_STANDARD,
                                                            CHStyles.PADDING_STANDARD, CHStyles.PADDING_STANDARD));
        secondRow.add(nameLabel, BorderLayout.CENTER);
        deckSizeLabel.setBorder(BorderFactory.createEmptyBorder(CHStyles.PADDING_NONE, PADDING_DECK_SIZE,
                                                                CHStyles.PADDING_NONE, PADDING_DECK_SIZE));
        secondRow.add(deckSizeLabel, mirrored ? BorderLayout.EAST : BorderLayout.WEST);

        this.add(mirrored ? firstRow : secondRow);
        this.add(mirrored ? secondRow : firstRow);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateHandPanel(final List<Card<?>> cards) {
        handPanel.removeAll();
        for (final Card<?> card : cards) {
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
                    controller.changeSelectedCard(cardLabel, card, player);
                }
            });
            handPanel.add(cardLabel);
        }

        this.validate();
        this.repaint();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateDeck() {
        deckSizeLabel.setText(String.valueOf(controller.getDeckCount(player)));
        deckLabel.setVisible(!controller.isEmptyDeck(player));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addToPanel(final JPanel panel, final Object constraints) {
        panel.add(this, constraints);
    }
}
