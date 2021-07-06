package com.class04;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 曾经的面试题：（淘宝？）
 *   实现一个容器，提供两个方法，add，size
 *   写两个线程，线程1添加10个元素到容器中，线程2实现监控元素的个数，当个数到5个时，线程2给出提示并结束
 *
 *   给lists添加volatile之后，t2能够接到通知，但是，t2线程的死循环很浪费cpu，如果不用死循环，
 *   而且，如果在if 和 break之间被别的线程打断，得到的结果也不精确
 */
public class WithVolatile {
   static volatile List<Object> lists = Collections.synchronizedList(new ArrayList<>(10));
    private void add(Object value){
        lists.add(value);
    }
    private int size(){
        return lists.size();
    }
    public static void main(String[] args) {
        WithVolatile wn=new WithVolatile();
        Thread t1= new Thread(()->{
            for (int i = 0; i <10 ; i++) {
                wn.add(new Object());
                System.out.println("add"+i);
            }
            System.out.println("t1...end");
        },"t1");
        Thread t2= new Thread(()->{
            while (true){
                if (wn.size()==5)break;
            }
            System.out.println("t2...end");
        },"t2");
        t1.start();
        t2.start();
    }
}
