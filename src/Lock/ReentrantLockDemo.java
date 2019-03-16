package Lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ReentrantLock is based on a FIFO queue to get the timed lock,
 * and it has two type of locks(fair or unfair)
 */
public class ReentrantLockDemo {

}

//仓库
class Depot{
    private int capacity;
    private int size; //当前数量
    private Lock lock; //独占锁
    private Condition produceCondition; //生产条件
    private Condition consumeCondition; //消费条件

    public Depot(int capacity) {
        this.capacity = capacity;
        this.size = 0;
        this.lock = new ReentrantLock();
        this.produceCondition = lock.newCondition();
        this.consumeCondition = lock.newCondition();
    }

    public void produce(int val) {
        lock.lock();
        try {
            int leftToProduce = val; //需要生产的数量
            while (leftToProduce > 0) {
                while (size >= capacity) {
                    produceCondition.await();
                }
                int incre = (size + leftToProduce) > capacity?(capacity - size):leftToProduce;
                size += incre;
                leftToProduce -= incre;
                System.out.printf("%s produce(%3d) --> left to produce=%3d, incre=%3d, size=%3d\n",
                        Thread.currentThread().getName(), val, leftToProduce, incre, size);
                consumeCondition.signalAll();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
    public void consume(int val) {
        lock.lock();
        try {
            int leftToConsume = val;
            while (leftToConsume > 0) {
                while (size <= 0){
                    consumeCondition.await();
                }
                int decre = (size < leftToConsume)? size:leftToConsume;
                size -= decre;
                leftToConsume -= decre;
                System.out.printf("%s consume(%3d) <-- left=%3d, dec=%3d, size=%3d\n",
                        Thread.currentThread().getName(), val, leftToConsume, decre, size);
                produceCondition.signalAll();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
    public String toString(){
        return "capacity:"+capacity+", actual size:"+size;
    }

    public static void main(String[] args) {
        Depot depot = new Depot(100);
        Producer producer = new Producer(depot);
        Consumer consumer = new Consumer(depot);
        producer.produce(60);
        producer.produce(100);
        consumer.consume(100);
        consumer.consume(100);
        producer.produce(60);
    }
}

class Producer{
    private Depot depot;

    public Producer(Depot depot){
        this.depot = depot;
    }

    public void produce(int val){
        new Thread(){
            @Override
            public void run() {
                depot.produce(val);
            }
        }.start();
    }
}

class Consumer{
    private Depot depot;

    public Consumer(Depot depot){
        this.depot = depot;
    }

    public void consume(int val){
        new Thread(){
            @Override
            public void run() {
                depot.consume(val);
            }
        }.start();
    }
}
