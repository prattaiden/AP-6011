import java.lang.reflect.Array;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws InterruptedException {
   // sayHello();
    badSum();
    }
    static int answer = 0;
    static final int i = 0;

    public static void sayHello() throws InterruptedException {
        myRunnable runnable = new myRunnable();

        ArrayList<Thread> threadsA = new ArrayList<>();
        for (int i = 0; i < 11; i++){
            Thread t = new Thread(runnable);
            threadsA.add(t);
            t.start();
            t.join();


        }
    }
    public static void badSum() throws InterruptedException {
    int maxValue = 100;

    int correctAnswer=(maxValue * (maxValue - 1) / 2);

    MathRunnable runnable ;

//    ArrayList<Thread> myThread = new ArrayList<>(6);
        Thread[] myThread = new Thread[6] ;
    for (int i = 0; i < myThread.length; i++) {
        runnable = new MathRunnable( i,maxValue, myThread.length );

        myThread[i] = new Thread(runnable);

        myThread[i].start();

        myThread[i].join();
        answer = (int) runnable.answer;

    }
        System.out.println("My answer:" + answer + " Correct answer:" + correctAnswer);

    }

}

//Q1. What happens? Do all the threads run in order?
    //threads do not run in order
    //println adds a new line and prints it down. print prints it left to right

//Q2. Run your program a couple of times - does the same thread always print the 1st hello? The last hello?
    //1st run, first hello is from thread #21, last hello is from thread #39
    //2nd run, first hello is from thread #21, last hello is from thread #39
    //3rd run, first hello is from thread #21, last hello is from thread #39

//Q3.  This is because the threads are running concurrently and accessing the shared variable answer at
// the same time. Since there is no synchronization mechanism in place, the order in which the threads access
// answer is non-deterministic, leading to different results each time.