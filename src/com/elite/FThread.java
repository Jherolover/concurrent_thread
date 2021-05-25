package com.elite;

import java.sql.Time;
import java.util.concurrent.TimeUnit;

public class FThread  {
    synchronized void m(){
        System.out.println("m start");
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("m end");
    }

    public static void main(String[] args) {
        new  CThread().m();
    }
}
