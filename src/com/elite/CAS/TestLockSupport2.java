package com.elite.CAS;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

/**
 *
 */
public class TestLockSupport2 {

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
        //唤醒线程t
        //线程unpark方法优先park方法执行
        LockSupport.unpark(t);
    }
}
