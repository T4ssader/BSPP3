package de.hawhamburg.bsp3.a1;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.Semaphore;

public class Paydesks {
    //list of Paydesks
    private List<Paydesk> paydesks = new ArrayList<>();
    private Semaphore paydesksQueueLock = new Semaphore(1);

    //adding paydesk to our List of Paydesks
    public void addPaydesk(Paydesk p){
        this.paydesks.add(p);
    }

    //getting the Paydesk with the shortest Line
    public Paydesk getPaydeskWithShortestLine() throws InterruptedException {
        //locks this method
        paydesksQueueLock.acquire();
        //gets the paydesk with the shortest line
        Paydesk paydeskWithShortestLine = paydesks.stream().min(Comparator.comparing(Paydesk::getQueueLength)).orElse(null);
        paydeskWithShortestLine.incrementQueueLength();

        //unlock this method for other students
        paydesksQueueLock.release();

        return paydeskWithShortestLine;
    }

    public List<Paydesk> getPaydesks() {
        return paydesks;
    }
}
