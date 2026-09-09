package it.unibo.cardhub.view.impl;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.Map;
import java.util.Objects;

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
import it.unibo.cardhub.model.domain.api.PlayerEnum;
import it.unibo.cardhub.view.api.PlayerPanel;
import it.unibo.cardhub.view.api.PlayfieldListener;
import it.unibo.cardhub.view.components.CHLabel;
import it.unibo.cardhub.view.components.CHPanel;
import it.unibo.cardhub.view.components.CHStyles;
import it.unibo.cardhub.view.util.ImageResolver;

/**
 * Implementation of PlayerPanel.
 */
@SuppressFBWarnings(
    value = "SE_TRANSIENT_FIELD_NOT_RESTORED",
    justification = "This class is never saved on fil or transmited: is just "
        + "a Swing view. It is 'Serializable' just by inheritance form JPanel, "
        + "not by choice or because it is usefull."
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
        Objects.requireNonNull(controller, "controller can't be null");
        Objects.requireNonNull(player, "player can't be null");

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
        deckLabel.setPreferredSize(new Dimension(ImageResolver.CARD_WIDTH, ImageResolver.CARD_HEIGHT));
        this.updateDeck();
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
    public void updateShowingHandPanel(final List<Card<?>> cards) {
        Objects.requireNonNull(cards, "hand can't be null");

        handPanel.removeAll();
        for (final Card<?> card : cards) {
            final JLabel cardLabel = new CHLabel(ImageResolver.resolve(card));
            cardLabel.setPreferredSize(new Dimension(ImageResolver.CARD_WIDTH, ImageResolver.CARD_HEIGHT));
            cardLabel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(final MouseEvent e) {
                    PlayerPanelImpl.this.updateDescriptionLabel(card);
                }

                @Override
                public void mouseExited(final MouseEvent e) {
                    PlayerPanelImpl.this.removeDescription();
                }

                @Override
                public void mouseClicked(final MouseEvent e) {
                    controller.playCard(player, card);
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
    public void updateHiddenHandPanel(final int cardCount) {
        handPanel.removeAll();
        for (int i = 0; i < cardCount; i++) {
            final JLabel cardLabel = new CHLabel(ImageResolver.resolveBack());
            cardLabel.setPreferredSize(new Dimension(ImageResolver.CARD_WIDTH, ImageResolver.CARD_HEIGHT));
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

    private void updateDescriptionLabel(final Card<?> card) {
        cardInfoLabel.setText("<html>"
                                + card.name().orElse("")
                                + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Value: "
                                + card.value() + "<br>"
                                + card.desc().orElse("") + "</html>");
    }

    private void removeDescription() {
        cardInfoLabel.setText("");
    }

    /**
     * Notifier for PlayerPanelImpl.
     */
    static class PlayerPanelNotifier implements PlayfieldListener {
        private final Map<PlayerEnum, PlayerPanel> playerPanels;

        /**
         * Constructor for PlayerPanelNotifier.
         * 
         * @param firstPlayerPanel player 1 panel
         * @param secondPlayerPanel player 2 panel
         */
        PlayerPanelNotifier(final PlayerPanel firstPlayerPanel, final PlayerPanel secondPlayerPanel) {
            Objects.requireNonNull(firstPlayerPanel);
            Objects.requireNonNull(secondPlayerPanel);

            this.playerPanels = Map.of(PlayerEnum.PLAYER_ONE, firstPlayerPanel, PlayerEnum.PLAYER_TWO, secondPlayerPanel);
        }

        /**
         * Updates the card description when the mouse hovers over a card.
         * 
         * @param card the card
         * @param player the player the card belongs to
         */
        @Override
        public void mouseHovered(final Card<?> card, final PlayerEnum player) {
            Objects.requireNonNull(card, "card can't be null");
            Objects.requireNonNull(player, "PlayerEnum can't be null");

            if (playerPanels.get(player) instanceof PlayerPanelImpl panel) {
                panel.updateDescriptionLabel(card);
            }
        }

        /**
         * Removes the card description when the mouse stops hovering a card.
         * 
         * @param player the player the card belonged to
         */
        @Override
        public void mouseExited(final PlayerEnum player) {
            Objects.requireNonNull(player, "PlayerEnum can't be null");

            if (playerPanels.get(player) instanceof PlayerPanelImpl panel) {
                panel.removeDescription();
            }
        }
    }
}
