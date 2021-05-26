package com.elite;

import java.util.ArrayList;
import java.util.List;

/**
 * synchronized保证了原子性操作 但是不能保证线程之间的可见性
 * volatile 能保证线程的可见性
 *
 */
public class T {

    volatile  int count = 0;

   synchronized void m(){
        for(int i = 0 ; i < 10000; i++){
            count ++;
        }
    }

    public static void main(String[] args) {
        T t =  new T();
        List<Thread> threads = new ArrayList<>();
        for(int i = 0 ; i < 10 ; i++){
            threads.forEach((o)->{
                try {
                    o.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
            System.out.println(t.count);
        }
    }
}
