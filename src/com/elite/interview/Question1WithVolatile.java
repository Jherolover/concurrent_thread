package com.elite.interview;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * question1:
 * 1.实现一个容器，提供两个方法，add,size ，写两个线程
 * 线程1：添加10个元素到容器中
 * 线程2：实施监控元素个数，当个数5个时，线程2提示并结束
 */
public class Question1WithVolatile {

    volatile  List list  = new ArrayList();

    public  void add(Object o){
        list.add(o);
    }
    public int size(){
        return list.size();
    }

    public static void main(String[] args) {
        Question1WithVolatile q = new Question1WithVolatile();
        //线程1
        new Thread(()->{
            for(int i = 0 ;i <10 ; i++){
                q.add(new Object());
                System.out.println("add="+i);
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"t1").start();

        //线程2
        new Thread(()->{
            while(true){
                if(q.size() == 5){
                    break;
                }
            }
            //
            System.out.println("t2 结束");
        },"t2").start();
    }
}
