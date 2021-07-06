package com.class04;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.locks.LockSupport;

/**
 *
 */
public class WithLockSupport {
    List<Object> lists = Collections.synchronizedList(new ArrayList<>());
    private void add(Object value){
        lists.add(value);
    }
    private int size(){
        return lists.size();
    }
    static Thread t1 = null, t2 = null;
    public static void main(String[] args) {
        WithLockSupport wcdl = new WithLockSupport();
       t2= new Thread(()->{
            System.out.println("t2 start");
            //阻塞t2
            LockSupport.park();
            System.out.println("t2...end");
            //唤醒t1
            LockSupport.unpark(t1);
        },"t2");
       t2.start();
        t1 =new Thread(()->{
            System.out.println("t1 start");
                for (int i = 0; i <10 ; i++) {
                    wcdl.add(new Object());
                    if (wcdl.size()==5) {
                        //唤醒t2,并阻塞
                        LockSupport.unpark(t2);
                        LockSupport.park();
                    }
                System.out.println("add"+i);
                }
            System.out.println("t1...end");
        },"t1");
        t1.start();
    }
}
