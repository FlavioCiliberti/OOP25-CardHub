package it.unibo.cardhub.model.logic.impl;

import java.util.ArrayList;
import java.util.Arrays;

import it.unibo.cardhub.model.domain.api.MatchState;
import it.unibo.cardhub.model.domain.api.Player;
import it.unibo.cardhub.model.domain.exceptions.CardCollectionFullException;
import it.unibo.cardhub.model.domain.impl.MatchStateImpl;
import it.unibo.cardhub.model.logic.api.LoserCardAction;
import it.unibo.cardhub.model.logic.api.Match;
import it.unibo.cardhub.model.logic.api.MatchLogic;
import it.unibo.cardhub.model.logic.api.PlayerEnum;
import it.unibo.cardhub.model.logic.api.WinnerCardAction;

class MatchImpl implements Match{
    private final MatchState matchState;
    private final MatchLogic matchLogic;

    private final int maxHandSize;
    private final int startingHandSize;
    private final int playerFieldSize;
    private final boolean autoDraw;
    private final WinnerCardAction winnerCardAction;
    private final LoserCardAction loserCardAction;

    public MatchImpl(Player player1, Player player2,
                        int maxHandSize, int startingHandSize,
                        int playerFieldSize, boolean autoDraw,
                        WinnerCardAction winnerAction, LoserCardAction loserAction,
                        MatchLogic matchLogic) {
        matchState = new MatchStateImpl(new ArrayList<>(Arrays.asList(player1, player2)));

        this.matchLogic = matchLogic;
        this.maxHandSize = maxHandSize;
        this.startingHandSize = startingHandSize;
        this.playerFieldSize = playerFieldSize;
        this.autoDraw = autoDraw;
        this.winnerCardAction = winnerAction;
        this.loserCardAction = loserAction;
    }

    public void drawCard(PlayerEnum player) throws CardCollectionFullException {
        try {
            matchState.getPlayers().get(player.getIndex()).drawCard();
        } catch (CardCollectionFullException e) {
            throw new CardCollectionFullException("Hand is full!");
        }
    }

    public PlayerEnum getTurnPlayer() {
        return matchLogic.getCurrentPlayer();
    }

    public void changeTurn() {
        this.matchLogic.changeTurn();
        if (autoDraw) {
            try {
                this.drawCard(this.getTurnPlayer());
            } catch (CardCollectionFullException e) {
            }
        }
    }
}
