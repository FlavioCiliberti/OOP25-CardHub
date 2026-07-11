package it.unibo.cardhub.view.components;

import java.awt.Color;

import javax.swing.JLabel;

public class CHLabel extends JLabel {
    public CHLabel(String text){
        super(text);
        customize();
    }

    private void customize(){
        this.setForeground(new Color(CHColor.TERTIARY.getCode()));
    }
}
