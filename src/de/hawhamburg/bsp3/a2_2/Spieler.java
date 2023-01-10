package de.hawhamburg.bsp3.a2_2;

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
        while (true && !interrupted()) {
            try {
                //lock table
                table.getLock().lock();

                while(table.getHands().stream().anyMatch((h) -> h.getSpieler().equals(this))){
                    //wait until new round starts
                    table.getTableFullCondition().await();
                }


                //generate random hand
                SpielObjekt randomGameObject = SpielObjekt.values()[ThreadLocalRandom.current().nextInt(SpielObjekt.values().length)];
                //add hand to table
                table.addHand(this, randomGameObject);
                table.getNewObjectAtTableCondition().signal();




            } catch (InterruptedException e) {
                break;
            } finally {
                //unlock table
                table.getLock().unlock();
            }
        }
    }

    @Override
    public String toString() {
        return  playerName;
    }
}
