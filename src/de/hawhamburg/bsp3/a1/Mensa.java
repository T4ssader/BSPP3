package de.hawhamburg.bsp3.a1;

import java.util.ArrayList;
import java.util.List;

public class Mensa {
    public static final int PAYDESK_COUNT = 3;  //Amount Paydesks
    public static final int STUDENT_COUNT = 10;  //Amount Students
    public static final int PROGRAM_EXECUTION_TIME =10000;  //Execution Time for Program Length

    public static void main(String[] args) throws InterruptedException {

        //init Paydesks and Students
        Paydesks paydesks = new Paydesks();
        List<Student> students = new ArrayList<>();

        //add Paydesk_Count amounts of paydesks into paydesks
        for(int i = 0; i< PAYDESK_COUNT; i++){
            paydesks.addPaydesk(new Paydesk(String.format("Paydesk %s", i)));
        }

        //add Student_Count amount of Students into students
        for(int i = 0; i< STUDENT_COUNT; i++){
            students.add(new Student(String.format("Student %s", i), paydesks));
        }

        //start student threads
        for(Student student : students){
            student.start();
        }

        //wait for program length (in milliseconds)
        Thread.sleep(PROGRAM_EXECUTION_TIME);

        //interrupt student threads
        for(Student student : students){
            student.interrupt();
        }

        //wait for all threads finished
        for(Student student : students){
            student.join();
        }


        //print out paydesks counter
        paydesks.getPaydesks().stream().forEach((p) -> {
            System.out.println(String.format("%s payments: %s", p.getName(), p.getCounter()));
        });

    }

}
