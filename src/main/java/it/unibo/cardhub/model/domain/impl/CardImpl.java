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
         * @param id ID
         * @return this
         */
        public Builder<T> id(final String id) {
            this.id = id;
            return this;
        }

        /**
         * Name.
         * 
         * @param name name
         * @return this
         */
        public Builder<T> name(final String name) {
            this.name = Optional.ofNullable(name);
            return this;
        }

        /**
         * Attributes.
         * 
         * @param attributes attributes
         * @return this
         */
        public Builder<T> attributes(final T attributes) {
            this.attributes = attributes;
            return this;
        }

        /**
         * Value.
         * 
         * @param value value
         * @return this
         */
        public Builder<T> value(final int value) {
            this.value = value;
            return this;
        }

        /**
         * Description.
         * 
         * @param desc description
         * @return this
         */
        public Builder<T> desc(final String desc) {
            this.desc = Optional.ofNullable(desc);
            return this;
        }

        /**
         * Image.
         * 
         * @param image image
         * @return this
         */
        public Builder<T> image(final String image) {
            this.image = image;
            return this;
        }

        /**
         * Builds a card
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
