package com.threadend;

import java.util.concurrent.TimeUnit;

public class Thread_Volatile {
    private static volatile boolean running=true;
    public static void main(String[] args) throws InterruptedException {
      Thread t= new Thread(()->{
            long i=0;
            while (running){
                //wait,accpet,阻塞了则不能循环下去，不能去读标志位，也结束不了
                i++;
            }
            System.out.println(i);//3188669794,1768041763
        });
        t.start();
        TimeUnit.SECONDS.sleep(1);
        running=false;
    }
}
