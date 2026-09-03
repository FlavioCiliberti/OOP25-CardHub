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

class MatchStateImplTest {

    private MatchState matchState;
    private Player playerOne;
    private Player playerTwo;

    @BeforeEach
    void setUp() {
        final Deck deckOne = new DeckImpl(new ArrayList<>());
        final Deck deckTwo = new DeckImpl(new ArrayList<>());

        playerOne = new PlayerImpl(
            "Player One",
            5,
            1,
            deckOne
        );

        playerTwo = new PlayerImpl(
            "Player Two",
            5,
            1,
            deckTwo
        );

        matchState = new MatchStateImpl(
            playerOne,
            playerTwo,
            3
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
}
