package com.elite.interview;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * question1:
 * 1.实现一个容器，提供两个方法，add,size ，写两个线程
 * 线程1：添加10个元素到容器中
 * 线程2：实施监控元素个数，当个数5个时，线程2提示并结束
 */
public class Question1WithCountDownLatch {
    //使用volatile，使得t2能够得到通知
    volatile  List list  = new ArrayList();

    public  void add(Object o){
        list.add(o);
    }
    public int size(){
        return list.size();
    }

    public static void main(String[] args) throws InterruptedException {
        Question1WithCountDownLatch q = new Question1WithCountDownLatch();
        //需要先启动t2再启动t1
        CountDownLatch latch = new CountDownLatch(1);

        //线程2
        new Thread(()->{
            System.out.println("t2启动");
            if (q.size() != 5) {
                try {
                    latch.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("t2 结束");
        },"t2").start();

        TimeUnit.SECONDS.sleep(1);
        //线程1
        new Thread(()->{

            for (int i = 0; i < 10; i++) {
                q.add(new Object());
                System.out.println("add=" + i);
                if (q.size() == 5) {
                    //暂停t1线程  线程数减去1停止运行
                    latch.countDown();
                }
                /*try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }*/
            }

        },"t1").start();


    }
}
