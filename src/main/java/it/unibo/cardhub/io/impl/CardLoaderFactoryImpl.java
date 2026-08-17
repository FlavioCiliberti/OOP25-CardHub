package it.unibo.cardhub.io.impl;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.yaml.snakeyaml.Yaml;

import it.unibo.cardhub.io.api.CardCreator;
import it.unibo.cardhub.io.api.CardLoader;
import it.unibo.cardhub.io.api.CardLoaderFactory;
import it.unibo.cardhub.io.api.CardType;

/**
 * Implementation of the CardLoaderFactory interface for creating instances of CardLoader for different card types.
 * 
 * @param <T> the type of the card content
 */
public final class CardLoaderFactoryImpl<T> implements CardLoaderFactory<T> {

    /**
     * Constructs a new CardLoaderFactoryImpl.
     */
    public CardLoaderFactoryImpl() {
        // Default constructor
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CardLoader<T> createLoader(final CardType cardType, final CardCreator<T> cardCreator) {
        return () -> {
            final var resource = getClass().getResourceAsStream(cardType.getResourcePath());

            if (resource == null) {
                throw new IllegalStateException("Resource not found: " + cardType.getResourcePath());
            }

            try (var inputStream = resource) {

                final Yaml yaml = new Yaml();

                final Map<String, Object> data = yaml.load(inputStream);

                @SuppressWarnings("unchecked")
                final List<Map<String, Object>> cardsData = (List<Map<String, Object>>) data.get("cards");

                return cardsData.stream().map(cardCreator::createCard).toList();

            } catch (final IOException e) {
                throw new IllegalStateException("Failed to load cards from resource: " + cardType.getResourcePath(), e);
            }
        };
    }
}
