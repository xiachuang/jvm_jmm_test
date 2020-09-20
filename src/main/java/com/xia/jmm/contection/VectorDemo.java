package com.xia.jmm.contection;

import java.util.List;
import java.util.UUID;
import java.util.Vector;

public class VectorDemo {
    public static void main(String[] args) {
        List<String> list=new Vector<>();
        for(int i=0;i<4;i++){
            new Thread(()->{
                list.add(UUID.randomUUID().toString().substring(0,8));
                System.out.println(Thread.currentThread().getName()+"  "+list);

            },String.valueOf(i)).start();
        }
    }
}
