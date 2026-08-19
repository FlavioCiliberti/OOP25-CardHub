package it.unibo.cardhub;

import it.unibo.cardhub.controller.ScreenId;
import it.unibo.cardhub.controller.api.MatchController;
import it.unibo.cardhub.controller.api.Navigator;
import it.unibo.cardhub.controller.impl.CardLayoutNavigator;
import it.unibo.cardhub.controller.impl.MatchControllerImpl;
import it.unibo.cardhub.io.api.DeckFactory;
import it.unibo.cardhub.io.impl.DeckFactoryImpl;
import it.unibo.cardhub.model.domain.api.Deck;
import it.unibo.cardhub.model.logic.impl.MatchFactory;
import it.unibo.cardhub.view.components.CHFrame;

/**
 * Main class of the CardHub application.
 */
public final class App {

    private App() {

    }

    /**
     * Application entry point.
     * 
     * @param args command line arguments
     */
    public static void main(final String[] args) {

        final Navigator navigator = new CardLayoutNavigator();
        final CHFrame frame = new CHFrame();
        frame.add(navigator.getRootView());

        final DeckFactory deckFactory = new DeckFactoryImpl();

        final Deck deck1 = deckFactory.createPokemonDeck();
        final Deck deck2 = deckFactory.createPokemonDeck();

        final MatchController match = new MatchControllerImpl(
                                    MatchFactory.createFreeMatch("Ash", 
                                    "Misty", deck1, deck2), navigator);
        navigator.show(ScreenId.MATCH, match.getView());

        frame.setVisible(true);
    }
}
