package com.xia.jmm.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

class MyCallable implements Callable{

    @Override
    public Integer call() throws Exception {
        try { TimeUnit.MILLISECONDS.sleep(2000); } catch (InterruptedException e) { e.printStackTrace(); }
        return 1024;
    }
}

public class CallableDemo  {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask<Integer> futureTask=new FutureTask<>(new MyCallable());
//        new Thread(futureTask,"BB").start();  FutureTask 自动复用
        Thread t1=new Thread(futureTask,"Call");
        t1.start();
        System.out.println("****************************");
        int result1=100;
        while (!futureTask.isDone()){

        }
        int result=futureTask.get();//返回 Callable call 方法中的返回值  通常放在最后执行  否则会造成线程的阻塞
        System.out.println(result+result1);
    }
    //   System.out.println(Runtime.getRuntime().availableProcessors()); 查看线程数
    //   ThreadPoolExecutor 线程池的底层实现  ThreadPoolExecutor ThreadPoolExecutor
    //Executors  辅助工具类  Arrays  Collections
}
