package com.xia.jmm.refect;

public class UserService {
    private static  String name="xiachuang";
    public static  int age=18;
    public UserService(){
        System.out.println("构造方法！！");
    }
    public int show(Integer id){
        return id;
    }
}
