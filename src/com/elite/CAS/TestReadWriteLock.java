package com.elite.CAS;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 读写锁
 */
public class TestReadWriteLock {

    static Lock lock = new ReentrantLock();

    private static  int value;

    static ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    //读锁
    static Lock readLock = readWriteLock.readLock();
    //写锁
    static Lock writeLock = readWriteLock.writeLock();
    //读操作
    public static  void read(Lock lock){
        try {
                lock.lock();
                Thread.sleep(1000);
                 System.out.println("read over");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
            lock.unlock();
        }
    }
    //模拟写操作
    public static void write(Lock lock){
        try {
            lock.lock();
            Thread.sleep(1000);
            System.out.println("write over");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
    //主方法入口
    public static void main(String[] args) {
        Runnable readR = () -> read(readLock);

        for(int i = 0 ; i < 18; i++){ new Thread(readR).start();}
    }
}
