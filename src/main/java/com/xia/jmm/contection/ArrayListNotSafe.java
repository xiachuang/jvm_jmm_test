package com.xia.jmm.contection;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

public class ArrayListNotSafe {
    AtomicInteger atomicInteger=new AtomicInteger();
    public static void main(String[] args) {
//    list();
        //解决方案
//        List<String> list1=Collections.synchronizedList(new ArrayList<>());//
        List<String> list=new CopyOnWriteArrayList<>();//arraylist 并发解决的方式二
//        List<String> list1=new ArrayList<>();  不安全
        for(int i=0;i<20;i++){
            new Thread(()->{
                list.add(UUID.randomUUID().toString().substring(0,8));
                System.out.println(Thread.currentThread().getName()+" "+list);
            },String.valueOf(i)).start(); }
        // 异常 ConcurrentModificationException
    }
// ConcurrentModificationException  高并发修改异常  concurrentModificationException  concurrent
    public static void list(){
        //线程不安全
//        List<String> list= Arrays.asList("A","b","c");
//        list.forEach(System.out::println);
//        Stream.of(Arrays.asList("s","v")).forEach(lists->{
//            System.out.println(lists);
//        });
        List<String> list1=new ArrayList<>();
        List<String> list=Collections.synchronizedList(new ArrayList<>());// 方法二  解决并发方法

        for(int i=0;i<3;i++){
            new Thread(()->{
                list.add(UUID.randomUUID().toString().substring(0,8));
                System.out.println(list);
            },String.valueOf(i)).start(); }

    }
}
