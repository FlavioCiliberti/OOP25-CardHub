package it.unibo.cardhub.controller.impl;

import java.awt.CardLayout;

import javax.swing.JComponent;
import javax.swing.JPanel;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.cardhub.controller.ScreenId;
import it.unibo.cardhub.controller.api.Navigator;
import it.unibo.cardhub.view.components.ScreenView;

/**
 * {@link Navigator} implementation that uses Swing {@link CardLayout}.
 * 
 * <p>
 * The HOME screen is added once and stays in the container for the
 * lifetime of the navigator. Every other screen is treated as
 * "transient": it is added when shown and removed as soon as it is
 * replaced.
 * </p>
 */
public class CardLayoutNavigator implements Navigator {
    private static final String HOME = ScreenId.HOME.name();
    private final CardLayout layout = new CardLayout();
    private final JPanel container = new JPanel();
    private ScreenView currentTransientView;
    private boolean homeAdded;

    /**
     * Creates a navigator with an empty container with
     * {@link CardLayout}.
     */
    public CardLayoutNavigator() {
        container.setLayout(layout);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @SuppressFBWarnings(value = "EI", justification =
            "The root JComponent must be returned by reference so it "
                    + "can be embedded in the real application window; "
                    + "cannot return a defensive copy for this purpuse.")
    public JComponent getRootView() {
        return container;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @SuppressFBWarnings(value = "EI2", justification =
            "The view is stored by reference because it must be the "
                    + "exact JComponent instance added to the container, "
                    + "so it can later be identified and removed.")
    public void show(final ScreenId id, final ScreenView view) {
        if (id == ScreenId.HOME) {
            if (!homeAdded) {
                container.add(view, HOME); 
                homeAdded = true;
            }
            clearTransientView();
            layout.show(container, HOME);
            return;
        }
        clearTransientView();
        container.add(view, id.name());
        layout.show(container, id.name());
        currentTransientView = view;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void goHome() {
        layout.show(container, HOME);
        clearTransientView();
    }

    /**
     * Removes the current transient view from the container and clears
     * the reference to it.
     */
    private void clearTransientView() {
        if (currentTransientView != null) {
            container.remove(currentTransientView); 
            currentTransientView = null;
        }
    }
}
