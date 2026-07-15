package it.unibo.cardhub.view.components;

import javax.swing.JPanel;

public class CHEntryPanel extends CHPanel {

    private final CHLabel firstLabel;
    private final CHLabel secondLabel;
    private final CHButton firstButton;
    private final CHButton secondButton;

    public CHEntryPanel(final String title, final String subtitle, final String firstButtonText, final String secondButtonText) {
        firstLabel = new CHLabel(title);
        secondLabel = new CHLabel(subtitle);
        firstButton = new CHButton(firstButtonText);
        secondButton = new CHButton(secondButtonText);
    }
}
