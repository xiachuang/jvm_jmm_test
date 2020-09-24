package com.xia.jmm.thread.pool;

import sun.awt.datatransfer.ToolkitThreadBlockedHandler;

import java.util.concurrent.*;
import java.util.logging.Handler;

public class ThreadPoolDiy {
    public static void main(String[] args) {
        ExecutorService threadPool=new ThreadPoolExecutor(
                2,//核心线程数
                5,//最大线程数 =CUP荷属+1
                1500L, //等待时间
                TimeUnit.MILLISECONDS,//
                new LinkedBlockingQueue<>(3),//阻塞队列大小
                Executors.defaultThreadFactory(),//默认工厂
                new ThreadPoolExecutor.AbortPolicy());//拒接策略 CallerRunPolicy 回调 AbortPolicy 异常 DiscardOldestPolicy放弃等待最长的线程
        try {
            for(int i=1;i<=10;i++){
                final int temp=i;
                threadPool.submit(()->{
                    System.out.println(Thread.currentThread().getName()+"窗口号"+"\t服务各户"+temp);
                    try { TimeUnit.MILLISECONDS.sleep(1000); } catch (InterruptedException e) { e.printStackTrace(); }
                });
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            threadPool.shutdown();
        }
    }

}
