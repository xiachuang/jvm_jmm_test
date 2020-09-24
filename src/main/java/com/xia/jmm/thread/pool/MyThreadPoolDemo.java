package com.xia.jmm.thread.pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class MyThreadPoolDemo {
    public static void main(String[] args) {
//        ExecutorService executorService= Executors.newFixedThreadPool(5);//创建5个线程
//        ExecutorService executorService=Executors.newSingleThreadExecutor();//一次只创建一个线程
        ExecutorService executorService=Executors.newCachedThreadPool();//创建多个
        try {
            for (int i = 0; i < 10; i++) {
                executorService.submit(() -> {
                    System.out.println(Thread.currentThread().getName() + "\t 办理业务");
                    try { TimeUnit.MILLISECONDS.sleep(30); } catch (InterruptedException e) { e.printStackTrace(); }
                });
//                try { TimeUnit.MILLISECONDS.sleep(50); } catch (InterruptedException e) { e.printStackTrace(); }
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            executorService.shutdown();//关闭连接池
        }
    }
    public  void showSingle(){
        ExecutorService executorService=Executors.newSingleThreadExecutor();//一次只创建一个线程
//        try {
//            for (int i = 0; i < 10; i++) {
//                executorService.submit(() -> {
//                    System.out.println(Thread.currentThread().getName() + "\t 办理业务");
//                });
//            }
//        }catch (Exception e){
//            e.printStackTrace();
//        }finally {
//            executorService.shutdown();//关闭连接池
//        }
    }
    public void showFixed(){
        ExecutorService executorService= Executors.newFixedThreadPool(5);//创建5个线程
//        try{
//            for (int i = 0; i < 10; i++) {
//                executorService.submit(() -> {
//                    System.out.println(Thread.currentThread().getName() + "\t 办理业务");
//                });
//            }
//        }catch (Exception e){
//            e.printStackTrace();
//        }finally {
//            executorService.shutdown();//关闭连接池
//        }
  }

}
