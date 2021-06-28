package com.executor;

import java.util.concurrent.*;

public class ThreadPoolExecutorDemo02 {
    public static void main(String[] args) throws InterruptedException {

        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(1, 1, 0, TimeUnit.MILLISECONDS, new
                LinkedBlockingDeque<>(), new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                Thread thread = new Thread(r);
                //
                thread.setDaemon(true);
                return thread;
            }
        });
        threadPoolExecutor.submit(()->{
            System.out.println("hello");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("exit");
        });
        threadPoolExecutor.shutdown();
        threadPoolExecutor.awaitTermination(1,TimeUnit.MINUTES);
    }
}
