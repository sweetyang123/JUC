package com.class04;

import java.util.*;
/**
 * 面试题：写一个固定容量同步容器，拥有put和get方法，以及getCount方法，
 * 能够支持2个生产者线程以及10个消费者线程的阻塞调用
 *
 * synchronized 加wait、notifyAll、notify，
 * 但由于notifyAll唤醒的是等待队列里面所有的线程，有可能是生产者，也有可能是消费者
 */
public class MyContainer<T> {
    int MAX=10;
    int count=0;
    LinkedList<T> list = new LinkedList();
   private synchronized void put(T value){
       //如果容器满了，则等待消费者消费
       while (list.size()==MAX) {
           try {
               this.wait();
           } catch (InterruptedException e) {
               e.printStackTrace();
           }
       }
       //没有满则直接生产，加入数据，并唤醒其他线程 notifyAll（包含所有生产，消费的线程）
       list.add(value);
       count++;
       this.notifyAll();
   }
    private synchronized T get(){
       //如果容器里的没有数据了，则等待生产者生产
       if (list.size()==0) {
           try {
               this.wait();
           } catch (InterruptedException e) {
               e.printStackTrace();
           }
       }
       //否则直接消费，移除表头的数据，并唤醒其他线程
       T t =list.removeFirst();
       count--;
       this.notifyAll();
       return t;
    }
    private int getCount(){
        return count;
    }
    public static void main(String[] args) {
        MyContainer mc = new MyContainer();
        for (int i = 0; i <2 ; i++) {
            new Thread(()->{
                for (int j = 0; j < 5; j++) {
                    mc.put(Thread.currentThread().getName()+"  "+j);
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
