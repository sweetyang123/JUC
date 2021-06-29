package com.executor;


import java.util.concurrent.*;

/**
 * 背压 CallerRunsPolicy
 */

public class ThreadPoolExecutorDemo03 {
    public static void main(String[] args){

        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(10, 10, 0, TimeUnit.MILLISECONDS, new
                LinkedBlockingDeque<>(10), new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                Thread thread = new Thread(r);
                return thread;
            }
        }, new RejectedExecutionHandler() {
            @Override
            public void rejectedExecution(Runnable r, ThreadPoolExecutor e) {
                if (!e.isShutdown()) {
                    r.run();
                    System.out.println("线程坚持不住了！！！不要再往里面放东西了");
                }
            }
        });
        //线程最大容量为10个，任务队列容量也为10个，一共只能容纳20个
        for (int i = 0; i <21 ; i++) {
            threadPoolExecutor.submit(()->{
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }

    }
}
