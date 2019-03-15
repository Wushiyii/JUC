package basic;

public class MyRunnableThread implements Runnable {
    private int totalTickets = 10;

    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            if (totalTickets > 0) {
                System.out.println(Thread.currentThread().getName()+" Sells tickets:" +this.totalTickets--);
            }
        }
    }

    public static void main(String[] args) {
        MyRunnableThread t = new MyRunnableThread();
        Thread t1 = new Thread(t);
        Thread t2 = new Thread(t);
        Thread t3 = new Thread(t);
        t1.start();
        t2.start();
        t3.start();
    }
}
