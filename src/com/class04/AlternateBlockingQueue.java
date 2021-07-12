package com.class04;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * 面试：有两个线程，一个输出1到26，另一个线程输出A到Z，
 * 现在要求交替输出A1B2。。。。。
 * BlockingQueue实现
 */
public class AlternateBlockingQueue {

    public static void main(String[] args) {
        BlockingDeque pro = new LinkedBlockingDeque(1);
        BlockingDeque com = new LinkedBlockingDeque(1);
        new Thread(()->{
            for(int i=65;i<91;i++)
            {
                System.out.print((char)i);
                try {
                    pro.put("ok");
                    com.take();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
      new Thread(()->{
            for (int i = 1; i <=26 ; i++) {
                try {
                    pro.take();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.print(i);
                try {
                    com.put("ok");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }).start();
    }
}
