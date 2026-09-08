package it.unibo.cardhub.io.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

import org.yaml.snakeyaml.Yaml;

import it.unibo.cardhub.io.api.DeckFactory;
import it.unibo.cardhub.model.domain.api.Card;
import it.unibo.cardhub.model.domain.api.Deck;
import it.unibo.cardhub.model.domain.api.DeckEnum;
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
    private static final String NAME_FIELD = "name";
    private static final String VALUE_FIELD = "value";
    private static final String DESCRIPTION_FIELD = "description";
    private static final String IMAGE_FIELD = "image";
    private static final String ATTRIBUTES_FIELD = "attributes";
    private static final String CARDS_FIELD = "cards";

    private static final String ECARD_RESOURCE_PATH = "/it/unibo/cardhub/model/ecard.yaml";

    private static final Map<DeckEnum, String> DECK_RESOURCE_PATHS = Map.of(
        DeckEnum.POKEMON, "/it/unibo/cardhub/model/pokemon.yaml",
        DeckEnum.DRAGONBALL, "/it/unibo/cardhub/model/dragonball.yaml",
        DeckEnum.YUGIOH, "/it/unibo/cardhub/model/yugioh.yaml"
    );

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
                deck.addCard(
                    CardImpl.<Suit>builder()
                        .id(suit.name() + "_" + value)
                        .attributes(suit)
                        .value(value)
                        .image(suit.name() + "_" + value + ".png")
                        .build()
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
        return loadDeck(DECK_RESOURCE_PATHS.get(DeckEnum.POKEMON), attributes -> new Pokemon(
            (String) attributes.get(TYPE_ATTRIBUTE),
            (String) attributes.get(RARITY_ATTRIBUTE)
        ));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Deck createDragonBallDeck() {
        return loadDeck(DECK_RESOURCE_PATHS.get(DeckEnum.DRAGONBALL), attributes -> new DragonBall(
            (String) attributes.get(TYPE_ATTRIBUTE),
            (String) attributes.get(RARITY_ATTRIBUTE)
        ));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Deck createYuGiOhDeck() {
        return loadDeck(DECK_RESOURCE_PATHS.get(DeckEnum.YUGIOH), attributes -> new YuGiOh(
            (String) attributes.get(TYPE_ATTRIBUTE),
            (String) attributes.get(RACE_ATTRIBUTE)
        ));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Deck createECardDeck() {
        return loadDeck(ECARD_RESOURCE_PATH, attributes -> new ECard(
            (String) attributes.get(TYPE_ATTRIBUTE)
        ));
    }

    private <T> Deck loadDeck(final String resourcePath, final Function<Map<String, Object>, T> attributeFactory) {
        try (var inputStream = getClass().getResourceAsStream(resourcePath)) {
            if (inputStream == null) {
                throw new IllegalStateException(
                    "Resource not found: " + resourcePath
                );
            }

            final Yaml yaml = new Yaml();
            final Map<String, Object> data = yaml.load(inputStream);

            @SuppressWarnings("unchecked")
            final List<Map<String, Object>> cardsData = (List<Map<String, Object>>) data.get(CARDS_FIELD);

            final List<Card<T>> cards = cardsData.stream()
                .map(cardData -> {
                    @SuppressWarnings("unchecked")
                    final Map<String, Object> attributes = (Map<String, Object>) cardData.get(ATTRIBUTES_FIELD);
                    final T cardAttributes = attributeFactory.apply(attributes);

                    final Card<T> card = CardImpl.<T>builder()
                        .id((String) cardData.get(ID_FIELD))
                        .name(Optional.ofNullable((String) cardData.get(NAME_FIELD)))
                        .attributes(cardAttributes)
                        .value((Integer) cardData.get(VALUE_FIELD))
                        .desc(Optional.ofNullable((String) cardData.get(DESCRIPTION_FIELD)))
                        .image((String) cardData.get(IMAGE_FIELD))
                        .build();

                    return card;
                })
                .toList();

            return new DeckImpl(new ArrayList<>(cards));

        } catch (final IOException e) {
            throw new IllegalStateException(
                "Failed to load cards from resource: " + resourcePath,
                e
            );
        }
    }
}
