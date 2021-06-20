package com.threadinterrupt;

import java.util.concurrent.TimeUnit;

public class Interrupt_sleep {
    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(()->{
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                System.out.println("Thread is interrupted");
                //捕捉到InterruptedException时，系统会自动修改标志位，以便于捕捉后面的打断异常
                System.out.println(Thread.interrupted());//false
            }
        });
        t.start();
        TimeUnit.SECONDS.sleep(5);
        //打断（更改状态）
        t.interrupt();
    }
}
