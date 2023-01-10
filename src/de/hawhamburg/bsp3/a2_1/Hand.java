package de.hawhamburg.bsp3.a2_1;

public class Hand {
    private Spieler spieler;
    private SpielObjekt spielObjekt;

    public Hand(Spieler spieler, SpielObjekt spielObjekt) {
        this.spieler = spieler;
        this.spielObjekt = spielObjekt;
    }

    public Spieler getSpieler() {
        return spieler;
    }

    public SpielObjekt getSpielObjekt() {
        return spielObjekt;
    }
}
