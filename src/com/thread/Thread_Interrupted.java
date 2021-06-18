package com.thread;

import java.util.concurrent.TimeUnit;

public class Thread_Interrupted {
    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(()->{
            for (;;){
                //返回是否打断并且修改值
                if (Thread.interrupted()){
                    System.out.println("Thread is interrupted");
                    System.out.println(Thread.interrupted());
                }
            }
        });
        t.start();
        TimeUnit.SECONDS.sleep(2);
        //打断（更改状态）
        t.interrupt();
    }
}
