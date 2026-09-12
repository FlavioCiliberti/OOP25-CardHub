package it.unibo.cardhub.view.util;

import javax.swing.ImageIcon;

import it.unibo.cardhub.model.domain.api.Card;
import it.unibo.cardhub.view.util.api.CardImageResolver;
import it.unibo.cardhub.view.util.impl.CachingCardImageResolver;
import it.unibo.cardhub.view.util.impl.SimpleCardImageResolver;

/**
 * A utility class for resolving images associated with cards.
 */
public final class ImageResolver {
    private static final CardImageResolver RESOLVER = new CachingCardImageResolver(
                                                        new SimpleCardImageResolver());

    public static final int CARD_WIDTH = RESOLVER.getCardWidth();
    public static final int CARD_HEIGHT = RESOLVER.getCardHeight();

    private ImageResolver() {
        // Private constructor to prevent instantiation
    }

    /**
     * Resolves the image associated with the given card.
     *
     * @param card the card for which to resolve the image
     * @return an ImageIcon representing the card's image
     * @throws NullPointerException if the image resource is not found
     */
    public static ImageIcon resolve(final Card<?> card) {
        return RESOLVER.resolve(card);
    }

    /**
     * Resolves the image associated with every back of a card.
     *
     * @return an ImageIcon representing the image of the back of the cards
     * @throws NullPointerException if the image resource is not found
     */
    public static ImageIcon resolveBack() {
        return RESOLVER.resolveBack();
    }

}
