package it.unibo.cardhub.view.util.api;

import javax.swing.ImageIcon;

import it.unibo.cardhub.model.domain.api.Card;

/**
 * Resolves the {@link ImageIcon} associated with a {@link Card}, and the
 * image shown on the back of any card.
 */
public interface CardImageResolver {
    /**
     * Resolves the image associated with the given card.
     *
     * @param card the card for which to resolve the image
     * @return an {@link ImageIcon} representing the card's image
     * @throws NullPointerException if the image resource is not found
     */
    ImageIcon resolve(Card<?> card);

    /**
     * Resolves the image associated with every back of a card.
     *
     * @return an {@link ImageIcon} representing the image of the back of the cards
     * @throws NullPointerException if the image resource is not found
     */
    ImageIcon resolveBack();

    /**
     * Getter for the card width.
     * 
     * @return the card width in pixels
     */
    int getCardWidth();

    /**
     * Getter for the card height.
     * 
     * @return the card height in pixels
     */
    int getCardHeight();
}
