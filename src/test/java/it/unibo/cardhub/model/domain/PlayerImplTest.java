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
import it.unibo.cardhub.model.domain.exceptions.NoSuchCardsException;
import it.unibo.cardhub.model.domain.impl.CardImpl;
import it.unibo.cardhub.model.domain.impl.DeckImpl;
import it.unibo.cardhub.model.domain.impl.PlayerImpl;

class PlayerImplTest {

    private Player player;
    private Deck deck;
    private Card<Suit> card1;
    private Card<Suit> card2;

    @BeforeEach
    void setUp() throws CardCollectionFullException {
        card1 = new CardImpl.Builder<Suit>()
                .id("1")
                .attributes(Suit.HEARTS)
                .value(10)
                .build();

        card2 = new CardImpl.Builder<Suit>()
                .id("")
                .attributes(Suit.SPADES)
                .value(20)
                .build();

        deck = new DeckImpl(new ArrayList<>());
        deck.addCard(card1);
        deck.addCard(card2);

        player = new PlayerImpl("John", 7, 7, deck);
    }

    @Test
    void testDrawCard() throws Exception {
        player.drawCard();

        assertEquals(1, player.getHand().size());
        assertEquals(1, player.getDeckCount());
        assertEquals(card2, player.getHand().getCards().get(0));
    }

    @Test
    void testPlayCard() throws Exception {
        player.drawCard();

        Card<?> playedCard = player.playCard(card2);

        assertEquals(card2, playedCard);
        assertEquals(0, player.getHand().size());
    }

    @Test
    void testPutInPile() throws Exception {
        player.drawCard();

        player.putInPile(card2);

        assertEquals(card2, player.peekDiscardPile().get());
    }

    @Test
    void testDrawCardWithEmptyDeck() {
        DeckImpl emptyDeck = new DeckImpl(new ArrayList<>());
        PlayerImpl emptyDeckPlayer = new PlayerImpl("John", 7, 7, emptyDeck);

        assertThrows(
            EmptyCardCollectionException.class,
            emptyDeckPlayer::drawCard
        );
    }
}
