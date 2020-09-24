package com.xia.jmm.Quenue;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class  ShareDate{
    private Integer number=1;
    private Lock lock=new ReentrantLock();
    private Condition c1=lock.newCondition();
    private Condition c2=lock.newCondition();
    private Condition c3=lock.newCondition();
    public void show(){

    }
    public void show5(){
        lock.lock();
        try {
            while (number!=1){
                c1.await();
            }
            for(int i=1;i<=5;i++){
                System.out.println(Thread.currentThread().getName()+"\t"+i);
            }
            number=2;
            c2.signal();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }

    }
    public void show10(){
      lock.lock();
      try {
        while (number!=2){
            c2.await();
        }
          for(int i=1;i<=10;i++){
              System.out.println(Thread.currentThread().getName()+"\t"+i);
          }
          number=3;
          c3.signal();
      }catch (Exception e){
          e.printStackTrace();
      }finally {
          lock.unlock();
      }


    }
    public void show15(){
      lock.lock();
      try {
      while (number!=3){
          c3.await();
      }
          for(int i=1;i<=15;i++){
              System.out.println(Thread.currentThread().getName()+"\t"+i);
          }
      number=1;
          c1.signal();
      }catch (Exception e){
          e.printStackTrace();
      }finally {
          lock.unlock();
      }

    }
}
public class ConditionDemo {

    public static void main(String[] args) {
        ShareDate shareDate=new ShareDate();
       new Thread(()->{
           for(int i=1;i<=10;i++){
               shareDate.show5();
           }
       },"AA").start();
        new Thread(()->{
            for(int i=1;i<=10;i++){
                shareDate.show10();
            }
        },"BB").start();
        new Thread(()->{
            for(int i=1;i<=10;i++){
                shareDate.show15();
            }
        },"CC").start();
    }
}
