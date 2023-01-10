package de.hawhamburg.bsp3.a2_1;

import java.util.concurrent.ThreadLocalRandom;

public class Spieler extends Thread{
    private String playerName;
    private Tisch table;

    public Spieler(String playerName, Tisch table){
        this.playerName = playerName;
        this.table = table;
    }

    @Override
    public void run() {
        //run until interrupted
        while (!interrupted()) {

            try {
                //add random hand to table
                table.addRandomHand(this);
            } catch (InterruptedException e) {
                break;
            }


        }
    }

    @Override
    public String toString() {
        return  playerName;
    }
}
