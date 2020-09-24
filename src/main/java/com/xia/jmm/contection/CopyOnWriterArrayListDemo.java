package com.xia.jmm.contection;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class CopyOnWriterArrayListDemo {
   static List<String> list=new CopyOnWriteArrayList<>();

    public static void main(String[] args) {
        list.add("a");
    }
}
