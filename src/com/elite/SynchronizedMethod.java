package com.elite;

public class SynchronizedMethod {
    private int count = 10;
    //锁住方法
    public  synchronized  void m(){
        //任何线程想要执行下边的代码，需要获取this的对象锁
        //synchronized (this){
            count -- ;
            System.out.println(Thread.currentThread().getName()+"count="+count);
       // }
    }
    public static void main(String[] args) {
        SynchronizedMethod m = new SynchronizedMethod();
        m.m();
    }
}
