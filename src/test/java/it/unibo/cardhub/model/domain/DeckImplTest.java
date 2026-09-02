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
import it.unibo.cardhub.model.domain.attributes.Suit;
import it.unibo.cardhub.model.domain.exceptions.EmptyCardCollectionException;
import it.unibo.cardhub.model.domain.impl.CardImpl;
import it.unibo.cardhub.model.domain.impl.DeckImpl;

class DeckImplTest {

    private Deck deck;
    private Card<Suit> card1;
    private Card<Suit> card2;

    @BeforeEach
    void setUp() {
        card1 = new CardImpl.Builder<Suit>()
                .id("1")
                .attributes(Suit.CLUBS)
                .value(10)
                .build();

        card2 = new CardImpl.Builder<Suit>()
                .id("2")
                .attributes(Suit.HEARTS)
                .value(20)
                .build();

        deck = new DeckImpl(
                new ArrayList<>(List.of(card1, card2))
        );
    }

    @Test
    void testDrawCard() throws EmptyCardCollectionException {
        Card<?> drawnCard = deck.drawCard();

        assertEquals(card2, drawnCard);
        assertEquals(1, deck.size());
    }

    @Test
    void testPeekCard() throws EmptyCardCollectionException {
        var peekedCard = deck.peekCard().get();

        assertEquals(card2, peekedCard);
        assertEquals(2, deck.size());
    }

    @Test
    void testDrawCardFromEmptyDeck() throws EmptyCardCollectionException {
        deck.drawCard();
        deck.drawCard();

        assertThrows(
                EmptyCardCollectionException.class,
                () -> deck.drawCard()
        );

        assertTrue(deck.isEmpty());
    }

    @Test
    void testShuffleKeepsAllCards() {
        deck.shuffle();

        assertEquals(2, deck.size());
        assertTrue(deck.getCards().contains(card1));
        assertTrue(deck.getCards().contains(card2));
    }
}
