package com.elite.CAS;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

/**
 * lockSupport是一个比较底层的工具类
 * 用来创建锁和其他同步工具类的基本线程阻塞原语。
 * java锁和同步器框架的核心AQS：
 * AbstractQueuedSynchronizer，就是通过调研LockSupport.park()和
 * LockSupport.unpark()的方法来实现阻塞和唤醒的。
 */
public class TestLockSupport1 {

    public static void main(String[] args) {
        Thread t = new Thread(()->{
            for(int i = 0 ;i < 10; i ++){
                System.out.println(i);
                if(i == 5){
                    //使用LockSupport的park方法阻塞当前线程t
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
    }
}
