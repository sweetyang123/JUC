package com.threadinterrupt;

import java.util.concurrent.TimeUnit;
//同步代码块synchronized中interrupt也不能抢到执行权，interrupt只是修改标志位

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
