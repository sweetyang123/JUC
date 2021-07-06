package com.class04;

import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 面试题：写一个固定容量同步容器，拥有put和get方法，以及getCount方法，
 * 能够支持2个生产者线程以及10个消费者线程的阻塞调用
 *
 * synchronized 加wait、notifyAll、notify，
 * 但由于notifyAll唤醒的是等待队列里面所有的线程，有可能是生产者，也有可能是消费者
 *
 * ReentrantLock的condition（等待队列的个数，ReentrantLock可以有多个，
 * 唤醒时这里就可以将消费者队列与生产者队列区分开）实现
 */
public class MyContainer01<T> {
    int MAX=10;
    int count=0;
    LinkedList<T> list = new LinkedList();
    Lock lock = new ReentrantLock();
    Condition produce = lock.newCondition();
    Condition consure = lock.newCondition();
   private void put(T value){
       try {
            lock.lock();
           //如果容器满了，则等待消费者消费
           while (list.size()==MAX) {
                   produce.await();
           }
           //没有满则直接生产，加入数据，并唤醒消费者消费
           list.add(value);
           count++;
           consure.signalAll();
        } catch (InterruptedException e) {
           e.printStackTrace();
       }finally {
           lock.unlock();
       }
   }
    private T get(){
        T t =null;
        try {
            lock.lock();
           //如果容器里的没有数据了，则等待生产者生产
           if (list.size()==0) {
                   consure.await();
           }
           //否则直接消费，移除表头的数据，并唤醒生产者
           t =list.removeFirst();
           count--;
          produce.signalAll();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
        return t;
    }
    private int getCount(){
        return count;
    }
    public static void main(String[] args) {
        MyContainer01 mc = new MyContainer01();
        for (int i = 0; i <2 ; i++) {
            new Thread(()->{
                for (int j = 0; j < 10; j++) {
                    mc.put(Thread.currentThread().getName()+"  "+j);
//                    System.out.println(mc.getCount());
                }
            },"p"+i).start();
        }
        for (int i = 0; i <10 ; i++) {
            new Thread(()->{
                for (int j = 0; j < 20; j++) {
                    System.out.println(mc.get());
                }
            },"c"+i).start();
        }
    }
}
