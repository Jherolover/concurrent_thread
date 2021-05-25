package com.elite;

public class SynchronizeVolatile implements Runnable {
    private volatile  int count = 100;

    @Override
    public synchronized  void run() {
        count  --;
        System.out.println(Thread.currentThread().getName()+"conut="+count);
    }

    public static void main(String[] args) {
        SynchronizeVolatile t = new SynchronizeVolatile();
        for(int i  = 0 ; i < 100; i++){
            new Thread(t,"Thread"+i).start();
        }
    }
}
