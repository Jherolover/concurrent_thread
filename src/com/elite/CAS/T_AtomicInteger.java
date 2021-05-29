package com.elite.CAS;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 解决问题的高效方法 使用AtomicXX类
 * Atomic本身类的方法是原子性的，但不能保证多个方法连续调用都是原子性
 */
public class T_AtomicInteger {

    AtomicInteger count = new AtomicInteger(0);
    void m(){
        for(int i = 0;i < 10000; i ++){
            count.incrementAndGet();//count++
        }
    }

    public static void main(String[] args) {

        //创建一个类
        T_AtomicInteger t = new T_AtomicInteger();
        List<Thread> threads = new ArrayList<>();
        for(int i = 0 ; i < 10; i++){
            threads.add(new Thread(t::m,"thread-"+i));
        }
        threads.forEach((o)-> o.start());

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
