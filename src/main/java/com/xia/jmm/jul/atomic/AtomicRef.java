package com.xia.jmm.jul.atomic;

import java.util.concurrent.atomic.AtomicReference;


public class AtomicRef {
    public static void main(String[] args) {


        User u2 = new User(23, "zhangsan");
        User u3 = new User(24, "xiachaung");
        AtomicReference<User> atomicReference = new AtomicReference<>();
        atomicReference.set(u2);//将u2设置成原子类型
        atomicReference.compareAndSet(u2,u3);  //原子引用包装
        System.out.println(atomicReference.get().toString());
    }
}
class User{
    String name;
    int age;
    public User(){

    }
    public  User(int age,String name){
        this.age=age;
        this.name=name;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}