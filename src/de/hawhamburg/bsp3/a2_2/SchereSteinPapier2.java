package de.hawhamburg.bsp3.a2_2;

import java.util.Comparator;

public class SchereSteinPapier2 {
    public static final int PROGRAM_EXECUTION_TIME = 1000; //Execution Time for Program Length

    public static void main(String[] args) throws InterruptedException {
        //initialize tisch
        Tisch tisch = new Tisch();

        //initialize player objects
        Spieler spieler1 = new Spieler("Spieler 1", tisch);
        Spieler spieler2 = new Spieler("Spieler 2", tisch);

        //start player threads
        spieler1.start();
        spieler2.start();

        //init and start schiedsrichter
        Schiedsrichter schiedsrichter = new Schiedsrichter(tisch);
        schiedsrichter.start();

        //wait for program length
        Thread.sleep(PROGRAM_EXECUTION_TIME);

        //interrupt spieler threads
        spieler1.interrupt();
        spieler2.interrupt();
        schiedsrichter.interrupt();

        //wait for all threads finished
        spieler1.join();
        spieler2.join();
        schiedsrichter.join();


        //output
        System.out.println(String.format("Rundenanzahl: %s", schiedsrichter.getRoundCount()));
        System.out.println(String.format("Runden Unentschieden: %s", schiedsrichter.getDrawCount()));
        schiedsrichter.getWinCount().entrySet().stream().forEach((entry) -> {
            System.out.println(String.format("Spieler '%s' hat %s Runden gewonnen", entry.getKey(), entry.getValue()));
        });
        Spieler winner = schiedsrichter.getWinCount().entrySet().stream().max(Comparator.comparing((e) -> e.getValue())).orElse(null).getKey();
        System.out.println(String.format("Spieler '%s' gewonnen!", winner));
    }
}
