package com.xia.jmm.lock;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class CountDownLatchDemo {
    private static final int  Count=4;
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch=new CountDownLatch(Count);
        for(int i=1;i<=4;i++){
                    new Thread(()->{
                        try {
                            TimeUnit.SECONDS.sleep(1);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        System.out.println(Thread.currentThread().getName()+"\t天  ：");
                        countDownLatch.countDown();
                    },CountryEnum.forEach_CountryEnum(i).getName()).start();
                }
        countDownLatch.await();//挡住线程
        System.out.println(Thread.currentThread().getName()+"\t过年了");
    }

    public static void show(){
        CountDownLatch countDownLatch=new CountDownLatch(Count);
        for(int i=1;i<=10;i++){
            new Thread(()->{
                System.out.println(Thread.currentThread().getName()+"\t吃完饭上课去了");
                countDownLatch.countDown();
            },String.valueOf(i)).start();
        }
        try {
            countDownLatch.await();//挡住线程
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName()+"\t洗碗");
    }
}
