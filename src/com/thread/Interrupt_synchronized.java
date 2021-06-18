package com.thread;

import java.util.concurrent.TimeUnit;

public class Interrupt_synchronized {
    static Object o=new Object();
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(()->{
           synchronized (o){
               try {
                   TimeUnit.SECONDS.sleep(5);
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }
           }
        });
        t1.start();
        TimeUnit.SECONDS.sleep(1);
        Thread t2 = new Thread(()->{
            synchronized (o){
            }
            System.out.println("t2 end");
        });
        t2.start();
        TimeUnit.SECONDS.sleep(1);
        //打断（更改状态）
        t2.interrupt();
    }
}
