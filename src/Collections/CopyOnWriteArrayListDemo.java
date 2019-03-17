package Collections;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class CopyOnWriteArrayListDemo {

    private static List<String> list = new CopyOnWriteArrayList<>();

    public static void main(String[] args) {
        new InnerThread("A").start();
        new InnerThread("B").start();
    }

    static class InnerThread extends Thread {
        InnerThread(String name) {
            super(name);
        }

        @Override
        public void run() {
            int i = 0;
            while (i++ < 6) {
                String name = Thread.currentThread().getName() + "-" + i;
                list.add(name);
                printAll();
            }
        }

        private static void printAll() {
            String value = null;
            Iterator iter = list.iterator();
            while (iter.hasNext()) {
                value = (String) iter.next();
                System.out.print(value + ", ");
            }
            System.out.println();
        }
    }
}
