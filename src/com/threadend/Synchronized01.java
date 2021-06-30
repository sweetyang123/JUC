package com.threadend;

/**
 * 可重入锁：被锁方法再调用加锁方法
 */
public class Synchronized01 {
    int count=0;
    synchronized void m(){
        System.out.println("hello m");

        try {
            Thread.sleep(2000);
            count++;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        m1();
        System.out.println(count);
    }
    synchronized  void m1(){
        count--;
        System.out.println("hello m1");
    }
    public static void main(String[] args) {
        new Synchronized01().m();
    }
}
