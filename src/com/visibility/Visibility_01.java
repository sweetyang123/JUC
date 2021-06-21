package com.visibility;

import java.util.concurrent.TimeUnit;

/**
 * 1、未加volatile时，线程之间是不可见的，running是不一致的
 * 2、在循环里加了System.out.println变为可见的，是因为该方法里加了synchronized
 */
public class Visibility_01 {
    private static /*volatile*/ boolean running=true;
    private static void  m(){
        while (running){
            System.out.println("go on");
        }
        System.out.println("t1 end");
    }
    public static void main(String[] args) {
        new Thread(Visibility_01::m,"t1").start();
        try {
            TimeUnit.SECONDS.sleep(1);
            running=false;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
