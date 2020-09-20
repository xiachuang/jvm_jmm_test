package com.xia.jmm.contection;

import java.sql.Connection;
import java.util.*;
import java.util.concurrent.CopyOnWriteArraySet;

public class SetNotSafe {
    public static void main(String[] args) {
//        testSet();
        //方式二
        new HashSet<>();//底层 HashMap  数组加链表的  结构

        testSet();
    }

    public static void testSet(){
//        Set<String> set=new HashSet<>();
        //方式一
//        Set<String> set= Collections.synchronizedSet(new HashSet<>());
//        方式二
        Set<String> set=new CopyOnWriteArraySet<>();//底层还是 ArrayList

        for(int i=0;i<30;i++){
            new Thread(()->{
            set.add(UUID.randomUUID().toString().substring(0,8));
                System.out.println(set);
            },String.valueOf(i)).start();
        }
    }
}
