package com.xia.jmm.Quenue;

import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

public class SynchornousQueueDemo {  // 一进一出
    public static void main(String[] args) {
        SynchronousQueue<String> synchronousQueue=new SynchronousQueue<>();
        new Thread(()->{

            try {
                System.out.println(Thread.currentThread().getName()+"\t put 1");
                synchronousQueue.put("1");
                System.out.println(Thread.currentThread().getName()+"\t put 2");
                synchronousQueue.put("2");
                System.out.println(Thread.currentThread().getName()+"\t put 3");
                synchronousQueue.put("3");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"AA").start();
        new Thread(()->{

            try {
                try { TimeUnit.MILLISECONDS.sleep(1000); } catch (InterruptedException e) { e.printStackTrace(); }
                System.out.println(Thread.currentThread().getName()+"\t"+synchronousQueue.take());
                try { TimeUnit.MILLISECONDS.sleep(1000); } catch (InterruptedException e) { e.printStackTrace(); }
                System.out.println(Thread.currentThread().getName()+"\t"+synchronousQueue.take());
                try { TimeUnit.MILLISECONDS.sleep(1000); } catch (InterruptedException e) { e.printStackTrace(); }
                System.out.println(Thread.currentThread().getName()+"\t"+synchronousQueue.take());

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"AA").start();

    }
}
