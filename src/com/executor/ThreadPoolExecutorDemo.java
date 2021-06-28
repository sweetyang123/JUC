package com.executor;

import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolExecutorDemo {
    public static void main(String[] args) {
        int cps=1;
        int mps=2;
        int r=5;
        int size=r+mps;
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(cps,mps,60,TimeUnit.SECONDS,new
                LinkedBlockingDeque<>(r),Executors.defaultThreadFactory(),new ThreadPoolExecutor.CallerRunsPolicy());
        for (int i = 0; i <size ; i++) {
            int finalI = i;
            threadPoolExecutor.execute(() -> {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("wait"+ finalI);
            });

        }
            threadPoolExecutor.execute(()->{
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
            System.out.println("wait");
    }
}
