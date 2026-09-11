package it.unibo.cardhub.view.util.impl;

import java.awt.Image;
import java.net.URL;
import java.util.Objects;

import javax.swing.ImageIcon;

import it.unibo.cardhub.model.domain.api.Card;
import it.unibo.cardhub.view.util.api.CardImageResolver;

/**
 * Base {@link CardImageResolver} that loads and scales an image from the
 * classpath on every call.
 */
public final class SimpleCardImageResolver implements CardImageResolver {

    private static final String IMAGE_BASE_PATH = "/it/unibo/cardhub/io/";
    private static final String CARD_BACK_IMAGE = "/it/unibo/cardhub/view/Back.png";

    private static final int CARD_WIDTH = 66;
    private static final int CARD_HEIGHT = 96;

    /**
     * {@inheritDoc}
     */
    @Override
    public ImageIcon resolve(final Card<?> card) {
        Objects.requireNonNull(card, "card must not be null");
        return load(IMAGE_BASE_PATH + card.image());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ImageIcon resolveBack() {
        return load(CARD_BACK_IMAGE);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getCardWidth() {
        return CARD_WIDTH;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getCardHeight() {
        return CARD_HEIGHT;
    }

    private ImageIcon load(final String path) {
        final URL url = Objects.requireNonNull(getClass().getResource(path), "Image not found: " + path);
        return new ImageIcon(new ImageIcon(url).getImage().getScaledInstance(
            CARD_WIDTH,
            CARD_HEIGHT,
            Image.SCALE_SMOOTH
        ));
    }

}
