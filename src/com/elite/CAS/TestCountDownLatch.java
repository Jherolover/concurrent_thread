package com.elite.CAS;

import java.util.concurrent.CountDownLatch;

/**
 * countdown倒数，latch门栓的意思
 *
 */
public class TestCountDownLatch {
    public static void main(String[] args) {
        usingCountDownLatch();
        uingjoin();
    }
    //
    private static  void usingCountDownLatch(){
        //100个线程
        Thread[] threads = new Thread[100];
       //创建门栓
        CountDownLatch latch = new CountDownLatch(threads.length);
        for(int i = 0 ; i < threads.length; i++){
            threads[i] = new Thread(()->{
                int result = 0;
                for(int j = 0 ; j < 10000; j++){
                    result += j;
                   // System.out.println(result);
                    latch.countDown();
                }
            });
        }
        //启动线程
        for(int i = 0 ; i < threads.length ; i++){
            threads[i].start();
        }
        //latch 等待
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("end latch");
    }

    /**
     * 使用join
     */
    private static  void uingjoin()  {
        //100个线程
        Thread[] threads = new Thread[100];

        for(int i = 0 ; i < threads.length; i++){
            threads[i] = new Thread(()->{
                int result = 0;
                for(int j = 0 ; j < 10000; j++){
                    result += j;
                    //System.out.println(result);

                }
            });
        }
        //启动线程
        for(int i = 0 ; i < threads.length ; i++){
            threads[i].start();
        }
        //join
        //启动线程
        for(int i = 0 ; i < threads.length ; i++){
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("join end");
    }
}
