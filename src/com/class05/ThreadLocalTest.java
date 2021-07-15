package com.class05;

import java.lang.ref.WeakReference;
import java.util.concurrent.CopyOnWriteArrayList;

public class ThreadLocalTest {
    public static void main(String[] args) {
        person p = new person();
        ThreadLocal tl= new ThreadLocal();
        Thread t1 = new Thread(()->{
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(tl.get());
        });
        Thread t2 = new Thread(()->{
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            p.name="lisi";
            tl.set(p);
        });
        t1.start();
        t2.start();
//        WeakReference wr = new WeakReference(p);
    }
    static class person{
         String name;

        public person() {
            this.name = "zhangsan";
        }
    }
}
