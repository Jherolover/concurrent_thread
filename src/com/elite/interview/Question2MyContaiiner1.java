package com.elite.interview;

import java.sql.SQLOutput;
import java.util.LinkedList;
import java.util.concurrent.TimeUnit;

/**
 * 写一个固定容量同步器，拥有put和get方法以及getcount方法
 * 能够支持2个生产者线程以及10个消费者线程的阻塞调用
 */
public class Question2MyContaiiner1<T> {

    //设置linked列表
    final private LinkedList<T> lists = new LinkedList<>();
    //设置最大的容量
    final private int MAX = 10;//最多10个元素
    private int count = 0 ;
    //生产者
    public synchronized void put(T t){
        while(lists.size() ==MAX){//想想为什么用while而不是if
            try {
                this.wait();//effective java
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        lists.add(t);
        ++count;
        //唤醒其他线程
        this.notifyAll();
    }
    //消费者
    public  synchronized T get(){
        T t = null;
         while (lists.size() == 0){
             try {
                 this.wait();//阻塞线程
             } catch (InterruptedException e) {
                 e.printStackTrace();
             }
         }
         //获取第一个
         t = lists.removeFirst();
         count --;
         //通知生产者线程进行生产
         this.notifyAll();
         return t;

    }

    public static void main(String[] args) {
        Question2MyContaiiner1<String> c = new Question2MyContaiiner1<>();

        //启动消费者线程
        for(int i = 0 ; i < 10; i++){
            new Thread(()->{
                for(int j = 0 ; j < 5; j++) {
                    System.out.println(c.get());
                }
            },"c"+i).start();
        }
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //启动生产者线程
        for(int i = 0 ; i <2 ; i ++){
            new Thread(()->{
                for(int j = 0 ; j < 25;j++)
                    c.put(Thread.currentThread().getName()+""+j);
            },"p"+i).start();
        }
    }
}
