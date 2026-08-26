package it.unibo.cardhub.model.domain.api;

import java.util.List;
import java.util.Objects;

import it.unibo.cardhub.model.domain.exceptions.CardCollectionFullException;
import it.unibo.cardhub.model.domain.impl.MatchStateImpl;
import it.unibo.cardhub.model.domain.impl.PlayerImpl;

/**
 * Builder for matchState.
 */
public class MatchStateBuilder {
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
        public MatchStateBuilder(final String firstPlayerName, final String secondPlayerName,
                        final Deck firstPlayerDeck, final Deck secondPlayerDeck) {
        this.firstPlayerName = Objects.requireNonNull(firstPlayerName, "missing player name");
        this.secondPlayerName = Objects.requireNonNull(secondPlayerName, "missing player name");

        this.firstPlayerDeck = Objects.requireNonNull(firstPlayerDeck, "missing deck");
        this.secondPlayerDeck = Objects.requireNonNull(secondPlayerDeck, "missing deck");
        }

        /**
         * Sets the max hand size.
         * 
         * @param maxHandSizeValue max hand size
         * @return the Builder
         */
        public MatchStateBuilder maxHandSize(final int maxHandSizeValue) {
            this.maxHandSize = maxHandSizeValue;
            return this;
        }

        /**
         * Sets the starting hand size.
         * 
         * @param startingHandSizeValue starting hand size
         * @return the Builder
         */
        public MatchStateBuilder startingHandSize(final int startingHandSizeValue) {
            this.startingHandSize = startingHandSizeValue;
            return this;
        }

        /**
         * Sets the playfield size.
         * 
         * @param playfieldSizeValue playfield size
         * @return the Builder
         */
        public MatchStateBuilder playfieldSize(final int playfieldSizeValue) {
            this.playfieldSize = playfieldSizeValue;
            return this;
        }

        /**
         * Builds the Match state.
         * 
         * @return the desired MatchState
         */
        public MatchState build() {
            final Player player1 = new PlayerImpl(firstPlayerName, maxHandSize,
                                                    startingHandSize, firstPlayerDeck);
            final Player player2 = new PlayerImpl(secondPlayerName, maxHandSize,
                                                    startingHandSize, secondPlayerDeck);

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

            return new MatchStateImpl(List.of(player1, player2), playfieldSize);
        }

    }
