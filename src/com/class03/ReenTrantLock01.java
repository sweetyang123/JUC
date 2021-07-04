package com.class03;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 可替代synchronized，是CAS实现，一样的可重入,y需要手动解锁
 * 有trylock，看能否上锁；
 */
public class ReenTrantLock01 {
    ReentrantLock lock = new ReentrantLock();

    void m1(){
        try {
            lock.lock();
            for (int i = 0; i <2 ; i++) {
                System.out.println("m1....");
                Thread.sleep(1000);
            }
         }catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
            lock.unlock();
        }

    }

    /**
     * tryLock不管是否锁定，都会执行后面的代码；
     * 可以用于判断是否锁定
     */
    void m2(){
        try {
            boolean tryLock = lock.tryLock(2, TimeUnit.SECONDS);
            System.out.println("m2...."+tryLock);
            Thread.sleep(1000);
        }catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }

    }
    public static void main(String[] args) {
        ReenTrantLock01 lock01 = new ReenTrantLock01();
        new Thread(lock01::m1).start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(lock01::m2).start();
    }
}
