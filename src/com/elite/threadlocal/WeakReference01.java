package com.elite.threadlocal;

import java.lang.ref.WeakReference;

/**
 * 弱引用
 */
public class WeakReference01 {
    public static void main(String[] args) {
        WeakReference<StrongReference> m = new WeakReference<>(new StrongReference());
        System.out.println(m.get());
        System.gc();
        System.out.println(m.get());
        ThreadLocal<StrongReference> t1 = new ThreadLocal<>();
        t1.set(new StrongReference());
        t1.remove();
    }
}
