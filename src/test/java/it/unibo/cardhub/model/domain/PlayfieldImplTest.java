package it.unibo.cardhub.model.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unibo.cardhub.model.domain.api.Card;
import it.unibo.cardhub.model.domain.api.PlayerEnum;
import it.unibo.cardhub.model.domain.api.Playfield;
import it.unibo.cardhub.model.domain.attributes.Suit;
import it.unibo.cardhub.model.domain.exceptions.NoSuchCardsException;
import it.unibo.cardhub.model.domain.impl.CardImpl;
import it.unibo.cardhub.model.domain.impl.PlayfieldImpl;

/**
 * Test class for the {@link PlayfieldImpl} class.
 */
final class PlayfieldImplTest {

    private static final int CARD_VALUE = 10;
    private static final int MAX_FIELD_SIZE = 7;

    private Playfield playfield;
    private Card<Suit> card;

    @BeforeEach
    void setUp() {
        playfield = new PlayfieldImpl(MAX_FIELD_SIZE);

        card = new CardImpl.Builder<Suit>()
                .id("1")
                .attributes(Suit.HEARTS)
                .value(CARD_VALUE)
                .build();
    }

    @Test
    void testRemoveCardFromCorrectPlayer() {
        playfield.addCard(PlayerEnum.PLAYER_ONE, card);
        playfield.addCard(PlayerEnum.PLAYER_TWO, card);

        playfield.removeCard(PlayerEnum.PLAYER_TWO, card);

        assertEquals(
            1,
            playfield.getCards(PlayerEnum.PLAYER_ONE).size()
        );

        assertTrue(
            playfield.getCards(PlayerEnum.PLAYER_TWO).isEmpty()
        );

        assertEquals(
            card,
            playfield.getCards(PlayerEnum.PLAYER_ONE).get(0)
        );
    }

    @Test
    void testAddAndGetCards() {
        playfield.addCard(PlayerEnum.PLAYER_ONE, card);

        assertEquals(
            1,
            playfield.getCards(PlayerEnum.PLAYER_ONE).size()
        );

        assertEquals(
            card,
            playfield.getCards(PlayerEnum.PLAYER_ONE).get(0)
        );
    }

    @Test
    void testRemoveCardNotPresent() {
        assertThrows(
            NoSuchCardsException.class,
            () -> playfield.removeCard(PlayerEnum.PLAYER_ONE, card)
        );
    }

    @Test
    void testAddCardWhenFieldIsFull() {
        for (int i = 0; i < MAX_FIELD_SIZE; i++) {
            final Card<Suit> newCard = new CardImpl.Builder<Suit>()
                    .id(String.valueOf(i))
                    .attributes(Suit.HEARTS)
                    .value(CARD_VALUE)
                    .build();
            playfield.addCard(PlayerEnum.PLAYER_ONE, newCard);  
        }
    }
}
