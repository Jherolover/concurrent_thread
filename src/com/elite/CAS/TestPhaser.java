package com.elite.CAS;

import javax.management.relation.RelationNotFoundException;
import java.util.Random;
import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;

/**
 * 阶段
 * Phaser它就像是结合了CountDownLatch和CyclicBarrier
 */
public class TestPhaser {

    static Random r = new Random();

    static MarriagePhaser phaser = new MarriagePhaser();

    //定义一个方法
    static  void millisleep(int milli){
        try {
            TimeUnit.MILLISECONDS.sleep(milli);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    //主方法
    public static void main(String[] args) {
        //7个人 5个客人+新郎新娘
        phaser.bulkRegister(7);
        for(int i = 0 ;i < 5 ; i++){
            new Thread(new Person("p"+i)).start();
        }
        new Thread(new Person("新郎")).start();
        new Thread(new Person("新娘")).start();

    }


    static class MarriagePhaser extends Phaser{
        @Override
        protected  boolean onAdvance(int phase,int registeredParties){
            switch (phase){
                case 0 :
                    System.out.println("所有人到齐了"+registeredParties);
                    System.out.println();
                    return false;
                case 1:
                    System.out.println("所有人都吃完了"+registeredParties);
                    System.out.println();
                    return false;
                case 2:
                    System.out.println("所有人都离开了"+registeredParties);
                    System.out.println();
                    return false;
                case 3:
                    System.out.println("婚礼结束"+registeredParties);
                    return true;
                default:return true;
            }
        }
    }
    //定义一个person类
    static class  Person implements Runnable{

        String name ;

        public Person(String name){
            this.name = name;
        }
        //到达
        public void arrive(){
            millisleep(1000);
            System.out.println("%s 到达现场"+name);
            phaser.arriveAndAwaitAdvance();
        }
        //吃
        public void eat(){
            millisleep(1000);
            System.out.println("%s 吃完"+name);
            phaser.arriveAndAwaitAdvance();
        }
        //离开
        public void leave(){
            millisleep(1000);
            System.out.println("%s 离开"+name);
            phaser.arriveAndAwaitAdvance();
        }
        //
        public void hug(){
            if("新娘".equals(name)||"新郎".equals(name)){
                millisleep(r.nextInt());
                System.out.println("%s 闹洞房"+name);
                phaser.arriveAndAwaitAdvance();
            }else{
                phaser.arriveAndDeregister();
            }
        }
        @Override
        public void run() {
            arrive();
            eat();
            leave();
            hug();
        }
    }
}
