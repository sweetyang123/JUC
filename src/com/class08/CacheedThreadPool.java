package com.class08;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 一般不会使用，线程少，任务不会堆积时,任务量忽高忽低时可以用
 */
public class CacheedThreadPool {
    public static void main(String[] args) throws InterruptedException {
//        等待队列是SynchroniousQueue,只有一个，容易堆积
        ExecutorService service = Executors.newCachedThreadPool();
        for (int i = 0; i < 2; i++) {
            service.execute(()->{
                try {
                    TimeUnit.MILLISECONDS.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName());
            });
        }
        System.out.println(service);
        TimeUnit.SECONDS.sleep(4);
        System.out.println(service);
    }
}
