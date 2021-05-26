package com.elite;

/**
 * 懒汉式
 */
public class Singleton04 {
    private static Singleton04 INSTANCE;

    private Singleton04(){}

    //线程安全6
    public static  Singleton04 getInstance(){

        if(INSTANCE == null){
            synchronized (Singleton04.class) {
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                INSTANCE = new Singleton04();
            }
        }
        return INSTANCE;
    }
}
