package it.unibo.cardhub.model.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unibo.cardhub.model.domain.api.Card;
import it.unibo.cardhub.model.domain.attributes.Suit;
import it.unibo.cardhub.model.domain.exceptions.CardCollectionFullException;
import it.unibo.cardhub.model.domain.exceptions.NoSuchCardsException;
import it.unibo.cardhub.model.domain.impl.CardImpl;
import it.unibo.cardhub.model.domain.impl.HandImpl;

public class HandImplTest {

    private HandImpl hand;
    private Card<Suit> card1;
    private Card<Suit> card2;
    private Card<Suit> card3;
    
    @BeforeEach
    void setUp() {
        hand = new HandImpl(new ArrayList<>(), 2);

        card1 = new CardImpl.Builder<Suit>()
                .id("1")
                .attributes(Suit.HEARTS)
                .value(10)
                .image(null)
                .build();
        
        card2 = new CardImpl.Builder<Suit>()
                .id("2")
                .attributes(Suit.SPADES)
                .value(5)
                .image(null)
                .build();

        card3 = new CardImpl.Builder<Suit>()
                .id("3")
                .attributes(Suit.CLUBS)
                .value(1)
                .image(null)
                .build();
    }

    @Test
    void testAddCard() throws CardCollectionFullException {
        hand.addCard(card1);

        assertEquals(1, hand.size());
        assertEquals(card1, hand.getCards().get(0));
    }

    @Test
    void testHandReachesMaximumSize() throws CardCollectionFullException {
        hand.addCard(card1);
        hand.addCard(card2);

        assertEquals(2, hand.size());
    }

    @Test
    void testAddCardWhenHandIsFull() throws CardCollectionFullException {
        hand.addCard(card1);
        hand.addCard(card2);

        assertThrows(
                CardCollectionFullException.class,
                () -> hand.addCard(card3)
        );

        assertEquals(2, hand.size());
    }

    @Test
    void testPlayCard() throws CardCollectionFullException {
        hand.addCard(card1);

        Card<?> playedCard = hand.playCard(card1);

        assertEquals(card1, playedCard);
        assertTrue(hand.isEmpty());
    }

    @Test
    void testPlayCardNotInHand() {
        assertThrows(
                NoSuchCardsException.class,
                () -> hand.playCard(card3)
        );
    }
}
