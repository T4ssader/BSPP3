package de.hawhamburg.bsp3.a1;

public class Student extends Thread{
    private static int EAT_WAIT_TIME_MAX = 1000;
    private static int COME_BACK_WAIT_TIME_MAX = 3000;

    private String studentName;
    private Paydesks paydesks;

    public Student(String studentName, Paydesks paydesks) {
        this.studentName = studentName;
        this.paydesks = paydesks;
    }

    public String getStudentName() {
        return studentName;
    }

    //we override run in class Thread
    @Override
    public void run() {
        while (true && !interrupted()) {
            try {
                //get paydesk with shortest line
                Paydesk paydeskWithShortestLine = paydesks.getPaydeskWithShortestLine();

                //pay
                paydeskWithShortestLine.pay();


                //wait eat time
                Thread.sleep(Math.round(Math.random() * EAT_WAIT_TIME_MAX));

                //wait come back time
                Thread.sleep(Math.round(Math.random() * COME_BACK_WAIT_TIME_MAX));

            } catch(InterruptedException e){
                //end thread if sleep is interrupted
                break;
            }
        }
    }

}
