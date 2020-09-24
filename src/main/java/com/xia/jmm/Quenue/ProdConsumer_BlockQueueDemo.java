package com.xia.jmm.Quenue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

class MyResource{//资源类
    private AtomicInteger  atomicInteger=new AtomicInteger();//默认为0
    private volatile  boolean FLAG=true;
    BlockingQueue<String> blockingQueue=null;
    public MyResource(BlockingQueue<String> blockingQueue){//传接口
        this.blockingQueue=blockingQueue;
        System.out.println(blockingQueue.getClass().getName());
    }
    public  void myProd() throws InterruptedException {
        String data=null;
        boolean reValue;
        while (FLAG){
            data=atomicInteger.incrementAndGet()+"";//i++
            reValue=blockingQueue.offer(data,2L, TimeUnit.SECONDS);
            if(reValue){
                System.out.println(Thread.currentThread().getName()+"\t插入队列成功"+data);
            }else {
                System.out.println(Thread.currentThread().getName()+"\t插入队列失败");
            }
            try { TimeUnit.MILLISECONDS.sleep(1000); } catch (InterruptedException e) { e.printStackTrace(); }
        }
        System.out.println(Thread.currentThread().getName()+"\t 线程停止 FLAG=false");
    }
    public void myConsumer() throws InterruptedException {
        String result=null;
        while (FLAG){
          result=  blockingQueue.poll(2L,TimeUnit.SECONDS);//设置取值的限定时间
          if(result==null||result.equalsIgnoreCase("")){
              FLAG=false;
              System.out.println(Thread.currentThread().getName()+"\t生产队列中不存在数据，消费退出");
              System.out.println();
              System.out.println();
              return;
          }
            System.out.println(Thread.currentThread().getName()+"\t 消费队列消费！"+ result +"成功");
        }
    }
    public void stop(){
        this.FLAG=false;
    }
}
public class ProdConsumer_BlockQueueDemo {

    public static void main(String[] args) {  //阻塞队列实现 消费者 生产者 模式

        MyResource myResource=new MyResource(new ArrayBlockingQueue<>(5));
        new Thread(()->{
            System.out.println(Thread.currentThread().getName()+"\t生产者线程启动");
            try {
                myResource.myProd();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"AA").start();
        new Thread(()->{
            System.out.println(Thread.currentThread().getName()+"\t 消费者启动");
            try {
                myResource.myConsumer();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"Consumer").start();
        try { TimeUnit.MILLISECONDS.sleep(10000); } catch (InterruptedException e) { e.printStackTrace(); }
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println("10秒结束");
        myResource.stop();
    }
}