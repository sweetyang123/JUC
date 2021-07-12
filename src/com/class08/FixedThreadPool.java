package com.class08;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * 任务量较平稳时可用，固定线程数的线程池
 * 但阿里都不用，要求自己去估算，进行精确定义
 */
public class FixedThreadPool {
    public static void main(String[] args) {
//        核心线程数和最大线程数都为nThreads
        ExecutorService service = Executors.newFixedThreadPool(4);
        MyTask t1 = new MyTask(1, 80000);
        MyTask t2 = new MyTask(80001, 130000);
        MyTask t3 = new MyTask(130001, 170000);
        MyTask t4 = new MyTask(170001, 200000);
        Future<List<Integer>> F1 = service.submit(t1);
        Future<List<Integer>> F2 = service.submit(t2);
        Future<List<Integer>> F3 = service.submit(t3);
        Future<List<Integer>> F4 = service.submit(t4);
        long start=System.currentTimeMillis();
        try {
            F1.get();
            F2.get();
            F3.get();
            F4.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println(System.currentTimeMillis()-start);
    }
    static class MyTask implements Callable<List<Integer>>{
        int startPos, endPos;

        MyTask(int s, int e) {
            this.startPos = s;
            this.endPos = e;
        }

        @Override
        public List<Integer> call() throws Exception {
            List<Integer> r = getPrime(startPos, endPos);
            return r;
        }

    }

    static boolean isPrime(int num) {
        for(int i=2; i<=num/2; i++) {
            if(num % i == 0) return false;
        }
        return true;
    }

    static List<Integer> getPrime(int start, int end) {
        List<Integer> results = new ArrayList<>();
        for(int i=start; i<=end; i++) {
            if(isPrime(i)) results.add(i);
        }

        return results;
    }
}

