package com.elite;

/**
 * 线程的状态
 */
public class ThreadState {

    static class MyThread extends Thread{
        @Override
        public void run(){
            System.out.println(this.getState());
            for (int  i =0 ; i < 10; i ++){
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(i);
            }
        }
    }

    public static void main(String[] args) {
        Thread t = new MyThread();
        //获取线程的状态
        System.out.println(t.getState());
        //启动完成之后是Runnable状态
        t.start();
        try {
            t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //join之后，结束了一个是Timenated的状态
        System.out.println(t.getState());
    }
}
