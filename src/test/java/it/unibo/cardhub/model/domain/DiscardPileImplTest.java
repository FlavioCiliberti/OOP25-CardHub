package it.unibo.cardhub.model.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unibo.cardhub.model.domain.api.Card;
import it.unibo.cardhub.model.domain.api.Deck;
import it.unibo.cardhub.model.domain.api.DiscardPile;
import it.unibo.cardhub.model.domain.attributes.Suit;
import it.unibo.cardhub.model.domain.exceptions.NoSuchCardsException;
import it.unibo.cardhub.model.domain.impl.CardImpl;
import it.unibo.cardhub.model.domain.impl.DeckImpl;
import it.unibo.cardhub.model.domain.impl.DiscardPileImpl;

/**
 * Test class for the {@link DiscardPileImpl} class.
 */
final class DiscardPileImplTest {

    private static final int CARD_VALUE = 10;
    private static final int DISCARD_PILE_SIZE = 2;

    private DiscardPile discardPile;
    private Deck deck;
    private Card<Suit> card1;
    private Card<Suit> card2;

    @BeforeEach
    void setUp() {
        card1 = CardImpl.<Suit>builder()
                .id("1")
                .attributes(Suit.BATONS)
                .value(CARD_VALUE)
                .build();

        card2 = CardImpl.<Suit>builder()
                .id("2")
                .attributes(Suit.BATONS)
                .value(CARD_VALUE)
                .build();

        discardPile = new DiscardPileImpl(
                new ArrayList<>(List.of(card1, card2))
        );

        deck = new DeckImpl(new ArrayList<>());
    }

    @Test
    void testTakeCard() {
        final Card<?> takenCard = discardPile.takeCard(card2);

        assertEquals(card2, takenCard);
        assertEquals(1, discardPile.size());
    }

    @Test
    void testPeekCard() {
        final Card<?> peekedCard = discardPile.peekCard().get();

        assertEquals(card2, peekedCard);
        assertEquals(DISCARD_PILE_SIZE, discardPile.size());
    }

    @Test
    void testTakeCardFromEmptyDiscardPile() {
        discardPile.takeCard(card1);
        discardPile.takeCard(card2);

        assertThrows(
                NoSuchCardsException.class,
                () -> discardPile.takeCard(card1)
        );

        assertTrue(discardPile.isEmpty());
    }

    @Test
    void testReshuffleIntoDeck() {
        discardPile.reshuffleIntoDeck(deck);

        assertTrue(discardPile.isEmpty());
        assertEquals(DISCARD_PILE_SIZE, deck.size());

        assertTrue(deck.getCards().contains(card1));
        assertTrue(deck.getCards().contains(card2));
    }
}
