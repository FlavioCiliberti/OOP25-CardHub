package it.unibo.cardhub.model.logic;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unibo.cardhub.model.domain.api.Card;
import it.unibo.cardhub.model.domain.api.MatchState;
import it.unibo.cardhub.model.domain.api.PlayerEnum;
import it.unibo.cardhub.model.domain.impl.CardImpl;
import it.unibo.cardhub.model.domain.impl.DeckImpl;
import it.unibo.cardhub.model.domain.impl.MatchStateBuilderImpl;
import it.unibo.cardhub.model.logic.api.ComparisonWinner;
import it.unibo.cardhub.model.logic.impl.ECardLogic;

/**
 * Test class for ECardLogic.
 */
class ECardLogicTest {
    private static final String TEST_STRING = "test";

    private ECardLogic logic;

    @BeforeEach 
    void createLogic() {
        logic = new ECardLogic();
    }

    @Test
    void testInitialPointsAndWinner() {
        assertEquals(0, logic.getPoints(PlayerEnum.PLAYER_ONE));
        assertEquals(0, logic.getPoints(PlayerEnum.PLAYER_TWO));
        assertEquals(ComparisonWinner.TIE, logic.getWinningPlayer());
    }

    @Test
    void testCitizenBeatsSlave() {
        final Card<?> firstPlayerCard = createTestCard(0);
        final Card<?> secondPlayerCard = createTestCard(1);
        final MatchState state = buildState(firstPlayerCard, secondPlayerCard);

        playCards(state, firstPlayerCard, secondPlayerCard);

        assertEquals(0, logic.getPoints(PlayerEnum.PLAYER_ONE));
        assertEquals(1, logic.getPoints(PlayerEnum.PLAYER_TWO));
        assertEquals(ComparisonWinner.PLAYER_2, logic.getWinningPlayer());
    }

    @Test
    void testSlaveBeatsEmperor() {
        final Card<?> firstPlayerCard = createTestCard(0);
        final Card<?> secondPlayerCard = createTestCard(2);
        final MatchState state = buildState(firstPlayerCard, secondPlayerCard);

        playCards(state, firstPlayerCard, secondPlayerCard);

        assertEquals(3, logic.getPoints(PlayerEnum.PLAYER_ONE));
        assertEquals(0, logic.getPoints(PlayerEnum.PLAYER_TWO));
        assertEquals(ComparisonWinner.PLAYER_1, logic.getWinningPlayer());
    }

    @Test
    void testTie() {
        final Card<?> firstPlayerCard = createTestCard(0);
        final Card<?> secondPlayerCard = createTestCard(0);
        final MatchState state = buildState(firstPlayerCard, secondPlayerCard);

        playCards(state, firstPlayerCard, secondPlayerCard);

        assertEquals(0, logic.getPoints(PlayerEnum.PLAYER_ONE));
        assertEquals(0, logic.getPoints(PlayerEnum.PLAYER_TWO));
        assertEquals(ComparisonWinner.TIE, logic.getWinningPlayer());
    }

    private void playCards(final MatchState state, final Card<?> firstPlayerCard, final Card<?> secondPlayerCard) {
        state.playCard(firstPlayerCard, PlayerEnum.PLAYER_ONE);
        state.playCard(secondPlayerCard, PlayerEnum.PLAYER_TWO);
        logic.compareCard(firstPlayerCard, secondPlayerCard, state);
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
