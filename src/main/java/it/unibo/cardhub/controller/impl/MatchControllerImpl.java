package it.unibo.cardhub.controller.impl;

import java.util.Objects;
import java.util.Optional;

import javax.swing.JComponent;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.cardhub.controller.api.MatchController;
import it.unibo.cardhub.controller.api.Navigator;
import it.unibo.cardhub.model.domain.api.Card;
import it.unibo.cardhub.model.domain.exceptions.CardCollectionFullException;
import it.unibo.cardhub.model.logic.api.CardAction;
import it.unibo.cardhub.model.logic.api.ComparisonWinner;
import it.unibo.cardhub.model.logic.api.Match;
import it.unibo.cardhub.model.logic.api.PlayerEnum;
import it.unibo.cardhub.view.api.MatchView;
import it.unibo.cardhub.view.impl.MatchViewImpl;

/**
 * implementation of {@link MatchController}.
 */
public class MatchControllerImpl implements MatchController {

    private final Match model;
    private final MatchView view;
    private final Navigator navigator;

    /**
     * Constructor for the controller.
     * 
     * @param model match model
     * @param navigator screen navigator
     */
    public MatchControllerImpl(final Match model, final Navigator navigator) {
        this.model = Objects.requireNonNull(model, "no model supplied");
        this.navigator = Objects.requireNonNull(navigator, "no navigator supplied");
        view = new MatchViewImpl(this);
        startMatch();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @SuppressFBWarnings(value = "EI", justification =
            "The view JComponent must be returned by reference so it "
                    + "can be embedded in the real application window; "
                    + "cannot return a defensive copy for this purpuse.")
    public JComponent getView() {
        return (JComponent) view;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void playCard(final PlayerEnum owner, final Card<?> card) {
        checkTurn(owner, card);
        tryPlayCard(owner, card);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void discardCard(final PlayerEnum owner, final Card<?> card) {
        checkTurn(owner, card);
        moveCardFromFieldToPile(owner, card);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void drawFromDeck(final PlayerEnum owner) {
        checkTurn(owner);
        tryDrawCard(owner);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void reshuffleIntoDeck(final PlayerEnum owner) {
        checkTurn(owner);
        model.shufflePileIntoDeck(owner);
        view.updateDeck(owner, getDeckCount(owner));
        view.updateDiscardPile(owner, Optional.empty());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isEmptyDeck(final PlayerEnum owner) {
        return model.isEmptyDeck(owner);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isEmptyDiscardPile(final PlayerEnum owner) {
        return model.isEmptyDiscardPile(owner);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getDeckCount(final PlayerEnum owner) {
        return model.getPlayer(owner).getDeckCount();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void endTurn() {
        view.updateHiddenHand(getTurnPlayer(), 
                            model.getPlayer(getTurnPlayer()).getHand().size());
        model.changeTurn();
        view.showCurrentPlayer(getTurnPlayer());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void concede() {
        model.changeTurn();
        model.endMatch(model.getPlayer(getTurnPlayer()));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PlayerEnum getTurnPlayer() {
        return model.getTurnPlayer();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getPlayerName(final PlayerEnum player) {
        return model.getPlayer(player).getName();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getPlayFieldSize() {
        return model.getPlayFieldSize();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void compareCard(final Card<?> firstPlayerCard, final Card<?> secondPlayerCard) {
        Objects.requireNonNull(firstPlayerCard, "no firstPlayerCard provided");
        Objects.requireNonNull(secondPlayerCard, "no secondPlayerCard provided");
        final ComparisonWinner winner = model.compareCard(firstPlayerCard, secondPlayerCard);
        switch (winner) {
            case TIE:
                break;
            case PLAYER_1:
                updateWithCardAction(PlayerEnum.PLAYER_ONE, Competitor.WINNER);
                updateWithCardAction(PlayerEnum.PLAYER_TWO, Competitor.LOOSER);
                break;
            case PLAYER_2:
                updateWithCardAction(PlayerEnum.PLAYER_TWO, Competitor.WINNER);
                updateWithCardAction(PlayerEnum.PLAYER_ONE, Competitor.LOOSER);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void startTurn() {
        view.updateShowingHand(model.getTurnPlayer(), model.getPlayer(model.getTurnPlayer()).getHand().getCards());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void goToHome() {
        navigator.goHome();
    }

    private void checkTurn(final PlayerEnum owner, final Card<?> card) {
        Objects.requireNonNull(card, "no card provided");
        checkTurn(owner);
    }

    private void checkTurn(final PlayerEnum owner) {
        Objects.requireNonNull(owner, "no player provided");
        if (getTurnPlayer() != owner) {
            throw new IllegalStateException("it is not the player's turn");
        }
    }

    private void tryPlayCard(final PlayerEnum owner, final Card<?> card) {
        try {
            model.playCard(card, owner);
            view.updatePlayfield(owner, model.getPlayfield().getCards(model.getPlayer(owner)));
            view.updateShowingHand(owner, model.getPlayer(owner).getHand().getCards());
        } catch (final CardCollectionFullException e) {
            view.showInvalidAction(e.getMessage());
        }
    }

    private void moveCardFromFieldToPile(final PlayerEnum owner, final Card<?> card) {
        model.moveCardFromFieldToPile(card, owner);
        view.updateDiscardPile(owner, Optional.of(card));
        view.updatePlayfield(owner, model.getPlayfield().getCards(model.getPlayer(owner)));
    }

    private void tryDrawCard(final PlayerEnum player) {
        try {
            model.drawCard(player);
            view.updateShowingHand(player, model.getPlayer(player).getHand().getCards());
            view.updateDeck(player, model.getPlayer(player).getDeckCount());
        } catch (final CardCollectionFullException e) {
            view.showInvalidAction(e.getMessage());
        }
    }

    private void updateWithCardAction(final PlayerEnum player, final Competitor competitor) {
        final CardAction action = competitor == Competitor.LOOSER ? model.getLooserCardAction() : model.getWinnerCardAction();
        switch (action) {
            case NONE:
                return;
            case TO_HAND:
                view.updateShowingHand(player, model.getPlayer(player).getHand().getCards());
                break;
            case TO_PILE:
                view.updateDiscardPile(player, model.getPlayer(player).peekDiscardPile());
                break;
        }
        view.updatePlayfield(player, model.getPlayfield().getCards(model.getPlayer(player)));
    }

    /**
     * Starts the match and notifies the view of the initial state.
     */
    private void startMatch() {
        view.updateHiddenHand(PlayerEnum.PLAYER_ONE, 
                            model.getPlayer(PlayerEnum.PLAYER_ONE).getHand().size());
        view.updateHiddenHand(PlayerEnum.PLAYER_TWO, 
                            model.getPlayer(PlayerEnum.PLAYER_TWO).getHand().size());
        view.showCurrentPlayer(model.getTurnPlayer());
    }

    enum Competitor {
        WINNER,
        LOOSER
    }

}
