package com.class04;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 保证t2先执行，当t2只有wait,t1只有notify时，会先全部输出t1后再输出t2
 * 所以需要两对wait，notify
 * wait会释放锁，notify不会释放锁
 * notify之后，t1必须释放锁，t2退出后，也必须notify，通知t1继续执行
 * 整个通信过程比较繁琐
 */
public class WaitNotifyTest {
     List<Object> lists = Collections.synchronizedList(new ArrayList<>());
    private void add(Object value){
        lists.add(value);
    }
    private int size(){
        return lists.size();
    }

    public static void main(String[] args) {
        Object lock=new Object();
        WaitNotifyTest wn=new WaitNotifyTest();
        new Thread(()->{
            System.out.println("t2 start");
            synchronized (lock){
//                if (wn.size()!=5){
                //一来直接将t2阻塞住，等待被唤醒
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
//                }
                System.out.println("t2...end");
                //t2运行完了再唤醒t1
                lock.notify();
            }
        },"t2").start();
        new Thread(()->{
            System.out.println("t1 start");
            synchronized (lock){
                for (int i = 0; i <10 ; i++) {
                    wn.add(new Object());
                    if (wn.size()==5) {
//                        容器为5时唤醒t2运行，
                        lock.notify();
                        try {
//                           并阻塞t1，释放锁
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.println("add"+i);
                }
            }
            System.out.println("t1...end");
        },"t1").start();

    }
}
