package de.hawhamburg.bsp3.a2_2;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Tisch {
    private final List<Hand> hands = new ArrayList<>();

    //lock
    private ReentrantLock lock = new ReentrantLock();
    //condition
    private Condition tableFullCondition = lock.newCondition();
    private Condition newObjectAtTableCondition = lock.newCondition();

    //add hand to table
    public void addHand(Spieler spieler, SpielObjekt spielObjekt){
        hands.add(new Hand(spieler, spielObjekt));
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

    public Condition getTableFullCondition() {
        return tableFullCondition;
    }

    public ReentrantLock getLock() {
        return lock;
    }

    public Condition getNewObjectAtTableCondition() {
        return newObjectAtTableCondition;
    }

    public List<Hand> getHands() {
        return hands;
    }
}
