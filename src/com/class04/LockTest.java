package com.class04;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class LockTest {
//    List<Object> lists = Collections.synchronizedList(new ArrayList<>(10));
  Stack stack = new Stack();
   private void put(int value){
       stack.push(value);
   }
    private Object get(){
       return stack.pop();
    }
    private int getCount(){
        return stack.size();
    }
 static ReadWriteLock lock = new ReentrantReadWriteLock();
    Lock readLock = lock.readLock();
    Lock writeLock = lock.writeLock();
    public static void main(String[] args) {

        for (int i = 0; i <2 ; i++) {
            new Thread(()->{

            });
        }
    }
}
