package it.unibo.cardhub.model.logic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;

import it.unibo.cardhub.model.domain.api.Card;
import it.unibo.cardhub.model.domain.api.MatchState;
import it.unibo.cardhub.model.domain.api.PlayerEnum;
import it.unibo.cardhub.model.domain.exceptions.CardCollectionFullException;
import it.unibo.cardhub.model.domain.impl.CardImpl;
import it.unibo.cardhub.model.domain.impl.DeckImpl;
import it.unibo.cardhub.model.domain.impl.MatchStateBuilderImpl;
import it.unibo.cardhub.model.logic.api.CardAction;
import it.unibo.cardhub.model.logic.api.ComparisonWinner;
import it.unibo.cardhub.model.logic.impl.StandardMatchLogic;

/**
 * Test class for StandardMatchLogic.
 */
class StandardLogicTest {
    private static final String TEST_STRING = "test";
    private static final String ERROR_STRING = "Unexpected Exception";

    @Test
    void testCardsMoveToPile() {
        final Card<?> firstPlayerCard = createTestCard(9);
        final Card<?> secondPlayerCard = createTestCard(3);
        final MatchState state = buildState(firstPlayerCard, secondPlayerCard);
        final StandardMatchLogic logic = new StandardMatchLogic(CardAction.TO_PILE, CardAction.TO_PILE, false);

        state.playCard(firstPlayerCard, PlayerEnum.PLAYER_ONE);
        state.playCard(secondPlayerCard, PlayerEnum.PLAYER_TWO);

        assertEquals(ComparisonWinner.PLAYER_1, logic.compareCard(firstPlayerCard, secondPlayerCard, state));
        assertTrue(state.getPlayfield().getAllCards().isEmpty());
        assertEquals(Optional.of(firstPlayerCard), state.getPlayer(PlayerEnum.PLAYER_ONE).peekDiscardPile());
        assertEquals(Optional.of(secondPlayerCard), state.getPlayer(PlayerEnum.PLAYER_TWO).peekDiscardPile());
    }

    @Test
    void testCardsMoveToDeck() {
        final Card<?> firstPlayerCard = createTestCard(9);
        final Card<?> secondPlayerCard = createTestCard(3);
        final MatchState state = buildState(firstPlayerCard, secondPlayerCard);
        final StandardMatchLogic logic = new StandardMatchLogic(CardAction.TO_DECK, CardAction.TO_DECK, false);

        state.playCard(firstPlayerCard, PlayerEnum.PLAYER_ONE);
        state.playCard(secondPlayerCard, PlayerEnum.PLAYER_TWO);

        assertEquals(ComparisonWinner.PLAYER_1, logic.compareCard(firstPlayerCard, secondPlayerCard, state));
        assertTrue(state.getPlayfield().getAllCards().isEmpty());

        try {
            state.drawCard(PlayerEnum.PLAYER_ONE);
        } catch (final CardCollectionFullException e) {
            fail(ERROR_STRING, e);
        }
        try {
            state.drawCard(PlayerEnum.PLAYER_TWO);
        } catch (final CardCollectionFullException e) {
            fail(ERROR_STRING, e);
        }

        assertEquals(List.of(firstPlayerCard), state.getPlayer(PlayerEnum.PLAYER_ONE).getHand().getCards());
        assertEquals(List.of(secondPlayerCard), state.getPlayer(PlayerEnum.PLAYER_TWO).getHand().getCards());
    }

    @Test 
    void testDifferentCardActions() {
        final Card<?> firstPlayerCard = createTestCard(9);
        final Card<?> secondPlayerCard = createTestCard(3);
        final MatchState state = buildState(firstPlayerCard, secondPlayerCard);
        final StandardMatchLogic logic = new StandardMatchLogic(CardAction.NONE, CardAction.TO_DECK, false);

        state.playCard(firstPlayerCard, PlayerEnum.PLAYER_ONE);
        state.playCard(secondPlayerCard, PlayerEnum.PLAYER_TWO);

        assertEquals(ComparisonWinner.PLAYER_1, logic.compareCard(firstPlayerCard, secondPlayerCard, state));
        assertFalse(state.getPlayfield().getAllCards().isEmpty());

        try {
            state.drawCard(PlayerEnum.PLAYER_TWO);
        } catch (final CardCollectionFullException e) {
            fail(ERROR_STRING, e);
        }

        assertEquals(List.of(secondPlayerCard), state.getPlayer(PlayerEnum.PLAYER_TWO).getHand().getCards());

    }

    @Test
    void testTie() {
        final Card<?> firstPlayerCard = createTestCard(3);
        final Card<?> secondPlayerCard = createTestCard(3);
        final MatchState state = buildState(firstPlayerCard, secondPlayerCard);
        final StandardMatchLogic logic = new StandardMatchLogic(CardAction.TO_DECK, CardAction.TO_PILE, false);

        state.playCard(firstPlayerCard, PlayerEnum.PLAYER_ONE);
        state.playCard(secondPlayerCard, PlayerEnum.PLAYER_TWO);

        assertEquals(ComparisonWinner.TIE, logic.compareCard(firstPlayerCard, secondPlayerCard, state));
        assertTrue(state.getPlayfield().getAllCards().isEmpty());
        assertEquals(Optional.of(firstPlayerCard), state.getPlayer(PlayerEnum.PLAYER_ONE).peekDiscardPile());
        assertEquals(Optional.of(secondPlayerCard), state.getPlayer(PlayerEnum.PLAYER_TWO).peekDiscardPile());
        assertEquals(0, state.getPlayer(PlayerEnum.PLAYER_ONE).getDeckCount());
        assertEquals(0, state.getPlayer(PlayerEnum.PLAYER_TWO).getDeckCount());
    }

    @Test 
    void testShouldAutoDraw() {
        final StandardMatchLogic logic = new StandardMatchLogic(CardAction.NONE, CardAction.NONE, true);

        assertFalse(logic.shouldAutoDraw());
        logic.changeTurn();
        assertTrue(logic.shouldAutoDraw());
    }

    private static Card<?> createTestCard(final int value) {
        return new CardImpl<>(TEST_STRING, Optional.of(TEST_STRING), TEST_STRING, value, Optional.of("desc"), "Exodia.png");
    }

    private static MatchState buildState(final Card<?> firstPlayerCard, final Card<?> secondPlayerCard) {
        return new MatchStateBuilderImpl("Player1", 
            "Player2",
            new DeckImpl(List.of(firstPlayerCard)),
            new DeckImpl(List.of(secondPlayerCard))).startingHandSize(1).build();
    }
}

