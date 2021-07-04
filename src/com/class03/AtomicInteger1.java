package com.class03;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.LongAdder;

/**
 *AtomicInteger CAS实现
 *synchronized 高并发时，效率较低
 *LongAdder CAS实现，分段锁， 在数据量达到一定量时（高并发），效率可能更快
 */
public class AtomicInteger1 {
    static AtomicInteger count1=new AtomicInteger(0);
    static long count2=0;
    static LongAdder count3=new LongAdder();
    static long testtime=100000;
    public static void main(String[] args) throws InterruptedException {
        Thread[] threads = new Thread[1000];
        for (int i = 0; i < threads.length; i++) {
            threads[i]=new Thread(()->{
                for (int j = 0; j < testtime; j++) {
                    count1.incrementAndGet();
                }
            });
        }
        long start = System.currentTimeMillis();
        for (Thread thread:threads)thread.start();
        for (Thread thread:threads)thread.join();
        System.out.println(count1.get()+"-----atomic-----"+(System.currentTimeMillis()-start));
       Object o = new Object();
        for (int i = 0; i < threads.length; i++) {
            threads[i]=new Thread(()->{
                for (int j = 0; j < testtime; j++) {
                        synchronized (o){
                        count2++;
                    }
                }
            });
        }
        start = System.currentTimeMillis();
        for (Thread thread:threads)thread.start();
        for (Thread thread:threads)thread.join();
        System.out.println(count2+"-----syn-----"+(System.currentTimeMillis()-start));


        for (int i = 0; i < threads.length; i++) {
            threads[i]=new Thread(()->{
                    for (int j = 0; j < testtime; j++) {
                        count3.increment();
                }
            });
        }
        start = System.currentTimeMillis();
        for (Thread thread:threads)thread.start();
        for (Thread thread:threads)thread.join();
        System.out.println(count3.longValue()+"-----LongAdder-----"+(System.currentTimeMillis()-start));

    }
}
