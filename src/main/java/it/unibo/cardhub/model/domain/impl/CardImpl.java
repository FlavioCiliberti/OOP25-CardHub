package it.unibo.cardhub.model.domain.impl;

import java.util.Optional;

import it.unibo.cardhub.model.domain.api.Card;

/**
 * Card implementation.
 * 
 * @param id card ID
 * @param name card name
 * @param attributes card attribute
 * @param value card value
 * @param desc card description
 * @param image image file name
 * @param <T> card attribute type
 */
public record CardImpl<T>(
    String id, 
    Optional<String> name, 
    T attributes, 
    int value, 
    Optional<String> desc, 
    String image
) implements Card<T> {

    /**
     * Card builder for a more flexible instantiation.
     * 
     * @param <T> card attribute type
     */
    public static final class Builder<T> {

        private String id;
        private Optional<String> name = Optional.empty();
        private T attributes;
        private int value;
        private Optional<String> desc = Optional.empty();
        private String image;

        /**
         * ID.
         * 
         * @param cardId ID
         * @return this
         */
        public Builder<T> id(final String cardId) {
            this.id = cardId;
            return this;
        }

        /**
         * Name.
         * 
         * @param cardName name
         * @return this
         */
        public Builder<T> name(final String cardName) {
            this.name = Optional.ofNullable(cardName);
            return this;
        }

        /**
         * Attributes.
         * 
         * @param cardAttributes attributes
         * @return this
         */
        public Builder<T> attributes(final T cardAttributes) {
            this.attributes = cardAttributes;
            return this;
        }

        /**
         * Value.
         * 
         * @param cardValue value
         * @return this
         */
        public Builder<T> value(final int cardValue) {
            this.value = cardValue;
            return this;
        }

        /**
         * Description.
         * 
         * @param cardDesc description
         * @return this
         */
        public Builder<T> desc(final String cardDesc) {
            this.desc = Optional.ofNullable(cardDesc);
            return this;
        }

        /**
         * Image.
         * 
         * @param cardImage image
         * @return this
         */
        public Builder<T> image(final String cardImage) {
            this.image = cardImage;
            return this;
        }

        /**
         * Builds a card.
         * 
         * @return card
         */
        public Card<T> build() {
            return new CardImpl<>(
                id,
                name,
                attributes,
                value,
                desc,
                image
            );
        }
    }
}
