package com.class04;

import java.util.concurrent.locks.LockSupport;

/**
 * 面试：有两个线程，一个输出1到26，另一个线程输出A到Z，
 * 现在要求交替输出A1B2。。。。。
 * CAS实现，第一个线程执行一次后自旋等待，等下一个线程执行完一次后再执行
 */
public class AlternateCas {
   enum RunAndTest {T1,T2};
   static volatile RunAndTest r=RunAndTest.T1;
    public static void main(String[] args) {
        new Thread(()->{
            for(int i=65;i<91;i++)
            {
                //当r为T1时输出，为T2时则死循环一直在这里等待自旋，让给另一个线程运行
                while (r==RunAndTest.T2){}
                System.out.print((char)i);
                r=RunAndTest.T2;
            }
        }).start();
      new Thread(()->{
            for (int i = 1; i <=26 ; i++) {
                while (r==RunAndTest.T1){}
                System.out.print(i);
                r=RunAndTest.T1;
            }
        }).start();
    }
}
