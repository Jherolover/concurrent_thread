package com.elite;

/**
 * 懒汉式
 * 可以通过synchronized 解决，但是也带来了效率下降
 */
public class Singleton05 {
    private  static volatile  Singleton05 INSTANCE;

    private Singleton05(){}

    //线程安全6
    public static Singleton05 getInstance(){

        if(INSTANCE == null){
            synchronized (Singleton05.class) {
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                INSTANCE = new Singleton05();
            }
        }
        return INSTANCE;
    }
}
