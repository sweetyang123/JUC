package com.class04;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedTransferQueue;
import java.util.concurrent.TransferQueue;

/**
 * 面试：有两个线程，一个输出1到26，另一个线程输出A到Z，
 * 现在要求交替输出A1B2。。。。。
 * BlockingQueue实现
 */
public class AlternateTransferQueue {

    public static void main(String[] args) {
        TransferQueue tq = new LinkedTransferQueue();
        new Thread(()->{
            for(int i = 1; i <=26 ;i++)
            {
                try {
                    System.out.print(tq.take());
                    //传送一个元素，如果有消费者已经在等待了，则要传输完成，交到手上才能结束
                    tq.transfer(i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
      new Thread(()->{
            for (int i=65;i<91; i++) {
                try {
                    tq.transfer((char)i);
                    System.out.print(tq.take());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }


            }
        }).start();
    }
}
