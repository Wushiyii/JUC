package basic;

public class MyThread extends Thread {
    private int totalTickets = 10;

    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            if (totalTickets > 0) {
                System.out.println(this.getName()+" Sells tickets:" +this.totalTickets--);
            }
        }
    }

    public static void main(String[] args) {
        MyThread t1 = new MyThread();
        MyThread t2 = new MyThread();
        MyThread t3 = new MyThread();
        t1.start();
        t2.start();
        t3.start();
    }
}
