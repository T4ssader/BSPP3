package de.hawhamburg.bsp3.a1;

import java.util.concurrent.Semaphore;

public class Paydesk {
    //payment wait time max
    private static int PAY_WAIT_TIME_MAX = 1000;

    private String name;
    private int counter = 0;

    private int queueLength = 0;

    //Semaphores are often used to restrict the number of threads
    //than can access some resource (pool of items).
    //in our case the number of threads is restricted to 1
    private Semaphore paydeskLock = new Semaphore(1);

    public Paydesk(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getQueueLength(){
        //return length of queue
        //return paydeskLock.getQueueLength();
        return queueLength;
    }


    public void pay() throws InterruptedException {
        //add student to queue (Warteschlange)
        paydeskLock.acquire();


        //waits random time before paying
        Thread.sleep(Math.round(Math.random() * PAY_WAIT_TIME_MAX));
        //increments pay counter
        counter++;
        this.queueLength--;
        //unlock the paydesk for other students
        paydeskLock.release();
    }

    //Amount of Payments
    public int getCounter() {
        return counter;
    }

    public void incrementQueueLength() {
        this.queueLength++;
    }
}
