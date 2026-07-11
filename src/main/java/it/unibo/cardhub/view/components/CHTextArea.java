package it.unibo.cardhub.view.components;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JTextArea;

public class CHTextArea extends JTextArea {
    public CHTextArea(){
        customize();
    }

    private void customize(){
        this.setBackground(new Color(CHColor.SECONDARY.getCode()));
        this.setForeground(new Color(CHColor.TERTIARY.getCode()));
        this.setCaretColor(new Color(CHColor.TERTIARY.getCode()));
        this.setBorder(BorderFactory.createLineBorder(new Color(CHColor.TERTIARY.getCode())));
        this.setBorder(BorderFactory.createCompoundBorder(
            this.getBorder(),
            BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
    }

}
