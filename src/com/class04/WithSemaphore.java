package com.class04;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;

/**
 * 先让t1 获取信号量许可，先运行4次，再释放许可，开始运行t2且join t2
 * t2获取了许可后运行完了再释放，t1再继续运行
 */
public class WithSemaphore {
    List<Object> lists = Collections.synchronizedList(new ArrayList<>());
    private void add(Object value){
        lists.add(value);
    }
    private int size(){
        return lists.size();
    }
   static   Thread t2 = null;
    static Thread t1= null;
    public static void main(String[] args) {
        Semaphore sp = new Semaphore(1);
        WithSemaphore wcdl = new WithSemaphore();
        t1= new Thread(()->{
            System.out.println("t1 start");
            try {
                sp.acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            for (int i = 0; i <4 ; i++) {
                wcdl.add(new Object());
                System.out.println("add"+i);
            }
            sp.release();
            t2.start();
            try {
                t2.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            for (int i = 4; i <10 ; i++) {
                wcdl.add(new Object());
                System.out.println("add"+i);
            }
            System.out.println("t1...end");
        },"t1");

        t2= new Thread(()->{
            System.out.println("t2 start");
            try {
                sp.acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("t2...end");
            sp.release();
        },"t2");
       t1.start();
    }
}
