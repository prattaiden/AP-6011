import javax.swing.plaf.TableHeaderUI;
import java.util.ArrayList;

public class classexamplethread {

  public static void main(String[] args) {

    myRunnable runnable = new myRunnable();
    Thread myThread = new Thread(runnable);

    //array list
//    ArrayList<Thread> myThreads = new ArrayList<>();
//    myThreads.add(new Thread(runnable));

    //making it executable
//    myThread.start();
    //prints out thread 21 ++
    //because there are a bunch of other threads that are already running like main()

    for (int i = 0; i < 5; i++){
      Thread t = new Thread (runnable);
      t.start();
      //runs them in different order than expected
    }
  }
}
