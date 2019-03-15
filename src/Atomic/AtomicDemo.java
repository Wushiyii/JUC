package Atomic;

import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicLongArray;
import java.util.concurrent.atomic.AtomicReference;

public class AtomicDemo {
    public static void main(String[] args) {
        System.out.println("=====AtomicLong=====");

        AtomicLong a = new AtomicLong();
        a.set(0x0123456789ABCDEFL);
        System.out.println(a.get());


        System.out.println("=====AtomicLongArray=====");
        AtomicLongArray atomicArray = new AtomicLongArray(10);
        atomicArray.set(1,1);
        atomicArray.set(2,2);
        atomicArray.set(3,3);
        atomicArray.set(4,4);
        System.out.println(atomicArray);

        long[] arrLong = new long[] {10, 20, 30, 40};
        atomicArray = new AtomicLongArray(arrLong);
        System.out.println(atomicArray);

        System.out.println("=====AtomicLongArray=====");

        Node node1 = new Node(1);
        Node node2 = new Node(2);
        AtomicReference<Node> atomicNode1 = new AtomicReference<>(node1);
        atomicNode1.compareAndSet(node1,node2);
        Node node = atomicNode1.get();
        System.out.println(node);



    }

    static class Node{
        private int val;

        public Node(int val) {
            this.val = val;
        }

        @Override
        public String toString() {
            return"val :"+val;
        }
    }
}
