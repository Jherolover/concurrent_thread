package com.elite;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

public class HowToCreateThread {

    static class MyThread extends Thread{
        @Override
        public void run(){
            System.out.println("hello my thread");
        }
    }
    static class MyRun implements Runnable{
        @Override
        public void run(){
            System.out.println("hello my runable");
        }
    }
    static class Mycall implements Callable<String>{
        @Override
        public String call() throws Exception {
            System.out.println("hello mycall");
            return "success";
        }
    }

    public static void main(String[] args) {
        //启动线程的五种方式
        //1.继承Thread类
        new MyThread().start();
        //实现Runable接口
        new Thread(new MyRun()).start();
        //3、lambda表达式
        new Thread(()->{
            System.out.println("hello lamda");
        }).start();
        //
        Thread t = new Thread(new FutureTask<String>(new Mycall()));
        t.start();
        ExecutorService service = Executors.newCachedThreadPool();
        service.execute(()->{
            System.out.println("Hello ThreadPool");
        });
        service.shutdown();
    }
}
