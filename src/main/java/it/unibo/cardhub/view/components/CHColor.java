package it.unibo.cardhub.view.components;

public enum CHColor {
    
    PRIMARY(0xE58909), // scelta discutibile
    SECONDARY(0x000000),
    TERTIARY(0xffffff);

    private final int colorCode;

    private CHColor (final int colorCode){
        this.colorCode = colorCode;
    } 

    public int getCode(){
        return colorCode;
    }
}
