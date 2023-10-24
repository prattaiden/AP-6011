public class myRunnable implements Runnable{

  @Override
  public void run() {
    for (int count = 0; count < 101; count++) {
//      System.out.println("Hello number: " + count + " From: " + Thread.currentThread().threadId());
    }

  }
}

