package com.threadinterrupt;

import java.util.concurrent.TimeUnit;

public class Thread_Interrupt {
    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(()->{
            for (;;){
                //是否打断，（查询状态）
                if (Thread.currentThread().isInterrupted()){
                    System.out.println("Thread is interrupted");
                    System.out.println(Thread.currentThread().isInterrupted());
                }
            }
        });
        t.start();
        TimeUnit.SECONDS.sleep(2);
        //打断（更改状态）
        t.interrupt();
    }
}
