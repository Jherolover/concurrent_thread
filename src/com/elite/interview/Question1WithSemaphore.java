package com.elite.interview;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

/**
 * question1:
 * 1.实现一个容器，提供两个方法，add,size ，写两个线程
 * 线程1：添加10个元素到容器中
 * 线程2：实施监控元素个数，当个数5个时，线程2提示并结束
 */
public class Question1WithSemaphore {

    //静态的线程
    static  Thread t1 = null, t2 = null;
    //使用volatile，使得t2能够得到通知
    volatile  List list  = new ArrayList();

    public  void add(Object o){
        list.add(o);
    }
    public int size(){
        return list.size();
    }

    public static void main(String[] args) throws InterruptedException {
        Question1WithSemaphore q = new Question1WithSemaphore();
        //需要先启动t2再启动t1
        //只允许一个线程执行
        Semaphore s = new Semaphore(1);

        //线程2
       t2 =  new Thread(()->{
           try {
               s.acquire();
               System.out.println("t2运行结束");
               s.release();
           } catch (InterruptedException e) {
               e.printStackTrace();
           }

       },"t2");

        TimeUnit.SECONDS.sleep(1);
        //线程1
        t1 = new Thread(()->{
                try {
                    s.acquire();
                    for (int i = 0; i < 5; i++) {
                        q.add(new Object());
                        System.out.println("add=" + i);
                    }
                    s.release();
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                try {
                    t2.start();
                    t2.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            try {
                s.acquire();
                for (int i = 5; i < 10; i++) {
                    q.add(new Object());
                    System.out.println("add=" + i);
                }
                s.release();
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        },"t1");

        t1.start();

    }
}
