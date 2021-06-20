package com.threadend;

import java.util.concurrent.TimeUnit;

public class Suspend {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(()->{
            while (true){
                try {
                    System.out.println("go on");
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
        TimeUnit.SECONDS.sleep(5);
        //暂停
        thread.suspend();
        TimeUnit.SECONDS.sleep(3);
//        继续
        thread.resume();
    }
}
