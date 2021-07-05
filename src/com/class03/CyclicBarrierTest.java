package com.class03;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * CAS
 * 循环爆炸，等满了多少个线程再一起执行，
 * 如某些线程要等其他线程执行完了才能继续执行；
 * 如某个线程要等数据链接线程，文件输出线程执行完了才能执行
 *
 *
 * 一种同步辅助工具，允许一组线程全部等待彼此到达一个共同的障碍点。
 * 它是适用于包含固定大小的线程组的程序,必须偶尔等待对方。cyclic被称为循环，
 * 因为它可以在等待线程之后重用被释放。
 */
public class CyclicBarrierTest {
    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(20,()->{
            System.out.println("20...人满了");
        });
        for (int i = 0; i < 102; i++) {
            new Thread(()->{
                try {
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }).start();
        }

    }
}
