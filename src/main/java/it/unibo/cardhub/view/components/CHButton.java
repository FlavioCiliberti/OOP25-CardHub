package it.unibo.cardhub.view.components;

import java.awt.Color;

import javax.swing.JButton;

public class CHButton extends JButton{
    CHButton(String text){
        super(text);
        this.setBackground(new Color(CHColor.PRIMARY.getCode()));
        this.setForeground(new Color(CHColor.SECONDARY.getCode()));
        this.setFocusable(false);
    }
}
