package it.unibo.cardhub.view.util;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

import javax.swing.ImageIcon;

import it.unibo.cardhub.model.domain.api.Card;

/**
 * A utility class for resolving images associated with cards.
 */
public final class ImageResolver {

    private static final String IMAGE_BASE_PATH = "/it/unibo/cardhub/view/";
    private static final String CARD_BACK_IMAGE = "Back.png";
    private static final Map<String, ImageIcon> CACHE = new ConcurrentHashMap<>();

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
        final String path = IMAGE_BASE_PATH + card.image();
        return CACHE.computeIfAbsent(path, ImageResolver::load);
    }

    /**
     * Resolves the image associated with every back of a card.
     * 
     * @return an ImageIcon representing the image of the back of the cards
     * @throws NullPointerException if the image resource is not found
     */
    public static ImageIcon resolveBack() {
        final String path = IMAGE_BASE_PATH + CARD_BACK_IMAGE;
        return CACHE.computeIfAbsent(path, ImageResolver::load);
    }

    private static ImageIcon load(final String path) {
        return new ImageIcon(Objects.requireNonNull(ImageResolver.class.getResource(path), "Image not found: " + path));
    }

}
