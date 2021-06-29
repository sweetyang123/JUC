package com.executor;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class ThreadPoolExecutorDemo02 {
    public static void main(String[] args) throws InterruptedException {
        System.out.println(getUserList());
    }
    static List getUserList() throws InterruptedException {
        ArrayList userList = new ArrayList();
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(1, 1, 0, TimeUnit.MILLISECONDS, new
                LinkedBlockingDeque<>(), new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                Thread thread = new Thread(r);
                thread.setDaemon(true);
                return thread;
            }
        });
        threadPoolExecutor.submit(()->{
            System.out.println("hello");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            userList.add(1);
            System.out.println("exit");
        });
//        加入shutdown时userList里才有值
        threadPoolExecutor.shutdown();
        threadPoolExecutor.awaitTermination(1,TimeUnit.MINUTES);
        return userList;
    }
}
