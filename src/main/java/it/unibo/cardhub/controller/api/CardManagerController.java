package it.unibo.cardhub.controller.api;

import java.util.Optional;
import java.util.Set;

/**
 * Controller responsible for creating and editing persistent {@code Card} instances.
 * 
 * <p>
 * Coordinates validation of user-provided card data, persistence of valid
 * cards to the application's file resources, and notification of the view
 * when validation fails or a new card is being created.
 * </p>
 */
public interface CardManagerController extends BackNavigableScreen {

    /**
     * Validates the provided card data and, if all fields are valid, persists the
     * card in the application's file resources. If validation fails, the view is
     * notified so that it can render a notice banner describing the issue.
     *
     * @param name the name of the card; must not be {@code null} or blank
     * @param value the numeric value associated with the card
     * @param imgPath the path to the card's image resource; must not be {@code null} or blank
     * @param description an optional textual description of the card
     * @param deckIds the identifiers of the decks the card belongs to; must not be {@code null}
     */
    void saveCard(String name, int value, String imgPath, Optional<String> description, Set<Integer> deckIds);

    /**
     * Clears the currently displayed card data in the view and initializes a new,
     * empty {@code Card} instance ready to receive new input.
     */
    void newCard();
}
