package com.elite.CAS;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * 同步工具 cyclicBarrier
 * 循环栅栏，有一个栅栏，什么时候人满了就把栅栏推到
 * 出去之后又重新起来，再来人，满了，推到之后又继续
 *
 */
public class TestCyclicBarrier {

    public static void main(String[] args) {

       // CyclicBarrier barrier = new CyclicBarrier(20);
      //CyclicBarrier barrier  = new CyclicBarrier(20,()-> System.out.println("满人"));
        CyclicBarrier barrier = new CyclicBarrier(20, new Runnable() {
            @Override
            public void run() {
                System.out.println("满人，发车");
            }
        });
        //启动100个线程，满20个执行一次
        for( int i = 0 ;i < 100; i ++){
            new Thread(()->{
                try {
                    barrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }).start();
        }


    }
}
