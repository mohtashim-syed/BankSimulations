// BankResources.java

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.Semaphore;

public class BankResources {
    public static final Semaphore bankOpen = new Semaphore(0);
    public static final Semaphore door = new Semaphore(2);
    public static final Semaphore manager = new Semaphore(1);
    public static final Semaphore safe = new Semaphore(2);
    public static final BlockingQueue<Customer> customerQueue = new LinkedBlockingQueue<>();
}
