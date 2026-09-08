package it.unibo.cardhub.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.awt.Component;

import static org.junit.jupiter.api.Assertions.assertFalse;

import javax.swing.JComponent;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unibo.cardhub.controller.impl.CardLayoutNavigator;
import it.unibo.cardhub.view.components.ScreenView;

/**
 * Test class for the {@link CardLayoutNavigator} class.
 */
final class CardLayoutNavigatorTest {

    private CardLayoutNavigator navigator;
    private JComponent root;

    @BeforeEach
    void setUp() {
        navigator = new CardLayoutNavigator();
        root = navigator.getRootView();
    }

    @Test
    void testShowHomeAddsHomeScreenOnlyOnce() {
        final ScreenView homeView = new ScreenView();

        navigator.show(ScreenId.HOME, homeView);
        navigator.show(ScreenId.HOME, homeView);

        assertEquals(1, root.getComponentCount());
        assertTrue(containsComponent(homeView));
    }

    @Test
    void testShowTransientScreenDoesNotRemoveHome() {
        final ScreenView homeView = new ScreenView();
        final ScreenView matchView = new ScreenView();

        navigator.show(ScreenId.HOME, homeView);
        navigator.show(ScreenId.MATCH, matchView);

        assertEquals(2, root.getComponentCount());
        assertTrue(containsComponent(homeView));
        assertTrue(containsComponent(matchView));
    }

    @Test
    void testShowingANewTransientScreenRemovesPreviousOne() {
        final ScreenView homeView = new ScreenView();
        final ScreenView matchView = new ScreenView();
        final ScreenView createMatchView = new ScreenView();

        navigator.show(ScreenId.HOME, homeView);
        navigator.show(ScreenId.MATCH, matchView);
        navigator.show(ScreenId.CREATE_MATCH, createMatchView);

        assertEquals(2, root.getComponentCount());
        assertFalse(containsComponent(matchView));
        assertTrue(containsComponent(createMatchView));
    }

    @Test
    void testGoHomeRemovesTheCurrentTransientScreen() {
        final ScreenView homeView = new ScreenView();
        final ScreenView matchView = new ScreenView();

        navigator.show(ScreenId.HOME, homeView);
        navigator.show(ScreenId.MATCH, matchView);

        navigator.goHome();

        assertEquals(1, root.getComponentCount());
        assertTrue(containsComponent(homeView));
    }

    private boolean containsComponent(final ScreenView view) {
        for (final Component c : root.getComponents()) {
            if (c == view) {
                return true;
            }
        }
        return false;
    }
}

