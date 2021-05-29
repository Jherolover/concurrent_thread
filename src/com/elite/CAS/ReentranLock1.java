package com.elite.CAS;

import java.util.concurrent.TimeUnit;

/**
 * reentrantLock 用于替代 synchronized
 * 本例中由于m1锁定this,只有m1执行完毕的时候,m2才能执行
 * 这是复习synchronized的意义
 *
 */
public class ReentranLock1 {

    synchronized  void m1(){
        for(int i = 0 ; i <10;i++){
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(i);
            if(i == 2){m2();};
        }
    }
    synchronized void m2(){
        System.out.println("m2.....");
    }

    public static void main(String[] args) {
        ReentranLock1 r1 = new ReentranLock1();
        new Thread(r1::m1).start();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
