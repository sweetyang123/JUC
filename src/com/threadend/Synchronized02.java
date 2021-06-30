package com.threadend;

/**
 * 面试：
 * 模拟银行，对写的方法加锁；对读的方法不加锁，可行吗？
 * 会出现脏读
 */
public class Synchronized02 {
      int count;

    public /*synchronized*/ int get() {
        return count;
    }

    public synchronized void set(int count) {
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.count = count;
    }


    public static void main(String[] args) {
        Synchronized02 syn = new Synchronized02();
            new Thread(()->{
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                syn.set(100);
            }).start();
        System.out.println(syn.get());
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(syn.get());
    }
}
