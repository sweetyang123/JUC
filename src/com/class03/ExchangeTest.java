package com.class03;

import java.util.concurrent.Exchanger;

/**
 * 交换锁，线程间通信的一种，多用于遗传算法
 * 交换变量的值
 */
public class ExchangeTest {
    static Exchanger<String> exchanger = new Exchanger<>();

    public static void main(String[] args) {
        new Thread(()->{
            String s = "T1";
            try {
               s= exchanger.exchange(s);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+"   "+s);
        },"t1").start();
        new Thread(()->{
            String s = "T2";
            try {
                s = exchanger.exchange(s);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+"   "+s);
        },"t2").start();    
    }

}
