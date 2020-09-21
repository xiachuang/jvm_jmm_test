package com.xia.jmm.lock;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

public class CyclicBarrierDemo {
    public  static int Count=10;
    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier=new CyclicBarrier(Count,()->{
            System.out.println(Thread.currentThread().getName()+"\t 现在开会");
        });//达到指定条件后 触发该线程执行
        for(int i=1;i<=10;i++){
                    new Thread(()->{
                        try {
                            TimeUnit.MICROSECONDS.sleep(300);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        System.out.println(Thread.currentThread().getName()+"到教室了");
                        try {
                            cyclicBarrier.await();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        } catch (BrokenBarrierException e) {
                            e.printStackTrace();
                        }
                    },String.valueOf(i)).start();
                }
    }
}
