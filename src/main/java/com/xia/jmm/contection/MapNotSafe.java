package com.xia.jmm.contection;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;

public class MapNotSafe {
    public static void main(String[] args) {
        MapNotSafe mapNotSafe=new MapNotSafe();//引用对象  指向实例对象  mapNotSafe 存的是 实例对象的地址
        //引用类型 指向的指针  值存在 堆中 string 是存在常量池中的
        //基本类型是在栈中的
        map1();
    }
    public static  void mapTest(){
        //不安全的实例

        Map<String,String> map=new HashMap<>();
        for(int i=0;i<30;i++){
            new Thread(()->{
                map.put(Thread.currentThread().getName(), UUID.randomUUID().toString().substring(0,8));
                System.out.println(map);
            },String.valueOf(i)).start();
        }
    }
    public static void map1(){
        Map<String,String > map=new HashMap<>();
        map.put("name","xiachuang");
        map.put("age","22");
        for(String key:map.keySet()){
            map.get(key
            );
        }
      Iterator iterator= map.entrySet().iterator();//迭代器
        while (iterator.hasNext()){
            Map.Entry<String,String> entry= (Map.Entry<String, String>) iterator.next();
            System.out.println("Key"+entry.getKey()+" value"+entry.getValue());
        }
    }
    public static void map2(){
        Map<Object,String > map=new HashMap<>();
        Iterator iterator=map.entrySet().iterator();
        while (iterator.hasNext()){
         Map.Entry<Object,String >  entry= (Map.Entry<Object, String>) iterator.next();
            System.out.println(entry.getKey()+entry.getValue());
        }
    }
}
