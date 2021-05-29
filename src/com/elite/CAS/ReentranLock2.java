package com.elite.CAS;

import jdk.internal.org.objectweb.asm.tree.TryCatchBlockNode;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * reentrantLock 用于替代 synchronized
 * 本例中由于m1锁定this,只有m1执行完毕的时候,m2才能执行
 * 这是复习synchronized的意义
 *
 * reentranlock 是可以代替synchronized的，怎么替代呢，看如下代码，原来写synchronized的地方写lock.lock(),
 * 加完锁之后要注意的记得lock.unlock() 解锁，由于synchronized是自动解锁的，
 * 大括号执行完就结束了，lock需要手动释放锁，释放锁放try ...finally里保证一定要解锁，
 * 不然的话上锁之后中间执行的过程有问题了，死在那了，别人就永远也拿不到这把锁了。
 *
 */
public class ReentranLock2 {
    Lock lock = new ReentrantLock();
    /*synchronized*/  void m1(){
        try {
            lock.lock();//synchronized(this)
            for(int i = 0 ; i <10;i++){
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(i);
                if(i == 2){m2();};
            }
        }finally {
            lock.unlock();
        }

    }
    /*synchronized*/ void m2(){
        try{
            lock.lock();
            System.out.println("m2.....");
        }finally {
            lock.unlock();
        }

}

    public static void main(String[] args) {
        ReentranLock2 r1 = new ReentranLock2();
        new Thread(r1::m1).start();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(r1::m2).start();
    }

}
