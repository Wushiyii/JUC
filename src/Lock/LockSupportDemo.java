package Lock;

import java.util.concurrent.locks.LockSupport;

public class LockSupportDemo extends Thread{
    public LockSupportDemo(String name) {
        super(name);
    }
    private static Thread cur;

    @Override
    public void run() {
        synchronized (this) {
            System.out.println(Thread.currentThread().getName() + " wake up other threads");
            LockSupport.unpark(cur);
        }
    }

    public static void main(String[] args) {
        LockSupportDemo A = new LockSupportDemo("A");
        cur = Thread.currentThread();

        System.out.println(Thread.currentThread().getName()+" start A");
        A.start();

        System.out.println(Thread.currentThread().getName()+" wait A");
        LockSupport.park(cur);

        System.out.println(Thread.currentThread().getName()+" continue A");

    }
}
