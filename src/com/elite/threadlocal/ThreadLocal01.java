package com.elite.threadlocal;

import java.util.concurrent.TimeUnit;

/**
 * 本地线程的意思，是线程独有的
 */
public class ThreadLocal01 {
    volatile  static Person p = new Person();

    public static void main(String[] args) {
        new Thread(()->{
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(p.name);
        }).start();
       //这个线程修改了对象里边的值，线程1打印的还是lisi
        new Thread(()->{
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            p.name = "lisi";
        }).start();

    }
}
