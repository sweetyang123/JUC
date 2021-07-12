package com.class04;



/**
 * 面试：有两个线程，一个输出1到26，另一个线程输出A到Z，
 * 现在要求交替输出A1B2。。。。。
 * synchronized实现
 * 两个线程都在输出后调用notify，wait
 */
public class AlternateSynchronized {
    static Thread t2;
   static Thread t1 = null;
    public static void main(String[] args) {
        Object o = new Object();
        t2 = new Thread(()->{
            for(int i=65;i<91;i++)
            {
                synchronized (o){
                    System.out.print((char)i);
                    o.notify();
                    try {
                        o.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    o.notify();//最后必须唤醒，不然线程不会停
                }
            }
        });
        t1 = new Thread(()->{
            for (int i = 1; i <=26 ; i++) {
               synchronized (o){
                   System.out.print(i);
                   o.notify();
                   try {
                       o.wait();
                   } catch (InterruptedException e) {
                       e.printStackTrace();
                   }
                   o.notify();//最后必须唤醒，不然线程不会停
               }

            }
        });

       t2.start();
       t1.start();
    }
}
