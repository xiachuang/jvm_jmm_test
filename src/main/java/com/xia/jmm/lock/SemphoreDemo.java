package com.xia.jmm.lock;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class SemphoreDemo {
    public static void main(String[] args) {
        /*
        抢车位的性质
        参数为： 信号量
        资源释放后 信号量会自动回滚
         */
        Semaphore semaphore=new Semaphore(3);
        for(int i=1;i<=10;i++){
                    new Thread(()->{
                        try {
                            semaphore.acquire();
                            System.out.println(Thread.currentThread().getName()+"\t车位站定");
                            try { TimeUnit.MILLISECONDS.sleep(2000); } catch (InterruptedException e) { e.printStackTrace(); }
                            System.out.println(Thread.currentThread().getName()+"\t 车开走了 2秒后让出车位");
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }finally {
                            semaphore.release();//释放一个资源

                        }
                    },String.valueOf(i)).start();
                }
    }
}
