package it.unibo.cardhub.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import javax.swing.JComponent;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unibo.cardhub.controller.impl.AbstractMatchController;
import it.unibo.cardhub.controller.impl.CardLayoutNavigator;
import it.unibo.cardhub.model.domain.api.Card;
import it.unibo.cardhub.model.domain.api.Deck;
import it.unibo.cardhub.model.domain.api.Hand;
import it.unibo.cardhub.model.domain.api.MatchState;
import it.unibo.cardhub.model.domain.api.Player;
import it.unibo.cardhub.model.domain.api.PlayerEnum;
import it.unibo.cardhub.model.domain.attributes.Suit;
import it.unibo.cardhub.model.domain.exceptions.CardCollectionFullException;
import it.unibo.cardhub.model.domain.exceptions.EmptyCardCollectionException;
import it.unibo.cardhub.model.domain.exceptions.FieldFullException;
import it.unibo.cardhub.model.domain.impl.CardImpl;
import it.unibo.cardhub.model.domain.impl.DeckImpl;
import it.unibo.cardhub.model.domain.impl.MatchStateImpl;
import it.unibo.cardhub.model.domain.impl.PlayerImpl;
import it.unibo.cardhub.model.logic.api.CardAction;
import it.unibo.cardhub.model.logic.impl.StandardMatchLogic;

/**
 * Test class for the shared controller logic in {@link
 * AbstractMatchController}.
 */
final class MatchControllerTest {

    private static final int MAX_HAND_SIZE = 6;
    private static final int STARTING_HAND_SIZE = 3;
    private static final int MAX_FIELD_SIZE = 3;
    private static final int CARD_VALUE = 11;
    private static final int HIGHER_CARD_VALUE = 13;

    private Deck deckOne;
    private Deck deckTwo;
    private MatchState state;
    private CardLayoutNavigator navigator;
    private TestableMatchController controller;
    private SilentMatchView view;
    private Card<Suit> card;
    private PlayerEnum turnPlayer;
    private PlayerEnum otherPlayer;

    @BeforeEach
    void setUp() {
        deckOne = new DeckImpl(new ArrayList<>());
        deckTwo = new DeckImpl(new ArrayList<>());

        card = CardImpl.<Suit>builder()
                .id("1")
                .attributes(Suit.BATONS)
                .value(CARD_VALUE)
                .build();

        final Player playerOne = new PlayerImpl("Player One", MAX_HAND_SIZE, STARTING_HAND_SIZE, deckOne);
        final Player playerTwo = new PlayerImpl("Player Two", MAX_HAND_SIZE, STARTING_HAND_SIZE, deckTwo);

        state = new MatchStateImpl(playerOne, playerTwo, MAX_FIELD_SIZE);
        navigator = new CardLayoutNavigator();

        controller = new TestableMatchController(
                state,
                new StandardMatchLogic(CardAction.NONE, CardAction.NONE, false),
                navigator
        );
        view = controller.getView();

        turnPlayer = controller.getTurnPlayer();
        otherPlayer = turnPlayer == PlayerEnum.PLAYER_ONE ? PlayerEnum.PLAYER_TWO : PlayerEnum.PLAYER_ONE;
    }

    @Test
    void testConstructorNotifiesInitialHiddenHands() {
        assertTrue(view.getHiddenHandUpdates().contains(PlayerEnum.PLAYER_ONE));
        assertTrue(view.getHiddenHandUpdates().contains(PlayerEnum.PLAYER_TWO));
    }

    @Test
    void testShowScreenAddsViewToTheNavigator() {
        controller.showScreen();

        final JComponent root = navigator.getRootView();
        assertEquals(1, root.getComponentCount());
        assertEquals(view, root.getComponent(0));
    }

    @Test
    void testPlayCardOnWrongTurnThrowsIllegalStateException() {
        assertThrows(
                IllegalStateException.class,
                () -> controller.playCard(otherPlayer, card)
        );
    }

    @Test
    void testPlayCardMovesCardFromHandToPlayfield() throws CardCollectionFullException {
        state.getPlayer(turnPlayer).getHand().addCard(card);

        assertEquals(1, state.getPlayer(turnPlayer).getHand().size());
        assertTrue(state.getPlayfield().getCards(turnPlayer).isEmpty());

        controller.playCard(turnPlayer, card);

        assertEquals(1, state.getPlayfield().getCards(turnPlayer).size());
        assertTrue(state.getPlayer(turnPlayer).getHand().isEmpty());
    }

    @Test
    void testDiscardCardMovesCardFromFieldToDiscardPile() throws CardCollectionFullException {
        state.getPlayer(turnPlayer).getHand().addCard(card);
        controller.playCard(turnPlayer, card);

        controller.discardCard(turnPlayer, card);

        assertTrue(state.getPlayfield().getCards(turnPlayer).isEmpty());
        assertFalse(controller.isEmptyDiscardPile(turnPlayer));
    }

    @Test
    void testDrawFromDeckOnEmptyDeckThrowsEmptyCardCollectionException() {
        assertTrue(controller.isEmptyDeck(turnPlayer));

        assertThrows(
                EmptyCardCollectionException.class,
                () -> controller.drawFromDeck(turnPlayer)
        );
    }

    @Test
    void testDrawFromDeckWithFullHandShowsInvalidAction() throws CardCollectionFullException {
        final Deck turnPlayerDeck = turnPlayer == PlayerEnum.PLAYER_ONE ? deckOne : deckTwo;
        turnPlayerDeck.addCard(card);

        final Hand hand = state.getPlayer(turnPlayer).getHand();
        for (int i = 0; i < MAX_HAND_SIZE; i++) {
            hand.addCard(
                    CardImpl.<Suit>builder()
                            .id("test" + i)
                            .attributes(Suit.BATONS)
                            .value(CARD_VALUE)
                            .build()
            );
        }

        controller.drawFromDeck(turnPlayer);

        assertNotNull(view.getLastInvalidActionMessage());
        assertEquals(1, controller.getDeckCount(turnPlayer));
    }

    @Test
    void testEndTurnSwitchesCurrentPlayer() {
        controller.endTurn();

        assertEquals(otherPlayer, controller.getTurnPlayer());
        assertEquals(otherPlayer, view.getLastShownCurrentPlayer());
    }

    @Test
    void testConcedeEndsMatchWithOpponentAsWinner() {
        controller.concede();

        assertTrue(state.isFinished());
        assertEquals(otherPlayer, state.getWinner().get().toPlayerEnum().get());
        assertEquals(otherPlayer, view.getLastMatchEndedWinner().toPlayerEnum().get());
    }

    @Test
    void testCompareCardWithNullCardThrowsNullPointerException() {
        assertThrows(
                NullPointerException.class,
                () -> controller.compareCard(null, card)
        );
    }

    @Test
    void testCompareCardAppliesWinnerAndLoserActions() throws CardCollectionFullException, FieldFullException {
        final TestableMatchController customController = new TestableMatchController(
                state,
                new StandardMatchLogic(CardAction.TO_PILE, CardAction.TO_DECK, false),
                navigator
        );

        final Card<Suit> playerOneCard = CardImpl.<Suit>builder()
                .id("k").attributes(Suit.BATONS).value(HIGHER_CARD_VALUE).build();
        final Card<Suit> playerTwoCard = CardImpl.<Suit>builder()
                .id("j").attributes(Suit.BATONS).value(CARD_VALUE).build();

        state.getPlayer(PlayerEnum.PLAYER_ONE).getHand().addCard(playerOneCard);
        state.getPlayer(PlayerEnum.PLAYER_TWO).getHand().addCard(playerTwoCard);
        state.playCard(playerOneCard, PlayerEnum.PLAYER_ONE);
        state.playCard(playerTwoCard, PlayerEnum.PLAYER_TWO);

        // player one's card is higher: player one wins (TO_PILE), player two loses (TO_DECK)
        customController.compareCard(playerOneCard, playerTwoCard);

        assertTrue(state.getPlayfield().getCards(PlayerEnum.PLAYER_ONE).isEmpty());
        assertFalse(state.isEmptyDiscardPile(PlayerEnum.PLAYER_ONE));
        assertTrue(state.getPlayfield().getCards(PlayerEnum.PLAYER_TWO).isEmpty());
        assertEquals(1, state.getPlayer(PlayerEnum.PLAYER_TWO).getDeckCount());
    }
}
