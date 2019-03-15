package basic;

public class StartOrRun extends Thread{

    public StartOrRun(String name) {
        super(name);
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName()+" is running");
    }

    public static void main(String[] args) {
        Thread t = new StartOrRun("myThread");
        System.out.println(Thread.currentThread().getName()+" call StartOrRun.run()");
        t.run();
        System.out.println(Thread.currentThread().getName()+" call StartOrRun.start()");
        t.start();
    }
}
