package com.class04;

import java.util.concurrent.locks.LockSupport;

/**
 * 面试：有两个线程，一个输出1到26，另一个线程输出A到Z，
 * 现在要求交替输出A1B2。。。。。
 * LockSupport实现：当一个线程循环完一次后就唤醒另一个线程，并停住
 * 另一个线程先直接停住，被唤醒后输出再唤醒下一个线程
 */
public class AlternateLockSupport {
    static Thread t2;
   static Thread t1 = null;
    public static void main(String[] args) {
        t2 = new Thread(()->{
            for(int i=65;i<91;i++)
            {
                System.out.print((char)i);
                LockSupport.unpark(t1);
                LockSupport.park();
            }
        });
        t1 = new Thread(()->{
            for (int i = 1; i <=26 ; i++) {
                LockSupport.park();
                System.out.print(i);
               LockSupport.unpark(t2);
            }
        });

       t2.start();
       t1.start();
    }
}
