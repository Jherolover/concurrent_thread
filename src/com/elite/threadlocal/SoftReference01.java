package com.elite.threadlocal;

import java.lang.ref.SoftReference;

/**
 * 当一个对象被一个软引用指向的时候，只有系统内存不够用的时候，才会回收它
 */
public class SoftReference01 {
    public static void main(String[] args) {
        SoftReference<byte[]> m = new SoftReference<>(new byte[1024*1024*10]);
        System.out.println(m.get());
        System.gc();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(m.get());
        //再分配一个数组  heap堆内存装不下，这时候系统会来及回收
        byte[] b = new byte[1024*1024*15];
        System.out.println(m.get());
    }
}
