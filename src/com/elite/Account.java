package com.elite;

import java.sql.SQLOutput;
import java.util.concurrent.TimeUnit;

/**
 * 对业务方法加锁
 * 业务的读方法加锁和写方法加锁
 *
 * 读方法不加锁则会产生dirtyRead
 */

public class Account {
    String name;
    double balance;
    public synchronized void set(String name,double balance){
        this.name = name;
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.balance = balance;
    }

    /**
     * 获取金额
     * @param name
     * @return
     */
    public synchronized double getBalance(String name){
       return this.balance;
    }

    public static void main(String[] args) {
        Account a = new Account();
        new Thread(()-> a.set("elite",100.00)).start();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(a.getBalance("elite"));
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(a.getBalance("elite"));
    }

}
