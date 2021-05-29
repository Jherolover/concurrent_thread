package com.elite.CAS;

import java.util.concurrent.Exchanger;

/**
 * 交换器  exchanger 可以交换两个线程之间的数值
 */
public class TestExchanger {
    static Exchanger<String> exchanger = new Exchanger<>();

    public static void main(String[] args) {
        new Thread(()->{
           String s= "T1";
            try {
                s = exchanger.exchange(s);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+"交换后的值"+s);
        },"t1").start();
        new Thread(()->{
            String s= "T2";
            try {
                s = exchanger.exchange(s);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+"交换后的值"+s);
        },"t2").start();
    }
}
