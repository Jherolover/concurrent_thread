package com.elite;

public class SynchronizedThis {
    private int count = 10;

    public void m(){
        //任何线程想要执行下边的代码，需要获取this的对象锁
        synchronized (this){
            count -- ;
            System.out.println(Thread.currentThread().getName()+"count="+count);
        }
    }
}
