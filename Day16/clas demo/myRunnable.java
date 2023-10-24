public class myRunnable implements Runnable{


  @Override
  public void run() {
    for (int count = 0; count < 20; count++) {
      System.out.println("Hello from thread #" + Thread.currentThread().threadId() + ":" + count);
    }
  }
}
