package it.unibo.cardhub.model.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unibo.cardhub.model.domain.api.Card;
import it.unibo.cardhub.model.domain.api.Deck;
import it.unibo.cardhub.model.domain.api.Player;
import it.unibo.cardhub.model.domain.attributes.Suit;
import it.unibo.cardhub.model.domain.exceptions.CardCollectionFullException;
import it.unibo.cardhub.model.domain.exceptions.EmptyCardCollectionException;
import it.unibo.cardhub.model.domain.impl.CardImpl;
import it.unibo.cardhub.model.domain.impl.DeckImpl;
import it.unibo.cardhub.model.domain.impl.PlayerImpl;

/**
 * Test class for the {@link PlayerImpl} class.
 */
final class PlayerImplTest {

    private static final int CARD_VALUE = 10;
    private static final int MAX_HAND_SIZE = 7;
    private static final int STARTING_HAND_SIZE = 0;

    private Player player;
    private Card<Suit> card2;

    @BeforeEach
    void setUp() {
        final Card<Suit> card1 = new CardImpl.Builder<Suit>()
                .id("1")
                .attributes(Suit.HEARTS)
                .value(CARD_VALUE)
                .build();

        card2 = new CardImpl.Builder<Suit>()
                .id("2")
                .attributes(Suit.SPADES)
                .value(CARD_VALUE)
                .build();

        final Deck deck = new DeckImpl(new ArrayList<>());
        deck.addCard(card1);
        deck.addCard(card2);

        player = new PlayerImpl("John", STARTING_HAND_SIZE, MAX_HAND_SIZE, deck);
    }

    @Test
    void testDrawCard() {
        player.drawCard();

        assertEquals(1, player.getHand().size());
        assertEquals(1, player.getDeckCount());
        assertEquals(card2, player.getHand().getCards().get(0));
    }

    @Test
    void testPlayCard() {
        player.drawCard();

        final Card<?> playedCard = player.playCard(card2);

        assertEquals(card2, playedCard);
        assertEquals(0, player.getHand().size());
    }

    @Test
    void testPutInPile() {
        player.drawCard();

        player.putInPile(card2);

        assertEquals(card2, player.peekDiscardPile().get());
    }

    @Test
    void testDrawCardWithEmptyDeck() {
        final DeckImpl emptyDeck = new DeckImpl(new ArrayList<>());
        final PlayerImpl emptyDeckPlayer = new PlayerImpl("John", STARTING_HAND_SIZE, MAX_HAND_SIZE, emptyDeck);

        assertThrows(
            EmptyCardCollectionException.class,
            emptyDeckPlayer::drawCard
        );
    }

    @Test
    void testDrawCardWithFullHand() {
        // riempi il mazzo
        assertThrows(
            CardCollectionFullException.class,
            () -> player.drawCard()
        );

        assertEquals(1, player.getDeckCount());
        assertEquals(MAX_HAND_SIZE, player.getHand().size());
    }
}
