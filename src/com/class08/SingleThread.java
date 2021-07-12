package com.class08;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SingleThread {
    public static void main(String[] args) {
//        只生成一个线程的线程池？线程的生命周期可交给线程池管理
        ExecutorService service = Executors.newSingleThreadExecutor();
        for (int i = 0; i < 5; i++) {
            int j=i;
            service.execute(()->{
                System.out.println(j+" "+Thread.currentThread().getName());
            });
//            new Thread(()->{System.out.println(j+" "+Thread.currentThread().getName());}).start();
        }

    }
}
