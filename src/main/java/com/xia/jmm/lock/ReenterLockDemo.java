package com.xia.jmm.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReenterLockDemo {
    public static void main(String[] args) throws InterruptedException {
        Phone phone=new Phone();
        new Thread(()->{
            try {
                phone.sendSMS();
            } catch (Exception e) {
                e.printStackTrace();
            }
        },"t1").start();
        new Thread(()->{
            try {
                phone.sendSMS();
            } catch (Exception e) {
                e.printStackTrace();
            }
        },"t2").start();
        System.out.println("-------------------------------");
        TimeUnit.SECONDS.sleep(1);
        Thread t3=new Thread(phone);
        Thread t4=new Thread(phone);
        t3.start();
        t4.start();
    }
}
class Phone implements Runnable{
    //资源
    Lock lock1=new ReentrantLock();
    Lock lock=new ReentrantLock();
    public synchronized  void sendSMS() throws Exception{
        System.out.println(Thread.currentThread().getId() +"\t  invoked sendSMS()");
        sendEmail();
    }
    public synchronized  void sendEmail() throws Exception{
        System.out.println(Thread.currentThread().getId() +"\t ------ invoked sendEmail()");
    }
    public void get() throws Exception{
        lock.lock();//lock  和 unlock 配对  就可以  如果只写一个  会卡死
        try {
            System.out.println(Thread.currentThread().getId() +"\t  invoked sendSMS()");
            set();
        }finally {
            lock.unlock();
        }
    }
    public void set() throws Exception{
        lock.lock();
        try{
            System.out.println(Thread.currentThread().getId() +"\t ------ invoked sendEmail()");
        }finally {
            lock.unlock();
        }
    }

    @Override
    public void run() {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            get();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}