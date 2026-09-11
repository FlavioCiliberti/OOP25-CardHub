package it.unibo.cardhub.model.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unibo.cardhub.model.domain.api.Card;
import it.unibo.cardhub.model.domain.api.Hand;
import it.unibo.cardhub.model.domain.attributes.Suit;
import it.unibo.cardhub.model.domain.exceptions.CardCollectionFullException;
import it.unibo.cardhub.model.domain.exceptions.NoSuchCardsException;
import it.unibo.cardhub.model.domain.impl.CardImpl;
import it.unibo.cardhub.model.domain.impl.HandImpl;

/**
 * Test class for the {@link HandImpl} class.
 */
final class HandImplTest {

    private static final int CARD_VALUE = 10;
    private static final int HAND_SIZE = 2;

    private Hand hand;
    private Card<Suit> card1;
    private Card<Suit> card2;
    private Card<Suit> card3;

    @BeforeEach
    void setUp() {
        hand = new HandImpl(new ArrayList<>(), HAND_SIZE);

        card1 = CardImpl.<Suit>builder()
                .id("1")
                .attributes(Suit.COINS)
                .value(CARD_VALUE)
                .build();

        card2 = CardImpl.<Suit>builder()
                .id("2")
                .attributes(Suit.CUPS)
                .value(CARD_VALUE)
                .build();

        card3 = CardImpl.<Suit>builder()
                .id("3")
                .attributes(Suit.SWORDS)
                .value(CARD_VALUE)
                .build();
    }

    @Test
    void testAddCard() throws CardCollectionFullException {
        hand.addCard(card1);

        assertEquals(1, hand.size());
        assertEquals(card1, hand.getCards().get(0));
    }

    @Test
    void testAddCardWhenHandIsFull() throws CardCollectionFullException {
        hand.addCard(card1);
        hand.addCard(card2);

        assertThrows(
                CardCollectionFullException.class,
                () -> hand.addCard(card3)
        );

        assertEquals(HAND_SIZE, hand.size());
    }

    @Test
    void testPlayCard() throws CardCollectionFullException {
        hand.addCard(card1);

        final Card<?> playedCard = hand.playCard(card1);

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
