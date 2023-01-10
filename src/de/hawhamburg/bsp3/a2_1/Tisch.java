package de.hawhamburg.bsp3.a2_1;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

public class Tisch {
    private final List<Hand> hands = new ArrayList<>();

    //add hand to table
    public synchronized void addRandomHand(Spieler spieler) throws InterruptedException {
        //wait if player has already placed their hand
        while(hands.stream().anyMatch((h) -> h.getSpieler().equals(spieler))){
            wait();
        }

            //generate random hand
            SpielObjekt randomGameObject = SpielObjekt.values()[ThreadLocalRandom.current().nextInt(SpielObjekt.values().length)];
            System.out.println(spieler.toString() + ":" + randomGameObject.name());
            hands.add(new Hand(spieler, randomGameObject));

            notifyAll();

    }


    //get current hand count
    public int getHandCount(){
        return hands.size();
    }

    //clears the table
    public void clearTable(){
        hands.clear();
    }

    //get hand by index
    public Hand getHandByIndex(int index) {
        return hands.get(index);
    }

}
