package com.elite.interview;

import com.sun.corba.se.spi.ior.ObjectKey;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * question1:
 * 1.实现一个容器，提供两个方法，add,size ，写两个线程
 * 线程1：添加10个元素到容器中
 * 线程2：实施监控元素个数，当个数5个时，线程2提示并结束
 */
public class Question1NotifyHoldingLock {
    //使用volatile，使得t2能够得到通知
    volatile  List list  = new ArrayList();

    public  void add(Object o){
        list.add(o);
    }
    public int size(){
        return list.size();
    }

    public static void main(String[] args) throws InterruptedException {
        Question1NotifyHoldingLock q = new Question1NotifyHoldingLock();
        //
        final Object lock = new Object();
        //需要先启动t2再启动t1
        //线程2
        new Thread(()->{
            synchronized (lock) {
                System.out.println("t2启动");
                    if (q.size() != 5) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

            }
            System.out.println("t2 结束");
        },"t2").start();

        TimeUnit.SECONDS.sleep(1);
        //线程1
        new Thread(()->{
            synchronized (lock) {
                for (int i = 0; i < 10; i++) {
                    q.add(new Object());
                    System.out.println("add=" + i);
                    if (q.size() == 5) {
                        lock.notify();
                    }
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        },"t1").start();


    }
}
