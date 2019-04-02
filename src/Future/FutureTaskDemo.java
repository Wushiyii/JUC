package Future;

import java.util.concurrent.*;

public class FutureTaskDemo {
    public static void main(String[] args) throws InterruptedException, ExecutionException, TimeoutException {

        //方式1
        ExecutorService executorService = Executors.newCachedThreadPool();
        Task task = new Task();
        FutureTask<Integer> futureTask = new FutureTask<>(task);
        try {
            executorService.submit(futureTask);
        }catch (RejectedExecutionException e) {
            //进入线程池失败(1.获取线程资源失败 2.由于等待队列已满，无法进入队列)
            e.printStackTrace();
        }finally {
            executorService.shutdown();
        }

        //方式2
//        Task task = new Task();
//        FutureTask<Integer> futureTask = new FutureTask<>(task);
//        Thread thread = new Thread(futureTask);
//        thread.start();

        Thread.sleep(1000);

        System.out.println("主线程正在执行任务...");
        System.out.println("Task 执行结果 : " + futureTask.get(5,TimeUnit.SECONDS));//如果超时，将会抛出异常
        System.out.println("所有任务执行完毕");
    }
}
