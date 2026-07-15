package it.unibo.cardhub.view.components;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;

/**
 * Generic panel used to display en element of a list (deck, card, saved match).
 */
public final class CHEntryPanel extends CHPanel {

    private static final long serialVersionUID = 1L;
    private final CHLabel title;
    private final CHLabel subtitle;
    private final CHPanel buttonPanel;

    /**
     * Creates a new entry panel.
     * 
     * @param titleText title
     * @param subtitleText subtitle
     */
    public CHEntryPanel(final String titleText, final String subtitleText) {

        super();

        this.setLayout(new BorderLayout(8, 8));
        this.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(CHColor.PRIMARY.getCode())), 
            BorderFactory.createEmptyBorder(10, 10, 10, 10)));

        title = new CHLabel(titleText);
        subtitle = new CHLabel(subtitleText);
        buttonPanel = new CHPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.setOpaque(false);

        this.add(title, BorderLayout.NORTH);
        this.add(subtitle, BorderLayout.CENTER);
        this.add(buttonPanel, BorderLayout.SOUTH);
    }

    /**
     * Adds a button to the panel.
     * 
     * @param button button to add
     */
    public void addButton(final CHButton button) {
        buttonPanel.add(button);
    }

    /**
     * Changes the title.
     * 
     * @param text new title
     */
    public void setTitle(final String text) {
        title.setText(text);
    }

    /**
     * Changes the subtitle.
     * 
     * @param text new subtitle
     */
    public void setSubtitle(final String text) {
        subtitle.setText(text);
    }
}
