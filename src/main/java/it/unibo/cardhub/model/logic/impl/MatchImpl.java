package it.unibo.cardhub.model.logic.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

import it.unibo.cardhub.model.domain.api.Card;
import it.unibo.cardhub.model.domain.api.MatchState;
import it.unibo.cardhub.model.domain.api.Player;
import it.unibo.cardhub.model.domain.api.Playfield;
import it.unibo.cardhub.model.domain.exceptions.CardCollectionFullException;
import it.unibo.cardhub.model.domain.impl.MatchStateImpl;
import it.unibo.cardhub.model.logic.api.Match;
import it.unibo.cardhub.model.logic.api.MatchLogic;
import it.unibo.cardhub.model.logic.api.PlayerEnum;
import it.unibo.cardhub.model.logic.api.PointTracker;
import it.unibo.cardhub.model.logic.api.CardAction;
import it.unibo.cardhub.model.logic.api.ComparisonWinner;

/**
 * an implementation of Match.
 */
class MatchImpl implements Match {
    private final MatchState matchState;
    private final MatchLogic matchLogic;

    private final boolean autoDraw;

    /**
     * Match constructor.
     * 
     * @param player1 the first player
     * @param player2 the second player
     * @param playerFieldSize maximum number of cards on the field per player
     * @param autoDraw should the turn player draw a card on turn start
     * @param matchLogic the match logic
     */
    MatchImpl(final Player player1, final Player player2,
                        final int playerFieldSize, final boolean autoDraw,
                        final MatchLogic matchLogic) {
        matchState = new MatchStateImpl(new ArrayList<>(Arrays.asList(player1, player2)), playerFieldSize);

        this.matchLogic = matchLogic;
        this.autoDraw = autoDraw;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void drawCard(final PlayerEnum player) throws CardCollectionFullException {
        this.getPlayer(player).drawCard();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void playCard(final Card<?> card, final PlayerEnum playerEnum) throws CardCollectionFullException {
        final Player player = matchState.getPlayer(playerEnum);

        if (matchState.getPlayfield().canAddCard(player)) {
            player.playCard(card);
            matchState.getPlayfield().addCard(player, card);
        } else {
            throw new CardCollectionFullException("Player's field is full!");
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void moveCardFromFieldToPile(final Card<?> card, final PlayerEnum playerEnum) {
        matchState.getPlayer(playerEnum).putInPile(card);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ComparisonWinner compareCard(final Card<?> firstPlayerCard, final Card<?> secondPlayerCard) {
        return matchLogic.compareCard(firstPlayerCard, secondPlayerCard, matchState);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Player getPlayer(final PlayerEnum player) {
        return matchState.getPlayer(player);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PlayerEnum getEnum(final Player player) {
        return matchState.getEnum(player);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PlayerEnum getTurnPlayer() {
        return matchLogic.getCurrentPlayer();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @SuppressWarnings("PMD.EmptyCatchBlock")
    public void changeTurn() {
        this.matchLogic.changeTurn();
        if (autoDraw) {
            try {
                this.drawCard(this.getTurnPlayer());
            } catch (final CardCollectionFullException e) {
                // Expected: the player doesn't draw if their hand is already full
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Player> getWinner() {
        return matchState.getWinner();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void endMatch(final Player player) {
        matchState.endMatch(player);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isFinished() {
        return matchState.isFinished();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Playfield getPlayfield() {
        return matchState.getPlayfield();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getPlayFieldSize() {
        return getPlayfield().getMaxCardsPerPlayer();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CardAction getWinnerCardAction() {
        return matchLogic.getWinnerCardAction();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CardAction getLooserCardAction() {
        return matchLogic.getLooserCardAction();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void shufflePileIntoDeck(final PlayerEnum player) {
        matchState.getPlayer(player).shufflePileIntoDeck();
    }

    @Override
    public boolean isEmptyDeck(final PlayerEnum owner) {
        return matchState.getPlayer(owner).hasEmptyDeck();
    }

    @Override
    public boolean isEmptyDiscardPile(final PlayerEnum owner) {
        return matchState.getPlayer(owner).hasEmptyDiscardPile();
    }

    @Override
    public int getPlayerPoints(final PlayerEnum player) {
        if (matchLogic instanceof PointTracker pointTracker) {
            return pointTracker.getPoints(player);
        }

        throw new UnsupportedOperationException("This match does not track points");
    }

    @Override
    public ComparisonWinner getWinningPlayer() {
        if (matchLogic instanceof PointTracker pointTracker) {
            return pointTracker.getWinningPlayer();
        }

        throw new UnsupportedOperationException("This match does not track points");
    }
}
