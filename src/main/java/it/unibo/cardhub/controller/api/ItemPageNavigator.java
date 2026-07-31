package it.unibo.cardhub.controller.api;

/**
 * Manages item list page navigation.
 */
public interface ItemPageNavigator {

    /**
     * Changes the current page of the item list to the given page number.
     *
     * @param page the target page number
     * @throws IllegalArgumentException if {@code page} is not a valid page number
     */
    void changePage(int page);

    /**
     * Changes the current page of the item list to the previous one.
     */
    void previousPage();

    /**
     * Changes the current page of the item list to the next one.
     */
    void nextPage();
}
