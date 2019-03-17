package Lock;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class SemaphoreDemo {
    private static int PERMITS_SIZE = 8;

    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(PERMITS_SIZE);
        ExecutorService threadPool = Executors.newFixedThreadPool(3);

        threadPool.execute(new InnerThread(semaphore,5));
        threadPool.execute(new InnerThread(semaphore,3));
        threadPool.execute(new InnerThread(semaphore,7));

        threadPool.shutdown();
    }

    static class InnerThread extends Thread{

        private volatile Semaphore semaphore;
        private int count;

        InnerThread(Semaphore semaphore, int count) {
            this.semaphore = semaphore;
            this.count = count;
        }

        @Override
        public void run() {
            try {
                //获取count个permit
                semaphore.acquire(count);
                Thread.sleep(2000);
                System.out.println(Thread.currentThread().getName() + " acquires " + "count =" +count);

            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                semaphore.release(count);
                System.out.println(Thread.currentThread().getName() + " releases ");
            }
        }
    }
}
