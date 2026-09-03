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

class PlayfieldImplTest {

    private Playfield playfield;
    private Card<Suit> card;

    @BeforeEach
    void setUp() {
        playfield = new PlayfieldImpl(7);

        card = new CardImpl.Builder<Suit>()
                .id("1")
                .attributes(Suit.HEARTS)
                .value(10)
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
}
