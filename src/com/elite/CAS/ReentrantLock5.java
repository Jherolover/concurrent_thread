package com.elite.CAS;

import java.util.concurrent.locks.ReentrantLock;

/**
 * reentrantLock 还可以指定为公平锁
 *
 */
public class ReentrantLock5 extends  Thread{

    //true表示公平锁
    private static ReentrantLock lock = new ReentrantLock(false);

    public void run(){
        for(int i = 0 ; i <20; i++){
            lock.lock();
            try {
                System.out.println(Thread.currentThread().getName()+"获得锁");
            }finally {
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) {
        ReentrantLock5 r = new ReentrantLock5();
        Thread th1 = new Thread(r);
        Thread th2 = new Thread(r);
        th1.start();
        th2.start();
    }
}
