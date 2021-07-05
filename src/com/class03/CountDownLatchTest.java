package com.class03;

import java.util.concurrent.CountDownLatch;

/**
 * 倒数门栓，如有100个线程，门栓栓住后，
 * 要这100个线程执行完了才把门栓打开，释放,之后的线程再进入执行
 *
 * 允许一个或多个线程等待直到其他线程中执行的一组操作完成的同步操作
 */
public class CountDownLatchTest {
    public static void main(String[] args) {
        useCountDownLatch();
//        useJoin();
    }

    private static void useJoin() {
        Thread[] threads = new Thread[12];
        for (int i = 0; i <threads.length ; i++) {
            threads[i]=new Thread(()->{
                int count = 0;
                for (int j = 0; j <100000 ; j++) {
                    count++;
                }
//                System.out.println(Thread.currentThread().getName()+"----start");
            });
        }
        for (Thread t:threads) t.start();
        for (int i = 0; i <threads.length ; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("join end....");
    }

    private static void useCountDownLatch() {
        Thread[] threads = new Thread[12];
        CountDownLatch latch = new CountDownLatch(10);
        for (int i = 0; i <threads.length ; i++) {
            threads[i]=new Thread(()->{
                int count = 0;
                for (int j = 0; j <100000 ; j++) {
                    count++;
                }
                latch.countDown();
                System.out.println(latch.getCount()+"----start");

            });
        }
        for (Thread t:threads) t.start();
        try {
                    latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("latch end....");
    }
}
