package com.elite.CAS;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

/**
 *LockSupport总结
 * 1.LockSupport不需要synchronized加锁就可以实现线程的阻塞和唤醒
 * 2.LockSupport.unpark()方法可以先于LockSupport执行，并且线程不会阻塞
 * 3.如果一个线程处于等待状态，则连续调用两次park()方法，就可以使改线程永远无法被唤醒
 */
public class TestLockSupport3 {

    public static void main(String[] args) {
        Thread t = new Thread(()->{
            for(int i = 0 ;i < 10; i ++){
                System.out.println(i);
                if(i == 5){
                    //使用LockSupport的park方法阻塞当前线程t
                    LockSupport.park();
                }
                if( i== 8){
                    //调用LockSupport的park方法阻塞当前线程t
                    LockSupport.park();
                }
                //当前线程t休眠1s
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        //启动当前线程
        t.start();
        //唤醒线程t
        //线程unpark方法优先park方法执行
        LockSupport.unpark(t);
    }
}
