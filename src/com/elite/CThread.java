package com.elite;

public class CThread  extends FThread{
    @Override
    synchronized void m(){
        System.out.println("c start");
        super.m();
        System.out.println("c end");
    }
}
