package com.elite;

public class SynchronizedObject {
    private int count = 10;
    private Object o = new Object();
    public void m(){
        //任何线程想要执行下边的代码，需要获取o的对象锁
        synchronized (o){
            count -- ;
            System.out.println(Thread.currentThread().getName()+"count="+count);
        }
    }
}
