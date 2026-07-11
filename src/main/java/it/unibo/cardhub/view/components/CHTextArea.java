package it.unibo.cardhub.view.components;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JTextArea;
import javax.swing.border.Border;

public class CHTextArea extends JTextArea {
    public CHTextArea(){
        customize();
    }

    private void customize(){
        this.setBackground(new Color(CHColor.SECONDARY.getCode()));
        this.setForeground(new Color(CHColor.TERTIARY.getCode()));
        this.setPreferredSize(new Dimension(100, 20));
        this.setBorder(BorderFactory.createLineBorder(new Color(CHColor.TERTIARY.getCode())));
    }

}
