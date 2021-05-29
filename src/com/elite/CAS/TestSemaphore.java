package com.elite.CAS;

import java.util.concurrent.Semaphore;

/**
 * reentrntLock,CountDownLatch,CyclicBarrier,Phaser,ReadWriteLock,Semaphore
 * Exchanger都是用同一个队列，同一个类来实现 这个类叫AQS
 */
public class TestSemaphore {
    public static void main(String[] args) {
        //第一个参数允许几个线程同时执行，第二个参数是否公平
        Semaphore s = new Semaphore(1,true);
        new Thread(()->{
            try{
                s.acquire();
                System.out.println("T1 running ...");
                Thread.sleep(200);
                System.out.println("T1 running...");
                s.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        //lambda表达式实现线程
        new Thread(()->{
           try{
                   s.acquire();
                   System.out.println("T2 running ...");
                   Thread.sleep(200);
                   System.out.println("T2 running...");
                   s.release();
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }
        }).start();
    }

}
