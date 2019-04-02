package Future;

import java.util.concurrent.*;

public class CallableDemo {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        Task task = new Task();
        Future<Integer> future = executorService.submit(task);
        executorService.shutdown();

        Thread.sleep(1000);

        System.out.println("主线程正在执行任务 ... ");

        System.out.println("task执行结果 : " + future.get());
        System.out.println("任务执行完毕");
    }
}

class Task implements Callable<Integer>{

    @Override
    public Integer call() throws Exception {
        System.out.println("子线程正在计算中...");
        Thread.sleep(2000);
        int sum = 0;
        for (int i = 0; i < 100; i++) {
            sum += i;
        }
        return sum;
    }
}