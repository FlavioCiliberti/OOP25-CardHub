package it.unibo.cardhub.view.components;

import java.awt.BorderLayout;

import javax.swing.BorderFactory;

/**
 * Represents a generic list header panel.
 */
public final class CHListHeader extends CHPanel {

    private static final long serialVersionUID = 1L;
    private final CHLabel title;
    private final CHLabel subtitle;

    /**
     * Creates a new header panel.
     * 
     * @param titleText title
     * @param subtitleText subtitle
     */
    public CHListHeader(final String titleText, final String subtitleText) {
        super();

        this.setLayout(new BorderLayout(8, 8));

        this.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(CHStyles.primaryColor()),
            BorderFactory.createEmptyBorder(10, 10, 10, 10)));

        title = new CHLabel(titleText);
        subtitle = new CHLabel(subtitleText);

        this.add(title, BorderLayout.WEST);
        this.add(subtitle, BorderLayout.CENTER);
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
