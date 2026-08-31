package it.unibo.cardhub.model.domain.impl;

import java.util.Objects;

import it.unibo.cardhub.model.domain.api.Deck;
import it.unibo.cardhub.model.domain.api.MatchState;
import it.unibo.cardhub.model.domain.api.MatchStateBuilder;
import it.unibo.cardhub.model.domain.api.Player;
import it.unibo.cardhub.model.domain.exceptions.CardCollectionFullException;

/**
 * Implementation of the MatchStateBuilder.
 */
public class MatchStateBuilderImpl implements MatchStateBuilder {
        private static final int DEFAULT_MAX_HAND_SIZE = 5;
        private static final int DEFAULT_STARTING_HAND_SIZE = 5;
        private static final int DEFAULT_PLAYFIELD_SIZE = 3;

        private final String firstPlayerName;
        private final String secondPlayerName;

        private final Deck firstPlayerDeck;
        private final Deck secondPlayerDeck;

        private int maxHandSize = DEFAULT_MAX_HAND_SIZE;
        private int startingHandSize = DEFAULT_STARTING_HAND_SIZE;
        private int playfieldSize = DEFAULT_PLAYFIELD_SIZE;

        /**
         * Constructor for MatchStateBuilder.
         * 
         * @param firstPlayerName player 1 name
         * @param secondPlayerName player 2 name
         * @param firstPlayerDeck player 1 deck
         * @param secondPlayerDeck player 2 deck
         */
        public MatchStateBuilderImpl(final String firstPlayerName, final String secondPlayerName,
                        final Deck firstPlayerDeck, final Deck secondPlayerDeck) {
        this.firstPlayerName = Objects.requireNonNull(firstPlayerName, "missing player name");
        this.secondPlayerName = Objects.requireNonNull(secondPlayerName, "missing player name");

        this.firstPlayerDeck = Objects.requireNonNull(firstPlayerDeck, "missing deck");
        this.secondPlayerDeck = Objects.requireNonNull(secondPlayerDeck, "missing deck");
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public MatchStateBuilder maxHandSize(final int maxHandSizeValue) {
            this.maxHandSize = maxHandSizeValue;
            return this;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public MatchStateBuilder startingHandSize(final int startingHandSizeValue) {
            this.startingHandSize = startingHandSizeValue;
            return this;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public MatchStateBuilder playfieldSize(final int playfieldSizeValue) {
            this.playfieldSize = playfieldSizeValue;
            return this;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public MatchState build() {
            final Player player1 = new PlayerImpl(firstPlayerName, maxHandSize,
                                                    startingHandSize, firstPlayerDeck);
            final Player player2 = new PlayerImpl(secondPlayerName, maxHandSize,
                                                    startingHandSize, secondPlayerDeck);
            player1.shuffleDeck();
            player2.shuffleDeck();

            //draw initial cards
            for (int i = 0; i < startingHandSize; i++) {
                try {
                    player1.drawCard();
                    player2.drawCard();
                } catch (final CardCollectionFullException e) {
                    //startingHandSize <= maxHandSize so the exception should never trigger
                    throw new IllegalStateException("Hand already had cards in it on instantiation", e);
                }
            }

            return new MatchStateImpl(player1, player2, playfieldSize);
        }
}
