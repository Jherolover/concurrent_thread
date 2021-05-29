package com.elite.CAS;
/**
 * longadder 速度比sync
 */

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAdder;

public class AtomicVSSyncVSLongadder {
    static long count2 = 0L;
    static AtomicLong count1 = new AtomicLong(0L);
    static LongAdder count3 = new LongAdder();

    public static void main(String[] args) {
        //atomic 原子类
        Thread[] threads = new Thread[1000];
        for(int i = 0 ; i < threads.length; i ++){
            threads[i] = new Thread(()->{
                for(int k =0 ; k < 100000; k ++){
                    count1.incrementAndGet();
                }
            });
        }
        long start = System.currentTimeMillis();
        for(Thread t :threads){ t.start();};
        for(Thread t :threads){
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        long end = System.currentTimeMillis();
        System.out.println("Atomic:"+count1.get()+"time"+(end -start));

        //synchronized 实现累加
        Object lock = new Object();
        for(int i = 0 ; i < threads.length ; i++){
            threads[i] = new Thread(new Runnable() {
                @Override
                public void run() {
                  for(int k = 0 ; k < 100000;k++){
                      synchronized (lock){
                          count2 ++;
                      }
                  }
                }
            });
        }
        start = System.currentTimeMillis();
        for(Thread t : threads){t.start();}
        for (Thread t : threads){
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        end = System.currentTimeMillis();
        System.out.println("Sync:"+count2 +"time"+(end - start));
        //longadder
        for(int i = 0; i < threads.length; i++){
            threads[i] = new Thread(()->{
               for(int k = 0 ; k < 100000; k++){
                   count3.increment();
               }
            });
        }
        start  = System.currentTimeMillis();
        for(Thread t : threads){
            t.start();
        }
        for(Thread t: threads) {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        end = System.currentTimeMillis();
        System.out.println("LongAdder"+count3.longValue()+"time"+(end - start));

    }
    /**
     *
     */
    static void microSleep(int m){
        try {
            TimeUnit.MICROSECONDS.sleep(m);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
