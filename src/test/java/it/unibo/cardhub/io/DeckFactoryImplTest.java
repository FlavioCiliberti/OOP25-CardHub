package it.unibo.cardhub.io;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unibo.cardhub.io.api.DeckFactory;
import it.unibo.cardhub.io.impl.DeckFactoryImpl;
import it.unibo.cardhub.model.domain.api.Deck;
import it.unibo.cardhub.model.domain.attributes.DragonBall;
import it.unibo.cardhub.model.domain.attributes.ECard;
import it.unibo.cardhub.model.domain.attributes.Pokemon;
import it.unibo.cardhub.model.domain.attributes.Suit;
import it.unibo.cardhub.model.domain.attributes.YuGiOh;

class DeckFactoryImplTest {

    private DeckFactory deckFactory;

    @BeforeEach
    void setUp() {
        deckFactory = new DeckFactoryImpl();
    }

    @Test
    void testCreateItalianDeck() {
        final Deck deck = deckFactory.createItalianDeck();

        assertNotNull(deck);
        assertEquals(40, deck.size());
        assertFalse(deck.isEmpty());

        assertTrue(deck.getCards().stream().allMatch(card -> card.attributes() instanceof Suit));
    }

    @Test
    void testItalianDeckHasUniqueIds() {
        final Deck deck = deckFactory.createItalianDeck();

        final Set<String> ids = new HashSet<>();

        deck.getCards().forEach(card -> ids.add(card.id()));

        assertEquals(deck.size(), ids.size());
    }

    @Test
    void testCreatePokemonDeck() {
        final Deck deck = deckFactory.createPokemonDeck();

        assertNotNull(deck);
        assertEquals(20, deck.size());
        assertFalse(deck.isEmpty());

        assertTrue(deck.getCards().stream().allMatch(card -> card.attributes() instanceof Pokemon));
    }

    @Test
    void testCreateDragonBallDeck() {
        final Deck deck = deckFactory.createDragonBallDeck();

        assertNotNull(deck);
        assertEquals(20, deck.size());
        assertFalse(deck.isEmpty());

        assertTrue(deck.getCards().stream().allMatch(card -> card.attributes() instanceof DragonBall));
    }

    @Test
    void testCreateYuGiOhDeck() {
        final Deck deck = deckFactory.createYuGiOhDeck();

        assertNotNull(deck);
        assertEquals(20, deck.size());
        assertFalse(deck.isEmpty());

        assertTrue(deck.getCards().stream().allMatch(card -> card.attributes() instanceof YuGiOh));
    }

    @Test
    void testCreateECardDeck() {
        final Deck deck = deckFactory.createECardDeck();

        assertNotNull(deck);
        assertEquals(7, deck.size());
        assertFalse(deck.isEmpty());

        assertTrue(deck.getCards().stream().allMatch(card -> card.attributes() instanceof ECard));
    }
}
