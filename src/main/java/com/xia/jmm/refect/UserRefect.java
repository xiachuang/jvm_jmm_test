package com.xia.jmm.refect;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class UserRefect {
    public static void main(String[] args) throws Exception{
        Class<UserService> clazz=UserService.class;
        Method method=clazz.getDeclaredMethod("show", Integer.class);
        System.out.println(method.invoke(clazz.newInstance(),10));
        System.out.println(clazz.newInstance().show(5));
        UserService userService=clazz.getConstructor().newInstance();
//        method.setAccessible(true);]
       Field[] fields= clazz.getDeclaredFields();
       for(Field f:fields){
           f.setAccessible(true);
           System.out.println(f.getName());//获取属性的名
           System.out.println(f.get(userService));//传入 对象
       }
       Field field=clazz.getDeclaredField("name");
       String name=field.getName();
       name=name.substring(0,1).toUpperCase()+name.substring(1,name.length());
        System.out.println(name);

    }

}
abstract interface A{

}
