package com.class03;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 可替代synchronized，是CAS实现，一样的可重入,y需要手动解锁
 * 有trylock，看能否上锁；
 * 调用lockInterruptibly可以对线程interrupt方法做出响应
 */
public class ReenTrantLock02 {

    public static void main(String[] args) {
        Lock lock = new ReentrantLock();
      Thread t1= new Thread(()->{
            try {
                System.out.println("t1 start");
                lock.lock();
                Thread.sleep(Integer.MAX_VALUE);
                System.out.println("t1 end");
            }catch (InterruptedException e) {
                e.printStackTrace();
                System.out.println("interrupt");
            }finally {
                lock.unlock();
            }

        });
      t1.start();

      Thread t2=new Thread(()->{
            try {
                System.out.println("t2 start");
                lock.lockInterruptibly();
                Thread.sleep(1000);
                System.out.println("t2 end");
            }catch (InterruptedException e) {
                e.printStackTrace();
                System.out.println("interrupt");
            }finally {
                lock.unlock();
            }
        });
      t2.start();
      //过1s再打断
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t2.interrupt();
    }
}
