package com.executor;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Demo1 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService service = Executors.newFixedThreadPool(10);
        ArrayList<Callable<Integer>> list=Lists.newArrayList(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                System.out.println(1);
                return 1;
            }
        },new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
//                System.out.println(2);
                try{
                    Thread.sleep(1000);
                    System.out.println(2);
                }catch (Exception e){
                    e.printStackTrace();
                }
                return 2;
            }
        });
//        service.invokeAny(list);
            Integer in = service.invokeAny(list);
        System.out.println(in);
    }
}
