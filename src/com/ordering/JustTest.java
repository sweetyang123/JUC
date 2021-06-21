package com.ordering;

/**
 * 汇编码
 * 0 new #2 <java/lang/Object> 占用内存，半初始化状态
 * 3 dup
 * 4 invokespecial #1 <java/lang/Object.<init>> 调用构造方法完成初始化
 * 7 astore_1  创建关联，将o与上面创建的内存关联上
 * 8 return
 */
public class JustTest {
    public static void main(String[] args) {
        Object o = new Object();
    }
}
