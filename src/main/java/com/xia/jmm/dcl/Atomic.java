package com.xia.jmm.dcl;

import java.util.concurrent.atomic.AtomicInteger;

public class Atomic {
    private volatile int n=0;
    AtomicInteger atomicInteger=new AtomicInteger();//保证原子性
    public void addAtomic(){
        atomicInteger.getAndIncrement();//i++原子性的i++
//        atomicInteger.getAndDecrement()//i--
    }
    public void addPlus(){
        n++;
    }
    public static void main(String[] args) {
        Atomic atomic=new Atomic();
        for(int i=0;i<10;i++){
            new Thread(()->{
                for(int j=0;j<2000;j++) {
                    atomic.addAtomic();
                    atomic.addPlus();
                }
            },String.valueOf(i)).start();
        }
        while (Thread.activeCount()>2){
            Thread.yield();
        }
        System.out.println(Thread.currentThread().getName()+"   "+atomic.n);
        System.out.println(Thread.currentThread().getName()+"   "+atomic.atomicInteger);
    }
}
