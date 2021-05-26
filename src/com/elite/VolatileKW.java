package com.elite;

import java.util.concurrent.TimeUnit;

/**
 * volatile 关键字 是一个变量在多个线程间可见
 * A B 线程都用到一个变量  java默认是A线程中保留一份copy，如果线程B 线程修改了改变量，A线程必须知道
 * Volatile 并不能保证多个线程共同修改running变量时所带来的不一致问题，也就是volatile 不能替代synchronized关键字
 */
public class VolatileKW {
   volatile    boolean running = true;

    void m(){
        System.out.println("m start");
        while(running){

        }
        System.out.println("m end");
    }

    public static void main(String[] args) {
        VolatileKW t = new VolatileKW();
        new Thread(t::m,"t1").start();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //由于变量没加volatile的时候修改了，别的线程不可见所以观察不到
        // 当加了volatile之后循环的值变了，线程就停止结束了
        t.running = false;
    }
}
