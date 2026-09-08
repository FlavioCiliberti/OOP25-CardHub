package it.unibo.cardhub.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import javax.swing.JComponent;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unibo.cardhub.controller.impl.CardLayoutNavigator;
import it.unibo.cardhub.controller.impl.CreateMatchControllerImpl;
import it.unibo.cardhub.model.impl.CreateMatchModelImpl;
import it.unibo.cardhub.view.components.ScreenView;

/**
 * Test class for {@link CreateMatchControllerImpl}.
 *
 * <p>Not covered: {@code tryCreatingMatch(...)}. Every branch of it
 * ends up calling a blocking Swing popup — {@code MatchViewImpl.showCurrentPlayer()} for
 * valid parameters, and {@code CreateMatchViewImpl.showInvalidForm()} for invalid ones.
 * Since {@code CreateMatchControllerImpl} builds its view directly in the constructor
 * there is no clean way to substitute a silent view here, so that method is left untested.
 * </p>
 */
final class CreateMatchControllerImplTest {

    private CreateMatchModelImpl model;
    private CardLayoutNavigator navigator;
    private CreateMatchControllerImpl controller;

    @BeforeEach
    void setUp() {
        model = new CreateMatchModelImpl();
        navigator = new CardLayoutNavigator();
        controller = new CreateMatchControllerImpl(model, navigator);
    }

    @Test
    void testGetMinHandSizeDelegatesToTheModel() {
        assertEquals(model.getMinHandSize(), controller.getMinHandSize());
    }

    @Test
    void testGetMaxHandSizeDelegatesToTheModel() {
        assertEquals(model.getMaxHandSize(), controller.getMaxHandSize());
    }

    @Test
    void testGetMinFieldSizeDelegatesToTheModel() {
        assertEquals(model.getMinFieldSize(), controller.getMinFieldSize());
    }

    @Test
    void testGetMaxFieldSizeDelegatesToTheModel() {
        assertEquals(model.getMaxFieldSize(), controller.getMaxFieldSize());
    }

    @Test
    void testGetDefaultHandSizeDelegatesToTheModel() {
        assertEquals(model.getDefaultHandSize(), controller.getDefaultHandSize());
    }

    @Test
    void testGetDefaultFieldSizeDelegatesToTheModel() {
        assertEquals(model.getDefaultFieldSize(), controller.getDefaultFieldSize());
    }

    @Test
    void testGetDecksDelegatesToTheModel() {
        assertEquals(model.getDecks(), controller.getDecks());
    }

    @Test
    void testShowScreenAddsViewToTheNavigator() {
        controller.showScreen();

        final JComponent root = navigator.getRootView();
        assertEquals(1, root.getComponentCount());
    }

    @Test
    void testGoBackNavigatesHomeAndRemovesTheTransientScreen() {
        final ScreenView homeView = new ScreenView();
        navigator.show(ScreenId.HOME, homeView);
        controller.showScreen();

        controller.goBack();

        final JComponent root = navigator.getRootView();
        assertEquals(1, root.getComponentCount());
        assertEquals(homeView, root.getComponent(0));
    }
}

