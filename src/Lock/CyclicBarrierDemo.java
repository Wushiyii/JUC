package Lock;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierDemo {
    private static int SIZE =5;
    private static CyclicBarrier cyclicBarrier;

    static class InnerThread extends Thread{
        @Override
        public void run() {
            try {
                System.out.println(Thread.currentThread().getName() + " wait for CyclicBarrier");

                cyclicBarrier.await();

                System.out.println(Thread.currentThread().getName() + " continued");
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        cyclicBarrier = new CyclicBarrier(SIZE, () -> System.out.println("CyclicBarrier's parties is: "+ cyclicBarrier.getParties()));
        for (int i = 0; i < SIZE; i++) {
            new InnerThread().start();
        }
    }
}
