package com.elite.CAS;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * renntranlock 3强大的地方在于，你可以使用tryLock进行尝试锁定
 *  不管锁定与否，方法都将继续执行，synchronized 如果搞不定的话他肯定阻塞了，
 *  但是reentrantLock你自己决定你到底要不要wait
 */
public class ReentranLock3 {
    ReentrantLock lock = new ReentrantLock();
    void m1(){
        //
        try {
            lock.lock();
            for (int i = 0 ; i < 3; i++){
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(i);
            }
        }finally {
            lock.unlock();
        }
    }
    /**
     * 使用tryLock 进行尝试锁定，不管锁定与否，方法都将继续执行
     * 也可以根据tryLock 的返回值判断是否锁定
     * 也可以指定tryLock的时间
     */
    void m2(){
        boolean locked = lock.tryLock();
        System.out.println("m2..."+locked);
        if(locked) lock.unlock();

        /*boolean locked = false;
        try{
            try {
                locked = lock.tryLock(5,TimeUnit.SECONDS);
                System.out.println("m2..."+locked);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }finally {
            if(locked)
            lock.unlock();
        }*/

    }

    public static void main(String[] args) {
        ReentranLock3 r = new ReentranLock3();
        new Thread(r::m1).start();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(r::m2).start();
    }
}
