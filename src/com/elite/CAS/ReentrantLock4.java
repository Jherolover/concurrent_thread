package com.elite.CAS;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ReentrantLock还可以用lock.lockInterruptibly 这个类，对interrupt方法做出
 * 相应，可以被打断的线程，如果这种加锁的话，我们可以用调用一个t2.interrupt打断线程2的等待。
 * 线程1上来之后进行加锁，加锁之后开始进行睡眠，睡得没完没了的，被线程1拿到这把锁的话，线程2如果说
 * 再想拿到这把锁不太可能，拿不到锁就在原地等待，想要停止线程2则可以用interrupt(),这就是ReentrantLock比
 * synchronized好用的一个地方
 */
public class ReentrantLock4 {

    public static void main(String[] args) {
        Lock lock = new ReentrantLock();

        Thread t1  = new Thread(()->{
            try {
                lock.lock();
                System.out.println("t1...start");
                TimeUnit.SECONDS.sleep(Integer.MAX_VALUE);
            } catch (InterruptedException e) {
                System.out.println("interrupted!");
            } finally {
                lock.unlock();
            }
        });
        t1.start();

        //线程2
        Thread t2 = new Thread(()->{
            try {
                //lock.lock();
                lock.lockInterruptibly();
                System.out.println("t2...start");
                try {
                    TimeUnit.SECONDS.sleep(5);
                    System.out.println("t2...end");
                } catch (InterruptedException e) {
                    System.out.println("interrupted!");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        });
        t2.start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //打断线程2的等待
        t2.interrupt();
    }
}
