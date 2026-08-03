package it.unibo.cardhub.view.components;

import java.awt.Color;
import java.awt.LayoutManager;

import javax.swing.JPanel;

/**
 * Custom panel component used throughout the application.
 * 
 * <p>
 * This class extends {@link JPanel} and applies the default application
 * styling to panels.
 * </p>
 */
public class CHPanel extends JPanel {
    private static final long serialVersionUID = 1L;

    /**
     * Creates a new custom panel with the default configuration.
     */
    public CHPanel() {
        this(CHStyles.secondaryColor());
    }

    /**
     * Creates a new custom panel with the default background color and the chosen layouot.
     * 
     * @param layout the layout to be used
     */
    public CHPanel(LayoutManager layout) {
        this(CHStyles.secondaryColor(), layout);
    }

    /**
     * Creates a new custom panel with the chosen background color.
     * 
     * @param color the color to be used as background color
     */
    public CHPanel(Color color) {
        super.setBackground(color);
    }

    /**
     * Creates a new custom panel with the chosen color and layout.
     * 
     * @param color the color to be used as background color
     * @param layout the layout to be used
     */
    public CHPanel(Color color, LayoutManager layout) {
        super.setLayout(layout);
        super.setBackground(color);
    }
}
