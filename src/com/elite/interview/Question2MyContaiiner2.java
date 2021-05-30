package com.elite.interview;

import java.util.LinkedList;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 写一个固定容量同步器，拥有put和get方法以及getcount方法
 * 能够支持2个生产者线程以及10个消费者线程的阻塞调用
 */
public class Question2MyContaiiner2<T> {

    //设置linked列表
    final private LinkedList<T> lists = new LinkedList<>();
    //设置最大的容量
    final private int MAX = 10;//最多10个元素
    private int count = 0 ;
    //锁
    private Lock lock =new ReentrantLock();
    private Condition producer = lock.newCondition();
    private Condition consumer = lock.newCondition();

    //生产者
    public synchronized void put(T t){
        try {
            lock.lock();
            while(lists.size() ==MAX){//想想为什么用while而不是if
                producer.await();
            }
            lists.add(t);
            ++count;
            //通知消费者线程进行消费
            consumer.signalAll();
        } catch (InterruptedException e) {
                e.printStackTrace();
         }finally {
            lock.unlock();
        }
    }
    //消费者
    public  synchronized T get(){
        T t = null;
        try {
             lock.lock();
             while (lists.size() == 0){
                 consumer.await();
             }
             //获取第一个
             t = lists.removeFirst();
             count --;
             //通知生产者进行生产
             producer.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
         return t;

    }

    public static void main(String[] args) {
        Question2MyContaiiner2<String> c = new Question2MyContaiiner2<>();

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
