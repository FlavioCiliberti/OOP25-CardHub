package it.unibo.cardhub.model.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unibo.cardhub.io.api.DeckFactory;
import it.unibo.cardhub.io.impl.DeckFactoryImpl;
import it.unibo.cardhub.model.domain.api.Deck;
import it.unibo.cardhub.model.domain.api.MatchState;
import it.unibo.cardhub.model.domain.api.PlayerEnum;
import it.unibo.cardhub.model.domain.impl.MatchStateBuilderImpl;

/**
 * Test class for {@link MatchStateBuilderImpl} class.
 */
final class MatchStateBuilderTest {
    private static final int DEFAULT_MAX_HAND_SIZE = 5;
    private static final int DEFAULT_STARTING_HAND_SIZE = 5;
    private static final int DEFAULT_PLAYFIELD_SIZE = 3;
    private static final int TEST_MAX_HAND_SIZE = 7;
    private static final int TEST_STARTING_HAND_SIZE = 3;
    private static final int TEST_PLAYFIELD_SIZE = 2;
    private static final String TEST_PLAYER_ONE_NAME = "test1";
    private static final String TEST_PLAYER_TWO_NAME = "test2";

    private Deck deck1;
    private Deck deck2;

    @BeforeEach
    void createDecks() {
        final DeckFactory factory = new DeckFactoryImpl();

        deck1 = factory.createDragonBallDeck();
        deck2 = factory.createDragonBallDeck();
    }

    @Test
    void testBuildUsesDefaultConfiguration() {
        final MatchState state = new MatchStateBuilderImpl(TEST_PLAYER_ONE_NAME, TEST_PLAYER_TWO_NAME, 
                                                            deck1, deck2).build();

        assertEquals("test1", state.getPlayer(PlayerEnum.PLAYER_ONE).getName());
        assertEquals("test2", state.getPlayer(PlayerEnum.PLAYER_TWO).getName());
        assertEquals(DEFAULT_MAX_HAND_SIZE, state.getPlayer(PlayerEnum.PLAYER_ONE).getHand().getMaxSize());
        assertEquals(DEFAULT_STARTING_HAND_SIZE, state.getPlayer(PlayerEnum.PLAYER_ONE).getHand().getCards().size());
        assertEquals(DEFAULT_PLAYFIELD_SIZE, state.getPlayFieldSize());
    }

    @Test
    void testBuildWithCustomValues() {
        final MatchState state = new MatchStateBuilderImpl(TEST_PLAYER_ONE_NAME, TEST_PLAYER_TWO_NAME, 
                                                            deck1, deck2).maxHandSize(TEST_MAX_HAND_SIZE)
                                                            .startingHandSize(TEST_STARTING_HAND_SIZE)
                                                            .playfieldSize(TEST_PLAYFIELD_SIZE).build();

        assertEquals(TEST_MAX_HAND_SIZE, state.getPlayer(PlayerEnum.PLAYER_ONE).getHand().getMaxSize());
        assertEquals(TEST_STARTING_HAND_SIZE, state.getPlayer(PlayerEnum.PLAYER_ONE).getHand().getCards().size());
        assertEquals(TEST_PLAYFIELD_SIZE, state.getPlayFieldSize());
    }

    @Test
    void rejectNullPlayerName() {
        assertThrows(NullPointerException.class, () -> 
                    new MatchStateBuilderImpl(null, TEST_PLAYER_TWO_NAME, deck1, deck2));
    }
}
