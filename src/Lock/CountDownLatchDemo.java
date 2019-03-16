package Lock;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchDemo {
    private static int SIZE = 5;
    private static CountDownLatch latch;

    public static void main(String[] args) {
        try {
            latch = new CountDownLatch(SIZE);
            for (int i = 0; i < SIZE; i++) {
                new InnerThread().start();
            }

            System.out.println("Main wait begin");
            latch.await();

            System.out.println("Main wait end");

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    static class InnerThread extends Thread{
        @Override
        public void run() {
            try {
                Thread.sleep(1000);
                System.out.println(Thread.currentThread().getName() + " sleep 1000ms.");
                latch.countDown();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
