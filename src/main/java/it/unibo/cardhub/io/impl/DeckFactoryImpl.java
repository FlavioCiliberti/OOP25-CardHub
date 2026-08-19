package it.unibo.cardhub.io.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

import org.yaml.snakeyaml.Yaml;

import it.unibo.cardhub.io.api.DeckFactory;
import it.unibo.cardhub.model.domain.api.Deck;
import it.unibo.cardhub.model.domain.attributes.DragonBall;
import it.unibo.cardhub.model.domain.attributes.ECard;
import it.unibo.cardhub.model.domain.attributes.Pokemon;
import it.unibo.cardhub.model.domain.attributes.Suit;
import it.unibo.cardhub.model.domain.attributes.YuGiOh;
import it.unibo.cardhub.model.domain.impl.CardImpl;
import it.unibo.cardhub.model.domain.impl.DeckImpl;

/**
 * Implementation of the DeckFactory interface for creating instances of Deck for different card types.
 */
public final class DeckFactoryImpl implements DeckFactory {

    private static final String TYPE_ATTRIBUTE = "type";
    private static final String RARITY_ATTRIBUTE = "rarity";
    private static final String RACE_ATTRIBUTE = "race";
    private static final String ID_FIELD = "id";
    private static final String VALUE_FIELD = "value";
    private static final String DESCRIPTION_FIELD = "description";
    private static final String IMAGE_FIELD = "image";
    private static final String ATTRIBUTES_FIELD = "attributes";
    private static final String CARDS_FIELD = "cards";

    /**
     * Constructs a new DeckFactoryImpl.
     */
    public DeckFactoryImpl() {
        // Default constructor
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Deck createItalianDeck() {
        final Deck deck = new DeckImpl(new ArrayList<>());

        for (final Suit suit : Suit.values()) {
            for (int value = 1; value <= 10; value++) {
                deck.addCard(new CardImpl<>(
                    suit.name() + "_" + value, 
                    suit, 
                    value, 
                    Optional.empty(), 
                    suit.name() + "_" + value + ".png")
                );
            }
        }

        return deck;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Deck createPokemonDeck() {
        return loadDeck(CardType.POKEMON, attributes -> new Pokemon(
            (String) attributes.get(TYPE_ATTRIBUTE),
            (String) attributes.get(RARITY_ATTRIBUTE)
        ));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Deck createDragonBallDeck() {
        return loadDeck(CardType.DRAGONBALL, attributes -> new DragonBall(
            (String) attributes.get(TYPE_ATTRIBUTE),
            (String) attributes.get(RARITY_ATTRIBUTE)
        ));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Deck createYuGiOhDeck() {
        return loadDeck(CardType.YUGIOH, attributes -> new YuGiOh(
            (String) attributes.get(TYPE_ATTRIBUTE),
            (String) attributes.get(RACE_ATTRIBUTE)
        ));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Deck createECardDeck() {
        return loadDeck(CardType.ECARD, attributes -> new ECard(
            (String) attributes.get(TYPE_ATTRIBUTE)
        ));
    }

    private <T> Deck loadDeck(final CardType cardType, final Function<Map<String, Object>, T> attributeFactory) {
        try (var inputStream = getClass().getResourceAsStream(cardType.getResourcePath())) {
            if (inputStream == null) {
                throw new IllegalStateException("Resource not found: " + cardType.getResourcePath());
            }

            final Yaml yaml = new Yaml();

            final Map<String, Object> data = yaml.load(inputStream);

            @SuppressWarnings("unchecked")
            final List<Map<String, Object>> cardsData = (List<Map<String, Object>>) data.get(CARDS_FIELD);

            final List<CardImpl<T>> cards = cardsData.stream().map(cardData -> {
                @SuppressWarnings("unchecked")
                final Map<String, Object> attributes = (Map<String, Object>) cardData.get(ATTRIBUTES_FIELD);
                final T cardAttributes = attributeFactory.apply(attributes);
                return new CardImpl<>(
                    (String) cardData.get(ID_FIELD),
                    cardAttributes,
                    (Integer) cardData.get(VALUE_FIELD),
                    Optional.ofNullable((String) cardData.get(DESCRIPTION_FIELD)),
                    (String) cardData.get(IMAGE_FIELD)
                );
            }).toList();

            return new DeckImpl(new ArrayList<>(cards));

        } catch (final IOException e) {
            throw new IllegalStateException("Failed to load cards from resource: " + cardType.getResourcePath(), e);
        }
    }

    /**
     * Enum representing the different types of cards and their corresponding resource paths.
     */
    private enum CardType {
        POKEMON("/it/unibo/cardhub/model/pokemon.yaml"), 
        DRAGONBALL("/it/unibo/cardhub/model/dragonball.yaml"), 
        YUGIOH("/it/unibo/cardhub/model/yugioh.yaml"),
        ECARD("/it/unibo/cardhub/model/ecard.yaml");

        private final String resourcePath;

        CardType(final String resourcePath) {
            this.resourcePath = resourcePath;
        }

        /**
         * Return the resource path.
         * 
         * @return the resource path
         */
        String getResourcePath() {
            return this.resourcePath;
        }
    }
}
