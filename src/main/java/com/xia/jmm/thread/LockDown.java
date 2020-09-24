package com.xia.jmm.thread;

import java.util.concurrent.TimeUnit;
/*
    A->A --B
    B->B --A
 */
class HoldLockThread implements Runnable { //死锁 线程有自己的锁对象 还尝试去获取别的线程的锁对象
    private String lockA;
    private String lockB;

    public HoldLockThread(String lockA, String lockB) {
        this.lockA = lockA;
        this.lockB = lockB;
    }

    @Override
    public void run() {
        synchronized (lockA){
            System.out.println(Thread.currentThread().getName()+"\t自己拥有A锁"+lockA+"\t并尝试去获取B锁"+lockB);
            try { TimeUnit.MILLISECONDS.sleep(1500); } catch (InterruptedException e) { e.printStackTrace(); }
            synchronized (lockB){
                System.out.println(Thread.currentThread().getName()+"\t自己拥有B锁"+lockB+"\t并尝试获取A锁"+lockA);
            }
        }
    }
}
public class LockDown {
    public static void main(String[] args) {
        String lockA="lockA";
        String lockB="lockB";
        HoldLockThread holdLockThread=new HoldLockThread(lockA,lockB);
        new Thread(holdLockThread,"AAA").start();
        HoldLockThread holdLockThread1=new HoldLockThread(lockB,lockA);
        new Thread(holdLockThread1,"BBB").start();
    }
}
