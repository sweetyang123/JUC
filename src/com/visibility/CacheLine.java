package com.visibility;

import sun.misc.Contended;

import java.util.concurrent.CountDownLatch;

public class CacheLine {
    private static long count=10_0000_0000L;
    private static class T{
        //增加无用变量是为了填充64个字节，提高效率
//        private long p1,p2,p3,p4,p5,p6,p7;
        //需在run的VM options中加参数：-XX:-RestrictContended；只有1.8支持
        @Contended
        public long x=0L;
//        private long p8,p9,p10,p11,p12,p13,p14;
    }
    public static T[] arr=new T[2];
    static {
        arr[0]=new T();
        arr[1]=new T();
    }

    public static void main(String[] args) throws Exception {
        CountDownLatch downLatch = new CountDownLatch(2);
        Thread t1= new Thread(()->{
            for (long i = 0; i <count ; i++) {
                arr[0].x=i;
            }
            downLatch.countDown();
        });
        Thread t2= new Thread(()->{
            for (long i = 0; i <count ; i++) {
                arr[1].x=i;
            }
            downLatch.countDown();
        });
       final long t= System.nanoTime();
        t1.start();
        t2.start();
        downLatch.await();
        System.out.println((System.nanoTime()-t)/100_0000);
    }
}
