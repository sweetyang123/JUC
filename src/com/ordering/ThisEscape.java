package com.ordering;

/**
 * this对象逸出
 */

import java.io.IOException;

/**
 * 汇编码
 * 0 new #2 <java/lang/Object> 占用内存，半初始化状态，m为默认的0
 * 3 dup
 * 4 invokespecial #1 <java/lang/Object.<init>> 调用构造方法完成初始化
 * 7 astore_1  创建关联,这里是与this创建关联
 * 8 return
 */
public class ThisEscape {
   private int m=8;
    public ThisEscape() {
        new Thread(()->{System.out.println(this.m);
        }).start();
    }
    public static void main(String[] args) throws IOException {
        //有可能出现m=0，当invokespecial，astore_1指令换序时
        new ThisEscape();
        System.in.read();
    }
}
