package com.ordering;
import java.io.IOException;
/**
 * this对象逸出
 * 最好不要在构造方法里start线程
 */
public class ThisEscape_02 {
   private int m=8;
   private Thread t;
    public ThisEscape_02() {
        t=new Thread(()->{System.out.println(this.m);
        });
    }
    private void start(){
        t.start();
    }
    public static void main(String[] args) throws IOException {
        new ThisEscape_02().start();
        System.in.read();
    }
}
