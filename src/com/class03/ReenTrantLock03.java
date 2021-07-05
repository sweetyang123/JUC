package com.class03;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 可替代synchronized，是CAS实现，一样的可重入,但需要手动解锁
 * 有trylock，看能否上锁；
 * 调用lockInterruptibly可以对线程interrupt方法做出响应
 * 公平锁
 */
public class ReenTrantLock03 extends Thread{
    //为true时为公平锁，在队列中排队，有时会先输出多个t1，这种可能是t2还没进队列
    Lock lock = new ReentrantLock(true);
    @Override
    public void run() {
        for (int i = 0; i <100 ; i++) {
            try {
                lock.lock();
                System.out.println(Thread.currentThread().getName()+"------start");
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                lock.unlock();
            }
        }
    }
    public static void main(String[] args) {
        ReenTrantLock03 lock03 = new ReenTrantLock03();
        Thread t1=new Thread(lock03);
        Thread t2=new Thread(lock03);
        t1.start();
        t2.start();
    }
}
