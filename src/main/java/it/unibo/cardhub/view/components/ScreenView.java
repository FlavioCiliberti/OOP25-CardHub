package it.unibo.cardhub.view.components;

import java.awt.Color;

import javax.swing.JPanel;

public class ScreenView extends JPanel{
    public ScreenView(){
        customize();
    }

    private void customize(){
        this.setBackground(new Color(CHColor.SECONDARY.getCode()));
    }
}
