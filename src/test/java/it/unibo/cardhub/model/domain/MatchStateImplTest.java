package it.unibo.cardhub.model.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unibo.cardhub.model.domain.api.Card;
import it.unibo.cardhub.model.domain.api.Deck;
import it.unibo.cardhub.model.domain.api.MatchState;
import it.unibo.cardhub.model.domain.api.Player;
import it.unibo.cardhub.model.domain.api.PlayerEnum;
import it.unibo.cardhub.model.domain.attributes.Suit;
import it.unibo.cardhub.model.domain.exceptions.CardCollectionFullException;
import it.unibo.cardhub.model.domain.exceptions.FieldFullException;
import it.unibo.cardhub.model.domain.impl.CardImpl;
import it.unibo.cardhub.model.domain.impl.DeckImpl;
import it.unibo.cardhub.model.domain.impl.MatchStateImpl;
import it.unibo.cardhub.model.domain.impl.PlayerImpl;

/**
 * Test class for the {@link MatchStateImpl} class.
 */
final class MatchStateImplTest {

    private static final int HAND_SIZE = 5;
    private static final int MAX_HAND_SIZE = 7;
    private static final int MAX_FIELD_SIZE = 3;

    private MatchState matchState;
    private Player playerOne;
    private Player playerTwo;
    private Card<Suit> card;

    @BeforeEach
    void setUp() {
        final Deck deckOne = new DeckImpl(new ArrayList<>());
        final Deck deckTwo = new DeckImpl(new ArrayList<>());

        card = CardImpl.<Suit>builder()
                .id("1")
                .attributes(Suit.BATONS)
                .value(10)
                .build();

        playerOne = new PlayerImpl(
            "Player One",
            MAX_HAND_SIZE,
            HAND_SIZE,
            deckOne
        );

        playerTwo = new PlayerImpl(
            "Player Two",
            MAX_HAND_SIZE,
            HAND_SIZE,
            deckTwo
        );

        matchState = new MatchStateImpl(
            playerOne,
            playerTwo,
            MAX_FIELD_SIZE
        );
    }

    @Test
    void testMatchStartsNotFinished() {
        assertFalse(matchState.isFinished());
        assertTrue(matchState.getWinner().isEmpty());
    }

    @Test
    void testEndMatchSetsWinner() {
        matchState.endMatch(PlayerEnum.PLAYER_ONE.toComparisonWinner());

        assertTrue(matchState.isFinished());
        assertEquals(
            playerOne,
            matchState.getPlayer(matchState.getWinner().get().toPlayerEnum().get())
        );
    }

    @Test
    void testMoveCardFromFieldToPile() throws FieldFullException, CardCollectionFullException {
        playerOne.getHand().addCard(card);

        matchState.playCard(card, PlayerEnum.PLAYER_ONE);

        assertFalse(
            matchState.getPlayfield().getCards(PlayerEnum.PLAYER_ONE).isEmpty()
        );

        assertTrue(
            matchState.isEmptyDiscardPile(PlayerEnum.PLAYER_ONE)
        );

        matchState.moveCardFromFieldToPile(
            card,
            PlayerEnum.PLAYER_ONE
        );

        assertTrue(
            matchState.getPlayfield().getCards(PlayerEnum.PLAYER_ONE).isEmpty()
        );

        assertFalse(
            matchState.isEmptyDiscardPile(PlayerEnum.PLAYER_ONE)
        );
    }

    @Test
    void testMoveCardFromCorrectPlayerWhenCardsAreEqual() throws FieldFullException, CardCollectionFullException {
        playerOne.getHand().addCard(card);
        playerTwo.getHand().addCard(card);

        matchState.playCard(card, PlayerEnum.PLAYER_ONE);
        matchState.playCard(card, PlayerEnum.PLAYER_TWO);

        matchState.moveCardFromFieldToPile(
            card,
            PlayerEnum.PLAYER_TWO
        );

        assertTrue(
            matchState.getPlayfield().getCards(PlayerEnum.PLAYER_TWO).isEmpty()
        );

        assertFalse(
            matchState.getPlayfield().getCards(PlayerEnum.PLAYER_ONE).isEmpty()
        );

        assertFalse(
            matchState.isEmptyDiscardPile(PlayerEnum.PLAYER_TWO)
        );
    }
}
