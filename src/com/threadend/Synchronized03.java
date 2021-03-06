package com.threadend;
/**
 * 程序在执行过程中，如果出现异常，默认情况锁会被释放
 * 所以，在并发处理的过程中，有异常要多加小心，不然可能会发生不一致的情况。
 * 比如，在一个web app处理过程中，多个servlet线程共同访问同一个资源，这时如果异常处理不合适，
 * 在第一个线程中抛出异常，其他线程就会进入同步代码区，有可能会访问到异常产生时的数据。
 * 因此要非常小心的处理同步业务逻辑中的异常
 */
public class Synchronized03 {
    private synchronized void m(){
        System.out.println("hello "+Thread.currentThread().getName());
        int count=0;
        while (true){
            count++;
            System.out.println(Thread.currentThread().getName()+" count="+count);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (count==5){//此处抛出异常，锁将被释放，要想不被释放，可以在这里进行catch，然后让循环继续
                int a=1/0;
                System.out.println(a);
            }
        }


    }
    public static void main(String[] args) {
        Synchronized03 syn = new Synchronized03();
        Runnable r = new Runnable() {
            @Override
            public void run() {
                syn.m();
            }
        };
       new Thread(r,"t1").start();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(r,"t2").start();


    }
}
