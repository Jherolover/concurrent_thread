package com.elite.interview;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

/**
 * question1:
 * 1.实现一个容器，提供两个方法，add,size ，写两个线程
 * 线程1：添加10个元素到容器中
 * 线程2：实施监控元素个数，当个数5个时，线程2提示并结束
 */
public class Question1WithLockSupport {

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
        Question1WithLockSupport q = new Question1WithLockSupport();
        //需要先启动t2再启动t1
        CountDownLatch latch = new CountDownLatch(1);

        //线程2
       t2 =  new Thread(()->{
            System.out.println("t2启动");
            if (q.size() != 5) {
                LockSupport.park();

            }
            System.out.println("t2 结束");
            LockSupport.unpark(t1);
        },"t2");

        TimeUnit.SECONDS.sleep(1);
        //线程1
        t1 = new Thread(()->{

            for (int i = 0; i < 10; i++) {
                q.add(new Object());
                System.out.println("add=" + i);
                if (q.size() == 5) {
                    LockSupport.unpark(t2);
                   LockSupport.park();
                }
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        },"t1");
        //需要注意先启动t2再启动t1
        t2.start();
        t1.start();

    }
}
