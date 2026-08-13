package it.unibo.cardhub.view.util;

import java.util.Objects;

import javax.swing.ImageIcon;

import it.unibo.cardhub.model.domain.api.Card;

/**
 * A utility class for resolving images associated with cards.
 */
public final class ImageResolver {

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
        final String path = "it/unibo/cardhub/view/images/resources/" + card.image();
        return new ImageIcon(Objects.requireNonNull(ImageResolver.class.getResource(path), "Image not found: " + path));
    }
}
