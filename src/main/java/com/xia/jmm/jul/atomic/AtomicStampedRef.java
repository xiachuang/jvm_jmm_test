package com.xia.jmm.jul.atomic;
//带时间戳的原子引用

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;

public class AtomicStampedRef {
    static AtomicReference<Integer> atomicReference=new AtomicReference<>(100);
    static AtomicInteger atomicInteger=new AtomicInteger(1000);
    static  AtomicStampedReference<Integer> atomicStampedReference=new AtomicStampedReference<>(100,1);//initialStamp 版本号的意思

    public static void main(String[] args) {
        System.out.println("=============================产生===============");
        new Thread(()->{
            atomicReference.compareAndSet(100,101);
            atomicReference.compareAndSet(101,100);
        },"t1").start();
        new Thread(()->{
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
           boolean bl= atomicReference.compareAndSet(100,2020);
            System.out.println(bl+"  "+atomicReference.get());
        },"t2").start();

        //停止两秒钟
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("=============================解决===============");
        new Thread(()->{
            int samp=atomicStampedReference.getStamp();//获得时间戳
            System.out.println(Thread.currentThread().getName()+"   "+samp);
            try {
                TimeUnit.SECONDS.sleep(1);//暂停1秒
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            atomicStampedReference.compareAndSet(100,101,atomicStampedReference.getStamp(),atomicStampedReference.getStamp()+1);
            System.out.println(atomicStampedReference.getReference()+"   " +atomicStampedReference.getStamp());
            atomicStampedReference.compareAndSet(101,100,atomicStampedReference.getStamp(),atomicStampedReference.getStamp()+1);
            System.out.println(atomicStampedReference.getReference()+"   " +atomicStampedReference.getStamp());

        },"t3").start();
        new Thread(()->{
            int samp=atomicStampedReference.getStamp();
            System.out.println(Thread.currentThread().getName()+"    "+samp);
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            boolean result=atomicStampedReference.compareAndSet(100,2020,samp,samp+1);
            System.out.println(Thread.currentThread().getName()+" "+result+"  "+atomicStampedReference.getStamp());
            System.out.println(Thread.currentThread().getName()+ "  "+atomicStampedReference.getReference());
        },"t4").start();
    }

}

