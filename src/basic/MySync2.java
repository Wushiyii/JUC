package basic;

public class MySync2 extends Thread {

    public MySync2(String name){
        super(name);
    }
    @Override
    public void run() {
        synchronized(this) {
            try {
                for (int i = 0; i < 5; i++) {
                    Thread.sleep(100);
                    System.out.println(Thread.currentThread().getName() + " loop : " + i +" times");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        /**
         * 创建多个Thread对象，会使synchronized锁住每一个对象，无法实现同步
         */
        Thread t1 = new MySync2("t1");
        Thread t2 = new MySync2("t2");
        t1.start();
        t2.start();
    }
}
