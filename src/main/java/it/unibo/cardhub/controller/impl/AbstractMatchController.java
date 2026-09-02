package it.unibo.cardhub.controller.impl;

import java.util.Objects;
import java.util.Optional;

import javax.swing.JComponent;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.cardhub.controller.api.MatchController;
import it.unibo.cardhub.controller.api.Navigator;
import it.unibo.cardhub.model.domain.api.Card;
import it.unibo.cardhub.model.domain.api.MatchState;
import it.unibo.cardhub.model.domain.api.PlayerEnum;
import it.unibo.cardhub.model.domain.exceptions.CardCollectionFullException;
import it.unibo.cardhub.model.domain.exceptions.EmptyCardCollectionException;
import it.unibo.cardhub.model.logic.api.CardAction;
import it.unibo.cardhub.model.logic.api.ComparisonWinner;
import it.unibo.cardhub.model.logic.api.MatchLogic;
import it.unibo.cardhub.view.api.MatchView;

/**
 * abstract implementation of {@link MatchController}.
 *
 *  @param <V> MatchView type
 *  @param <L> MatchLogic type
 */
public abstract class AbstractMatchController<V extends MatchView, L extends MatchLogic> implements MatchController {

    private final MatchState state;
    private final L logic;
    private final V view;
    private final Navigator navigator;

    /**
     * Constructor for the controller.
     *
     * @param state match state
     * @param logic match logic
     * @param navigator screen navigator
     */
    @SuppressFBWarnings(value = "MC_OVERRIDABLE_METHOD_CALL_IN_CONSTRUCTOR", justification =
        "createView() is called only for instantiating the view assotiated with the controller.")
    @SuppressWarnings("PMD.ConstructorCallsOverridableMethod")
    protected AbstractMatchController(final MatchState state, final L logic, final Navigator navigator) {
        this.state = Objects.requireNonNull(state, "no MatchState supplied");
        this.logic = Objects.requireNonNull(logic, "no MatchLogic supplied");
        this.navigator = Objects.requireNonNull(navigator, "no navigator supplied");
        view = createView();
        startMatch();
    }

    /**
     * Creates the view instance for this controller.
     *
     * @return the view to use
     */
    protected abstract V createView();

    /**
     * {@inheritDoc}
     */
    @Override
    public void endTurn() {
        getMatchView().updateHiddenHand(getTurnPlayer(), 
                            getState().getPlayer(getTurnPlayer()).getHand().size());
        onEndTurn();
        getMatchView().showCurrentPlayer(getTurnPlayer());
    }

    /**
     * {@inheritDoc}
     */
    @Override
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
        state.shufflePileIntoDeck(owner);
        view.updateDeck(owner, getDeckCount(owner));
        view.updateDiscardPile(owner, Optional.empty());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isEmptyDeck(final PlayerEnum owner) {
        return state.isEmptyDeck(owner);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isEmptyDiscardPile(final PlayerEnum owner) {
        return state.isEmptyDiscardPile(owner);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getDeckCount(final PlayerEnum owner) {
        return state.getPlayer(owner).getDeckCount();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void concede() {
        logic.changeTurn();
        state.endMatch(getTurnPlayer());
        view.showMatchEnded(state.getWinner().get());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final PlayerEnum getTurnPlayer() {
        return logic.getCurrentPlayer();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getPlayerName(final PlayerEnum player) {
        return state.getPlayer(player).getName();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getPlayFieldSize() {
        return state.getPlayFieldSize();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void compareCard(final Card<?> firstPlayerCard, final Card<?> secondPlayerCard) {
        Objects.requireNonNull(firstPlayerCard, "no firstPlayerCard provided");
        Objects.requireNonNull(secondPlayerCard, "no secondPlayerCard provided");
        final ComparisonWinner winner = logic.compareCard(firstPlayerCard, secondPlayerCard, state);
        switch (winner) {
            case TIE:
                updateWithCardAction(PlayerEnum.PLAYER_ONE, Competitor.LOOSER);
                updateWithCardAction(PlayerEnum.PLAYER_TWO, Competitor.LOOSER);
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
    @SuppressWarnings("PMD.EmptyCatchBlock")
    public void startTurn() {
        if (logic.isAutoDrawEnabled()) {
            try {
                state.drawCard(logic.getCurrentPlayer());
            } catch (final CardCollectionFullException | EmptyCardCollectionException e) {
                // Expected: the player doesn't draw if their hand is already full or the deck is empty
            }
        }
        view.updateShowingHand(getTurnPlayer(), state.getPlayer(getTurnPlayer()).getHand().getCards());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void goToHome() {
        navigator.goHome();
    }

    /**
     * Action to be performed during the change of a turn.
     * 
     * <p>this happens after the previous player hand gets covered 
     * but before the next player's turn notification shows up.
     * </p>
     */
    protected abstract void onEndTurn();

    /**
     * Match state getter.
     * 
     * @return the match state
     */
    protected MatchState getState() {
        return state;
    }

    /**
     * Match logic getter.
     *
     * @return the match state
     */
    protected L getLogic() {
        return logic;
    }

    /**
     * Match view getter.
     * 
     * @return the match state
     */
    protected V getMatchView() {
        return view;
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
            state.playCard(card, owner);
            view.updatePlayfield(owner, state.getPlayfield().getCards(owner));
            view.updateShowingHand(owner, state.getPlayer(owner).getHand().getCards());
        } catch (final CardCollectionFullException e) {
            view.showInvalidAction(e.getMessage());
        }
    }

    private void moveCardFromFieldToPile(final PlayerEnum owner, final Card<?> card) {
        state.moveCardFromFieldToPile(card, owner);
        view.updateDiscardPile(owner, Optional.of(card));
        view.updatePlayfield(owner, state.getPlayfield().getCards(owner));
    }

    private void tryDrawCard(final PlayerEnum player) {
        try {
            state.drawCard(player);
            view.updateShowingHand(player, state.getPlayer(player).getHand().getCards());
            view.updateDeck(player, state.getPlayer(player).getDeckCount());
        } catch (final CardCollectionFullException e) {
            view.showInvalidAction(e.getMessage());
        }
    }

    private void updateWithCardAction(final PlayerEnum player, final Competitor competitor) {
        final CardAction action = competitor == Competitor.LOOSER ? logic.getLoserCardAction() : logic.getWinnerCardAction();
        switch (action) {
            case NONE:
                return;
            case TO_DECK:
                view.updateDeck(player, state.getPlayer(player).getDeckCount());
                break;
            case TO_PILE:
                view.updateDiscardPile(player, state.getPlayer(player).peekDiscardPile());
                break;
        }
        view.updatePlayfield(player, state.getPlayfield().getCards(player));
    }

    /**
     * Starts the match and notifies the view of the initial state.
     */
    private void startMatch() {
        view.updateHiddenHand(PlayerEnum.PLAYER_ONE, 
                            state.getPlayer(PlayerEnum.PLAYER_ONE).getHand().size());
        view.updateHiddenHand(PlayerEnum.PLAYER_TWO, 
                            state.getPlayer(PlayerEnum.PLAYER_TWO).getHand().size());
        view.showCurrentPlayer(getTurnPlayer());
    }

    private enum Competitor {
        WINNER,
        LOOSER
    }

}
