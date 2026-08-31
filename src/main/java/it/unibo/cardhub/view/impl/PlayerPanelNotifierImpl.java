package it.unibo.cardhub.view.impl;

import java.util.Map;
import java.util.Objects;

import it.unibo.cardhub.model.domain.api.Card;
import it.unibo.cardhub.model.domain.api.PlayerEnum;
import it.unibo.cardhub.view.api.PlayerPanel;
import it.unibo.cardhub.view.api.PlayerPanelNotifier;

/**
 * Implementation of PlayerPanelNotifier.
 */
class PlayerPanelNotifierImpl implements PlayerPanelNotifier {
    private final Map<PlayerEnum, PlayerPanel> playerPanels;

    /**
     * Constructor for PlayerPanelNotifier.
     * 
     * @param firstPlayerPanel player 1 panel
     * @param secondPlayerPanel player 2 panel
     */
    PlayerPanelNotifierImpl(final PlayerPanel firstPlayerPanel, final PlayerPanel secondPlayerPanel) {
        Objects.requireNonNull(firstPlayerPanel);
        Objects.requireNonNull(secondPlayerPanel);

        this.playerPanels = Map.of(PlayerEnum.PLAYER_ONE, firstPlayerPanel, PlayerEnum.PLAYER_TWO, secondPlayerPanel);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void mouseHovered(final Card<?> card, final PlayerEnum player) {
        playerPanels.get(player).updateDescriptionLabel(card);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void mouseExited(final PlayerEnum player) {
        playerPanels.get(player).removeDescription();
    }
}
