package it.unibo.cardhub.view.util.impl;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

import javax.swing.ImageIcon;

import it.unibo.cardhub.model.domain.api.Card;
import it.unibo.cardhub.view.util.api.CardImageResolver;

/**
 * Proxy that adds caching on top of another {@link CardImageResolver}, so
 * that each distinct image is loaded and scaled only once.
 */
public final class CachingCardImageResolver implements CardImageResolver {
    private static final String BACK_CACHE_KEY = "back-of-card";

    private final CardImageResolver resolver;
    private final Map<String, ImageIcon> cache = new ConcurrentHashMap<>();

    /**
     * Creates a caching proxy around the given resolver.
     *
     * @param resolver the resolver that actually performs the loading
     */
    public CachingCardImageResolver(final CardImageResolver resolver) {
        this.resolver = Objects.requireNonNull(resolver, "resolver must not be null");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ImageIcon resolve(final Card<?> card) {
        Objects.requireNonNull(card, "card must not be null");
        return cache.computeIfAbsent(card.image(), key -> resolver.resolve(card));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ImageIcon resolveBack() {
        return cache.computeIfAbsent(BACK_CACHE_KEY, key -> resolver.resolveBack());
    }

    @Override
    public int getCardWidth() {
        return resolver.getCardWidth();
    }

    @Override
    public int getCardHeight() {
        return resolver.getCardHeight();
    }

}
