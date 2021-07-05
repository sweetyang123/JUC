package com.class03;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 可替代synchronized，是CAS实现，一样的可重入,但需要手动解锁
 * 有trylock，看能否上锁；
 * 调用lockInterruptibly可以对线程interrupt方法做出响应
 */
public class ReenTrantLock02 {

    public static void main(String[] args) {
        Lock lock = new ReentrantLock();
        Thread t1= new Thread(()->{
            try {
                lock.lock();
                System.out.println("t1 start");
                Thread.sleep(Integer.MAX_VALUE);
                System.out.println("t1 end");
            }catch (InterruptedException e) {
                e.printStackTrace();
                System.out.println("t1 interrupt");
            }finally {
                lock.unlock();
            }

        });
      t1.start();
      Thread t2=new Thread(()->{
            try {
//                lock.lock();
                lock.lockInterruptibly();
                System. out.println("t2 start");
                Thread.sleep(3000);
                System.out.println("t2 end");
            }catch (InterruptedException e) {
                //打断后这里开始运行
                e.printStackTrace();
                System.out.println("t2 interrupt");
            }finally {
                lock.unlock();
            }
        });
      t2.start();
      //过1s再打断
//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        t2.interrupt();
    }
}
