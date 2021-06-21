package com.visibility;

public class Visibility_02 {
   static   class A{
       /*volatile*/ boolean running = true;
        public void m(){
            System.out.println("start");
            while (running){

            }
            System.out.println("end");
        }
    }
    private static volatile A a = new A();
    public static void main(String[] args) {
        new Thread(a::m,"t1").start();
        try {
            Thread.sleep(1000);
            //引用对象里的变量是不可见的
            a.running=false;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
