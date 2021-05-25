package com.elite;

import jdk.internal.org.objectweb.asm.tree.TryCatchBlockNode;

import java.beans.Transient;
import java.util.concurrent.TimeUnit;

/**
 * 程序在执行过程中 如果出现异常，默认锁会被释放
 * 因此在并发处理的过程中，有异常需要多加小心进行处理，不然可能导致数据不一致的情况
 * 比如：在一个web app处理过程中，多个servlet 线程共同访问同一个资源，这是如果异常处理不合适
 * 在第一个线程跑出异常，其他线程就会进入同步代码块，有可能产生异常的数据。
 * 因此需要 小心处理同步业务逻辑中的异常
 */
public class ThreadException {

    int count = 0;

    synchronized  void m(){
        System.out.println(Thread.currentThread().getName()+"start");
        while(true){
            count ++;
            System.out.println(Thread.currentThread().getName()+"count="+count);
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //抛出异常  锁将被释放  要想不被释放 可以在这里进行catch 然后让循环继续
            if (count == 5 ){
                try {
                    int i = 1/0;
                    System.out.println(i);
                }catch(Exception e){
                    System.out.println(e.getMessage());
                    continue;
                }
            }
        }
    }

    public static void main(String[] args) {
        ThreadException t = new ThreadException();
        Runnable r = new Runnable() {
            @Override
            public void run() {
                t.m();
            }
        };
        new Thread(r,"t1").start();
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(r,"t2").start();
    }
}