package com.class08;

import java.io.IOException;
import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.*;

public class ForkJoinPool01 {
    static int[] num=new int[1000000];
    static long size=50000;
    static Random r = new Random();
    static {
        for (int i = 0; i <num.length ; i++) {
            num[i]=r.nextInt(100);
        }
        long l = System.currentTimeMillis();
        System.out.println(Arrays.stream(num).sum());
        System.out.println("直接循环增加------"+(System.currentTimeMillis()-l));
    }
    public static void main(String[] args) throws IOException{
        ForkJoinPool fjp = new ForkJoinPool();
        MyTask myTask = new MyTask(0, num.length);
        long l = System.currentTimeMillis();
        fjp.execute(myTask);
        System.out.println("fork----"+(System.currentTimeMillis()-l));
        //可以获取返回值的，join
        MyTask01 myTask01 = new MyTask01(0, num.length);
        long l1 = System.currentTimeMillis();
        fjp.execute(myTask01);
        System.out.println("fork01----"+(System.currentTimeMillis()-l1)+"-------"+myTask01.join());
        System.in.read();
    }
    static class MyTask01 extends RecursiveTask<Long>{
        int start,end;

        public MyTask01(int start, int end) {
            this.start = start;
            this.end = end;
        }
        @Override
        protected Long compute() {
            if (end-start<=size){
                long sum=0l;
                for (int i = start; i < end; i++) {
                    sum+=num[i];
                }
                return sum;
            }else {
                int middle=start+(end-start)/2;
                MyTask01 myTask01 = new MyTask01(start, middle);
                MyTask01 myTask02 = new MyTask01(middle, end);
                myTask01.fork();
                myTask02.fork();
                return myTask01.join()+myTask02.join();
            }
        }
    }
    static class MyTask extends RecursiveAction{
        int start,end;

        public MyTask(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        protected void compute() {
            if (end-start<=size){
                    long sum=0l;
                for (int i = start; i < end; i++) {
                        sum+=num[i];
                }
//                System.out.println("start:"+start+"----end:"+end+"=====sum:"+sum);
            }else {
                int middle=start+(end-start)/2;
                MyTask myTask01 = new MyTask(start, middle);
                MyTask myTask02 = new MyTask(middle, end);
                myTask01.fork();
                myTask02.fork();
            }
        }
    }
}
