package com.threadinterrupt;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;
/**
 * 加锁lock中interrupt也不能抢到执行权，interrupt只是修改标志位,
 * 如果非要打断interrupt的话
 * 则可以用ReentrantLock的lockInterruptibly方法。
 */
public class Interrupt_lock {
    private static ReentrantLock lock=new ReentrantLock();
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(()->{
            lock.lock();
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
            System.out.println("t1 end....");
        });
        t1.start();
        TimeUnit.SECONDS.sleep(1);
        Thread t2 = new Thread(()->{
            System.out.println("t2 start....");
            try {
                lock.lockInterruptibly();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
            System.out.println("t2 end...");
        });
        t2.start();
        TimeUnit.SECONDS.sleep(1);
        //打断（更改状态）
        t2.interrupt();
    }
}
