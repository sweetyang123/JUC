package com.executor;


import java.util.concurrent.*;

/**
 * 背压 CallerRunsPolicy
 */

public class ThreadPoolExecutorDemo04 {
    static int a=0;
    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i <100 ; i++) {
            new Thread(()->{a++;}).start();
        }
        Thread.sleep(1000);
        System.out.println(a);
    }
}
