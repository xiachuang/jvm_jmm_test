package com.xia.jmm.lock;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.ReentrantReadWriteLock;

class  myCache{
    private  final int Age=10;
//    private volatile AtomicReference<Thread> atomicReference=new AtomicReference<>();
    private volatile Map<String,Object> map=new HashMap<>();
    private ReentrantReadWriteLock rwLock=new ReentrantReadWriteLock();//读写锁

    public void put(){
        //设置 模块化编程  加快编程速度
        rwLock.writeLock().lock(); //获取 写锁
                try {

                }catch (Exception e){
                    e.printStackTrace();
                }finally {
                    rwLock.writeLock().unlock();
                }
    }
    public void get(){
         rwLock.readLock().lock();//获得写锁
                 try {
//                   int age=Age;
                 }catch (Exception e){
                     e.printStackTrace();
                 }finally {
                     rwLock.readLock().unlock();
                 }
    }
}
public class ReadWriterLockDemo {
    public static void main(String[] args) {
      for(int i=1;i<=10;i++){
                  new Thread(()->{

                  },String.valueOf(i)).start();
              }
    }
}
