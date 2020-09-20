package com.xia.jmm.dcl;

import java.util.concurrent.atomic.AtomicInteger;

//dcl 模式的单例模式
public class Singleta {
    private volatile static Singleta instance=null; //禁止指令重排
    private Singleta(){
        System.out.println(Thread.currentThread().getName()+"\t"+"hello");
    }
    private  static Singleta getInstance(){
        if(instance==null){
            synchronized (Singleta.class) {//双端加锁模式
                if(instance==null) {
                    instance = new Singleta();
                }
            }
        }
        return instance;
    }

    public static void main(String[] args) {
        System.out.println(Singleta.getInstance()==Singleta.getInstance());
//        System.out.println(Singleta.getInstance()==Singleta.getInstance());
//        System.out.println(Singleta.getInstance()==Singleta.getInstance());
        System.out.println("多线程");
        for(int i=0;i<10;i++){
            new Thread(()->{
               Singleta.getInstance();
            },String.valueOf(i)).start();
        }
        Singleta singleta=new Singleta();
        System.out.println(singleta.getAtomicInteger());
        singleta.addAtomic();
        System.out.println(singleta.number);

    }
    public int getAtomicInteger(){
        AtomicInteger atomicInteger=new AtomicInteger(5);
        atomicInteger.compareAndSet(5,2020);
        return  atomicInteger.get();
    }
    AtomicInteger number=new AtomicInteger(0);
    public void addAtomic(){
        for(int i=0;i<1000;i++) {
            number.getAndIncrement();
        }
    }
}
