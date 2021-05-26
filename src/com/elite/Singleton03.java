package com.elite;

/**
 * 懒汉式
 */
public class Singleton03 {
    private static Singleton03 INSTANCE;

    private Singleton03(){}

    //线程安全6
    public static synchronized Singleton03 getInstance(){

        if(INSTANCE == null){
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            INSTANCE = new Singleton03();
        }
        return INSTANCE;
    }
}
