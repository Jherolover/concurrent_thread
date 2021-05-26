package com.elite;

/**
 * 懒汉式
 */
public class Singleton02 {
    private static  Singleton02 INSTANCE;

    private Singleton02(){}

    public static  Singleton02 getInstance(){

        if(INSTANCE == null){
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            INSTANCE = new Singleton02();
        }
        return INSTANCE;
    }
}
