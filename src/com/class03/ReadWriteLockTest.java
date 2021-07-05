package com.class03;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 读写锁，读锁共享锁，写锁排他锁
 *读锁共享，多个线程同一时间读取，效率快
 * ReentrantLock读的时候按线程一个一个读，较慢
 */
public class ReadWriteLockTest {
    static int value=0;
    static ReentrantLock lock = new ReentrantLock();
    static ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    static Lock readLock = readWriteLock.readLock();
    static Lock writeLock = readWriteLock.writeLock();


    public static void main(String[] args) {
        Runnable readR=()->read(readLock);
//        Runnable readR=()->read(lock);
        Runnable writeR=()->write(writeLock,new Random().nextInt());
//        Runnable writeR=()->write(lock,new Random().nextInt());
        for (int i = 0; i <4 ; i++) {
            new Thread(readR).start();
        }
        for (int i = 0; i <2 ; i++) {
            new Thread(writeR).start();
        }
        for (int i = 0; i <4 ; i++) {
            new Thread(readR).start();
        }
    }

    private static void read(Lock lock) {
        try {
            lock.lock();
            System.out.println("read over---"+value);
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    private static void write(Lock lock,int v) {
        try {
            lock.lock();
            Thread.sleep(1000);
            value=v;
            System.out.println("write over---"+value);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
}
