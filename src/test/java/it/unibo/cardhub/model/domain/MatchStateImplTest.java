package it.unibo.cardhub.model.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unibo.cardhub.model.domain.api.Deck;
import it.unibo.cardhub.model.domain.api.MatchState;
import it.unibo.cardhub.model.domain.api.Player;
import it.unibo.cardhub.model.domain.api.PlayerEnum;
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

    @BeforeEach
    void setUp() {
        final Deck deckOne = new DeckImpl(new ArrayList<>());
        final Deck deckTwo = new DeckImpl(new ArrayList<>());

        playerOne = new PlayerImpl(
            "Player One",
            MAX_HAND_SIZE,
            HAND_SIZE,
            deckOne
        );

        final Player playerTwo = new PlayerImpl(
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
        matchState.endMatch(PlayerEnum.PLAYER_ONE);

        assertTrue(matchState.isFinished());
        assertEquals(
            playerOne,
            matchState.getPlayer(matchState.getWinner().get())
        );
    }

    @Test
    void testMoveCardFromFieldToPile() {
        final Card<Suit> card = new CardImpl.Builder<Suit>()
                .id("1")
                .attributes(Suit.HEARTS)
                .value(10)
                .build();

        matchState.getPlayfield().addCard(PlayerEnum.PLAYER_ONE, card);

        matchState.moveCardFromFieldToPile(
            card,
            PlayerEnum.PLAYER_ONE
        );

        assertTrue(
            matchState.getPlayfield().getCards(PlayerEnum.PLAYER_ONE).isEmpty()
        );

        assertEquals(
            card,
            matchState.getPlayer(PlayerEnum.PLAYER_ONE).getDiscardPile().peekCard().get()
        );
    }

    @Test
    void testMoveCardFromCorrectPlayerWhenCardsAreEqual() {
        // stessa carta nel campo di entrambi

        matchState.moveCardFromFieldToPile(
            card,
            PlayerEnum.PLAYER_TWO
        );

        // Player 1 deve ancora avere la carta
        // Player 2 non deve più averla
        // Player 2 deve averla nella discard pile
    }
}
