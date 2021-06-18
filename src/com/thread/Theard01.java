package com.thread;

import java.util.concurrent.*;

public class Theard01 {
   public static class  MyThread extends Thread{
       @Override
       public void run() {
           System.out.println("hello MyThread");
       }
   }
   public static  class  MyRunnable implements Runnable{
       @Override
       public void run() {
           System.out.println("hello MyRunnable");
       }

   }
    public static class MyCallable implements Callable<String>{
        @Override
        public String call() throws Exception {
            System.out.println("hello MyCallable");
            return "success";
        }
    }
    public static void main(String[] args) throws ExecutionException, InterruptedException {
//       1、继承Thread
        new MyThread().start();
//      2、实现Runnable
        new Thread(new MyRunnable()).start();
//      3、lamed方式
        new Thread(()->{
            System.out.println("hello lamda Thread");
        }).start();
//        4、实现Callable
        Thread t = new Thread(new FutureTask<String>(new MyCallable()));
        t.start();
        //5、线程池
        ExecutorService service = Executors.newCachedThreadPool();
        service.execute(()->{
            System.out.println("hello myThreadlocal");
        });
        Future<String> f=service.submit(new MyCallable());
        String s = f.get();
        System.out.println(s);
        service.shutdown();
    }
}

