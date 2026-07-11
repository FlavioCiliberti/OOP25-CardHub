package it.unibo.cardhub.view.components;

import java.awt.Color;
import javax.swing.JPanel;

class CHPanel extends JPanel{
    public CHPanel(){
        customize();
    }

    private void customize(){
        this.setBackground(new Color(CHColor.SECONDARY.getCode()));
    }
}