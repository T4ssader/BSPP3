package de.hawhamburg.bsp3.a2_1;

import java.util.HashMap;
import java.util.Map;

public class Schiedsrichter extends Thread{
    private Tisch table;

    //count of rounds played
    private int roundCount=0;
    //count of rounds resulting in a draw
    private int drawCount=0;
    //map with wins per player
    private Map<Spieler, Integer> winCount = new HashMap<>();

    public Schiedsrichter(Tisch table){
        this.table = table;
    }


    @Override
    public void run() {
        //run until interrupted
        while (true && !interrupted()){
            //synchronize over table
            synchronized (table){
                try {
                    //if two players have submitted their hand
                    while(table.getHandCount() < 2) {
                        table.wait();
                    }


                        //add counters and determine winner
                        calculateWinningPlayer(table.getHandByIndex(0), table.getHandByIndex(1));
                        //increment round counter
                        roundCount++;

                        //clear all hands from table
                        table.clearTable();

                        //start next round and wakes up all threads that are waiting
                        table.notifyAll();
                        //new round starts


                } catch (InterruptedException e) {
                    break;
                }

            }
        }

    }

    private void calculateWinningPlayer(Hand hand1, Hand hand2){
        if(hand1.getSpielObjekt() == hand2.getSpielObjekt()){
            //if draw
            drawCount++;
        }else if(hand1.getSpielObjekt() == SpielObjekt.PAPIER){
            if(hand2.getSpielObjekt() == SpielObjekt.STEIN){
                addWinToPlayer(hand1.getSpieler());
            }else{
                addWinToPlayer(hand2.getSpieler());
            }
        }else if(hand1.getSpielObjekt() == SpielObjekt.STEIN){
            if(hand2.getSpielObjekt() == SpielObjekt.PAPIER){
                addWinToPlayer(hand2.getSpieler());
            }else{
                addWinToPlayer(hand1.getSpieler());
            }
        }else{
            if(hand2.getSpielObjekt() == SpielObjekt.PAPIER){
                addWinToPlayer(hand1.getSpieler());
            }else{
                addWinToPlayer(hand2.getSpieler());
            }
        }
    }

    private void addWinToPlayer(Spieler spieler){
        if(winCount.containsKey(spieler)){
            //increment win count from player
            winCount.put(spieler, winCount.get(spieler) + 1);
        }else{
            //if winCount doesnt have spieler key, than count = 1
            winCount.put(spieler, 1);
        }
    }

    public int getRoundCount() {
        return roundCount;
    }

    public int getDrawCount() {
        return drawCount;
    }

    public Map<Spieler, Integer> getWinCount() {
        return winCount;
    }
}
