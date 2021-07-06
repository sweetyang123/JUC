package com.class04;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * 实现一个容器，提供两个方法，add，size
 * 写两个线程，线程1添加10个元素到容器中，线程2实现监控元素的个数，当个数到5个时，线程2给出提示并结束
 *  双重CountDownLatch实现
 *
 * 使用Latch（门闩）替代wait notify来进行通知
 * 好处是通信方式简单，同时也可以指定等待时间
 * 使用await和countdown方法替代wait和notify
 * CountDownLatch不涉及锁定，当count的值为零时当前线程继续运行
 * 当不涉及同步，只是涉及线程通信的时候，用synchronized + wait/notify就显得太重了
 * 这时应该考虑countdownlatch/cyclicbarrier/semaphore
 */
public class WithCountDownLatch {
    List<Object> lists = Collections.synchronizedList(new ArrayList<>());
    private void add(Object value){
        lists.add(value);
    }
    private int size(){
        return lists.size();
    }

    public static void main(String[] args) {
        CountDownLatch cdl = new CountDownLatch(1);
        CountDownLatch cdl2 = new CountDownLatch(1);
        WithCountDownLatch wcdl = new WithCountDownLatch();
        new Thread(()->{
            System.out.println("t2 start");
            try {
                //一来直接将t2阻塞住，将门栓1关住
                cdl.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

                System.out.println("t2...end");
                //t2运行完了再将门栓2打开
            cdl2.countDown();

        },"t2").start();
        new Thread(()->{
            System.out.println("t1 start");

                for (int i = 0; i <10 ; i++) {
                    wcdl.add(new Object());
                    if (wcdl.size()==5) {
//                        容器为5时唤醒t2运行，将门栓1打开
                       cdl.countDown();
                        try {
//                            并关上门栓2，等待t2运行完
                            cdl2.await();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                System.out.println("add"+i);
                }
            System.out.println("t1...end");
        },"t1").start();
    }
}
