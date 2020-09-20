package com.xia.jmm.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class T1 {
    volatile  int n=0;
    public void add(){
        n++;
    }

    public static void main(String[] args) {
        Lock lock=new ReentrantLock(true);//true 为公平的  默认为false 非公平


    }

}
