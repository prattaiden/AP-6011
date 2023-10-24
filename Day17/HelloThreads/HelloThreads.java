import java.util.ArrayList;

public class HelloThreads {

  static int answer;
public static void main(String[] args) throws InterruptedException {

//sayHello();
  badSum();

}


  public static void sayHello() throws InterruptedException {
    myRunnable runnable = new myRunnable();

    ArrayList<Thread> threadsA = new ArrayList<>();
    for (int i = 0; i < 11; i++){
      Thread t = new Thread(runnable);
      threadsA.add(t);
      t.start();
      t.run();
      t.join();
      //threads do not run in order after the first part
      //println adds a new line and prints it down. print prints it left to right

    }
  }

  public static void badSum() throws InterruptedException {
  myRunnable runnable = new myRunnable();
  answer = 0;
  int maxValue = 40000;
  int min;
  int counter = 0;

  //ARRAYLIST and for loop to add threads to the array
  ArrayList<Thread> TArray = new ArrayList<>();
  for(int i = 0; i < 10; i ++) {
    Thread t = new Thread(runnable);
    TArray.add(t);
//    System.out.println("Thread id " + t.threadId());
    int numThreads = TArray.size();

//    answer += i*maxValue/numThreads;
  }
  //for each loop
  for(Thread t : TArray){
    //initializes the thread
    t.start();

//    counter++;
    //starts the run method


    t.run();
      for (int i = counter*40000/10; i <= Math.min((counter+1)*40000/10, 40000); i++){
//        System.out.println();
        answer += i;
      }
    counter++;
    //wait state
    t.join();
  }

  System.out.println("Answer is " + answer + " Expected " + (maxValue * (maxValue - 1) / 2));
  }

  //check if value in answer is equal (maxValue * (maxValue - 1) / 2)








}
