package com.xia.jmm.contection;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ArrayTest {
    public static void main(String[] args) {
        List<String> list=new ArrayList<>();
        for(int i=0;i<30;i++){
            new Thread(()->{
                list.add(UUID.randomUUID().toString().substring(0,8));
                System.out.println(list);
            },String.valueOf(i)).start();
        }
    }
}
