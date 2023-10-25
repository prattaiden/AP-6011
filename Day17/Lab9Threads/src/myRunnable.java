
public class myRunnable implements Runnable {
    @Override
    public void run() {
        for (int count = 0; count < 20; count++) {
            System.out.print("Hello number " + count + " Hello from thread # " + Thread.currentThread().threadId());
        }
            for(int j = 10; j<20; j++){
            System.out.println("Hello number "+j+" Hello from thread # " + Thread.currentThread().threadId());
        }
    }
    //gets from runnable class, interface
    //wrapper class
}

