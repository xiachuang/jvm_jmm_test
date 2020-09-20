package com.xia.jmm.jul;

import java.util.concurrent.atomic.AtomicInteger;

//CAS 比较交换  保证数据的原子性 ==>compareAndSet
public class CasDemo {
    public static void main(String[] args) {
        AtomicInteger atomicInteger=new AtomicInteger(5);
//        atomicInteger.compareAndSet(5,2019);
//      期望值  以及我要修改的值  //如果期望值和主内存中所拿到的值 相同则交换 boolean 值

        System.out.println(atomicInteger.compareAndSet(5,2019)+"   "+atomicInteger.get());
        //      期望值 与主内存不同时   值不交换 保持原来的值
        System.out.println(atomicInteger.compareAndSet(5,2019)+"   "+atomicInteger.get());
        atomicInteger.getAndIncrement();//i++

    }
}
