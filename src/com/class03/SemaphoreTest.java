package com.class03;

import java.util.concurrent.Semaphore;

/**
 * 信号量，限流，acquire拿到许可
 */
public class SemaphoreTest {
    public static void main(String[] args) {
        //可允许permits的线程同步执行
        Semaphore semaphore = new Semaphore(10,true);
        //允许一个线程同时执行
//        Semaphore semaphore = new Semaphore(1);
//        for (int i = 0; i < 10; i++) {
//            new Thread(()->{
//                try {
//                    semaphore.acquire();
//                    System.out.println(Thread.currentThread().getName()+".....running");
//                    Thread.sleep(200);
//                    System.out.println(Thread.currentThread().getName()+"t1.....===end");
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }finally {
//                    //信号量没有调用release释放的话，当信号量满了就一直阻塞着，并不可重复使用
//                    semaphore.release();
//                }
//            }).start();
//        }
        new Thread(()->{
            try {
                semaphore.acquire();
                System.out.println("t1.....running");
                Thread.sleep(2000);
                System.out.println("t1.....===running");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                //信号量没有调用release释放的话，当信号量满了就一直阻塞着，并不可重复使用
                semaphore.release();
            }
        }).start();
        new Thread(()->{
            try {
                semaphore.acquire();
                System.out.println("t2.....running");
                Thread.sleep(2000);
                System.out.println("t2.....===running");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                semaphore.release();
            }
        }).start();
    }
}
