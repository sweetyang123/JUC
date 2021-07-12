package com.class07;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

public class CompletableFutureTest {
    public static void main(String[] args) {
        CompletableFuture<Double> futureTM = CompletableFuture.supplyAsync(()->priceOfTM());
        CompletableFuture<Double> futureTB = CompletableFuture.supplyAsync(()->priceOfTB());
        CompletableFuture.allOf(futureTM,futureTB);
        CompletableFuture.supplyAsync(()->priceOfTB())
                .thenApply(String::valueOf)
                .thenApply(str-> "price " + str)
                .thenAccept(System.out::println);
//        Callable
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private static double priceOfTM() {
        delay();
        return 1.00;
    }
    private static double priceOfTB() {
        delay();
        return 2.00;
    }
    private static void delay() {
        int time = new Random().nextInt(500);
        try {
            TimeUnit.MILLISECONDS.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.printf("After %s sleep!\n", time);
    }
}
