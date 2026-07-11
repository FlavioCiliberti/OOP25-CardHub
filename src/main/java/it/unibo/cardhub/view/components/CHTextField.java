package it.unibo.cardhub.view.components;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JTextField;

public class CHTextField extends JTextField{
    public CHTextField(){
        customize();
    }

    private void customize(){
        this.setBackground(new Color(CHColor.SECONDARY.getCode()));
        this.setForeground(new Color(CHColor.TERTIARY.getCode()));
        this.setCaretColor(new Color(CHColor.TERTIARY.getCode()));
        this.setPreferredSize(new Dimension(100, 26));
        this.setBorder(BorderFactory.createLineBorder(new Color(CHColor.TERTIARY.getCode())));
        this.setBorder(BorderFactory.createCompoundBorder(
            this.getBorder(),
            BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
    }
}
