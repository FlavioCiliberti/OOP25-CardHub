package it.unibo.cardhub.view.components;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;

public class CHTitle extends JLabel{
    public CHTitle(String text){
        super(text);
        customize();
    }

    private void customize(){
        this.setForeground(new Color(CHColor.PRIMARY.getCode()));
        this.setFont(new Font("SansSerif", Font.BOLD, 24));
    }
}
