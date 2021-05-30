package com.elite.threadlocal;

/**
 * java的引用方式：
 * 1.强引用
 * 垃圾回收的时候会调用finalize方法
 * 只用一个应用指向这个对象，那么垃圾回收器一定不会回收它
 *
 */
public class StrongReference {

    @Override
    protected void finalize(){
        System.out.println("finalize");
    }
}
