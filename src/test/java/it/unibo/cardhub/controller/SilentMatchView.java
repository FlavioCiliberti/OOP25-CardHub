package it.unibo.cardhub.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import it.unibo.cardhub.model.domain.api.Card;
import it.unibo.cardhub.model.domain.api.PlayerEnum;
import it.unibo.cardhub.model.logic.api.ComparisonWinner;
import it.unibo.cardhub.view.api.MatchView;
import it.unibo.cardhub.view.components.ScreenView;

/**
 * Minimal {@link MatchView} used only to test {@link
 * it.unibo.cardhub.controller.impl.AbstractMatchController}.
 *
 * <p>The real {@code MatchViewImpl} cannot be used in an automated
 * test: its {@code showCurrentPlayer(...)} pops a blocking Swing dialog.
 * </p>
 */
public final class SilentMatchView extends ScreenView implements MatchView {

    private static final long serialVersionUID = 1L;

    private final List<PlayerEnum> hiddenHandUpdates = new ArrayList<>();
    private String lastInvalidActionMessage;
    private ComparisonWinner lastMatchEndedWinner;
    private PlayerEnum lastShownCurrentPlayer;

    @Override
    public void updateShowingHand(final PlayerEnum player, final List<Card<?>> cards) {
    }

    @Override
    public void updateHiddenHand(final PlayerEnum player, final int cardCount) {
        hiddenHandUpdates.add(player);
    }

    @Override
    public void updatePlayfield(final PlayerEnum player, final List<Card<?>> cards) {
    }

    @Override
    public void updateHiddenPlayfield(final PlayerEnum player, final int cardCount) {
    }

    @Override
    public void updateDiscardPile(final PlayerEnum player, final Optional<Card<?>> topCard) {
    }

    @Override
    public void updateDeck(final PlayerEnum player) {
    }

    @Override
    public void showCurrentPlayer(final PlayerEnum player) {
        lastShownCurrentPlayer = player;
    }

    @Override
    public void showMatchEnded(final ComparisonWinner winner) {
        lastMatchEndedWinner = winner;
    }

    @Override
    public void showInvalidAction(final String message) {
        lastInvalidActionMessage = message;
    }

    /**
     * Getter for the players with hidden hand.
     * 
     * @return a list of the players with hidden hand 
     */
    public List<PlayerEnum> getHiddenHandUpdates() {
        return List.copyOf(hiddenHandUpdates);
    }

    /**
     * Getter for the last invalid action message.
     * 
     * @return the last invalid action message
     */
    public String getLastInvalidActionMessage() {
        return lastInvalidActionMessage;
    }

    /**
     * Getter for the last match winner.
     * 
     * @return the last match winner
     */
    public ComparisonWinner getLastMatchEndedWinner() {
        return lastMatchEndedWinner;
    }

    /**
     * Getter for the last shown current player.
     * 
     * @return the last shown current player
     */
    public PlayerEnum getLastShownCurrentPlayer() {
        return lastShownCurrentPlayer;
    }
}
