package com.xia.jmm.Quenue;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class  ShareData{ //资源类 lock  判断  操作  通知
    private int number=0;
    private Lock lock=new ReentrantLock();
    private Condition condition=lock.newCondition();//获得 Condition
    public void increment(){
        lock.lock();
        try {
            while(number!=0){
                condition.await();
            }
            try { TimeUnit.MILLISECONDS.sleep(500); } catch (InterruptedException e) { e.printStackTrace(); }

            number++;
            System.out.println(Thread.currentThread().getName()+"/t生产一个"+number);
            condition.signal();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
    //消费者
    public void decrement(){
        lock.lock();
        try {
            while(number==0){   //while  控制虚假唤醒
                condition.await();
            }
            try { TimeUnit.MILLISECONDS.sleep(300); } catch (InterruptedException e) { e.printStackTrace(); }

            number--;
            System.out.println(Thread.currentThread().getName()+"/t消费一个"+number);
            condition.signal();
        }catch (Exception e){
                     e.printStackTrace();
        }finally {
                     lock.unlock();
        }
    }
}
public class ProdConsumer_TraditionDemo01 {//生产者 消费者  就是 线程操作资源类
    public static void main(String[] args) {
        ShareData shareData=new ShareData();
        new Thread(()->{
            for (int i=0;i<5;i++) {
                try {
                    shareData.increment();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        },"AA").start();
        new Thread(()->{
            for (int i=0;i<5;i++) {
                try {
                    shareData.decrement();
                }catch (Exception e){
                    e.printStackTrace();
                }

            }
        },"BB").start();
        new Thread(()->{
            for (int i=0;i<5;i++) {
                try {
                    shareData.increment();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        },"CC").start();
        new Thread(()->{
            for (int i=0;i<5;i++) {
                try {
                    shareData.decrement();
                }catch (Exception e){
                    e.printStackTrace();
                }

            }
        },"DD").start();
    }
}
