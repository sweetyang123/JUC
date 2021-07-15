package com.class08;

import java.text.DecimalFormat;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 定时线程池（quartz，cron）
 * 可以给定延迟时间，或者定时时间，是DelayedWorkQueue
 * 面试：假如提供一个闹钟服务，订阅这个服务的人特别多，10亿人，如何优化(首先是分服务器的，再是用线程池)
 */
public class ScheduleThreadPool {
    public static void main(String[] args) {
        ScheduledExecutorService service = Executors.newScheduledThreadPool(4);
        service.scheduleAtFixedRate(()->{
            try {
                TimeUnit.MILLISECONDS.sleep(new Random().nextInt(1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName());
        },0,5000,TimeUnit.MILLISECONDS);
    }
}
