package com.xia.jmm.lock;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;

 class  myCache{
//    private  final int Age=10;
//    private volatile AtomicReference<Thread> atomicReference=new AtomicReference<>();
    private volatile Map<String,Object> map=new HashMap<>();
    private ReentrantReadWriteLock rwLock=new ReentrantReadWriteLock();//读写锁

    public void put(String key,Object value){
        //设置 模块化编程  加快编程速度
            rwLock.writeLock().lock(); //获取 写锁
                try {
                    System.out.println(Thread.currentThread().getName()+"：\t正在写入"+key);
                    try {
                          TimeUnit.SECONDS.sleep(1);
                      }catch (InterruptedException e){
                          e.printStackTrace();
                      }
                    map.put(key,value);
                    System.out.println(Thread.currentThread().getName()+"：\t正在完成:");
                }catch (Exception e){
                    e.printStackTrace();
                }finally {
                    rwLock.writeLock().unlock();
                }
    }
    public void get(String key){
         rwLock.readLock().lock();//获得写锁
                 try {
                     System.out.println(Thread.currentThread().getName()+"\t正在读取");
                     try {
                         TimeUnit.MILLISECONDS.sleep(300);
                     }catch (InterruptedException e){
                         e.printStackTrace();
                     }
                     Object object =map.get(key);
                     System.out.println(Thread.currentThread().getName()+"\t读出的值为："+object.toString());
                 }catch (Exception e){
                     e.printStackTrace();
                 }finally {
                     rwLock.readLock().unlock();
                 }
    }
}
public class ReadWriterLockDemo {
    public static void main(String[] args) {
        myCache myCache=new myCache();
        for(int i=1;i<=6;i++){
                final  int tempInt=i;
                  new Thread(()->{
                      myCache.put(tempInt+"",tempInt+"");
                      myCache.get(tempInt+"");
                  },String.valueOf(i)).start();
              }
    }
}
