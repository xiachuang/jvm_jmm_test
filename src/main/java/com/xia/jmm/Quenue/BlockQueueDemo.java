package com.xia.jmm.Quenue;

import java.util.concurrent.*;

public class BlockQueueDemo { //阻塞队列是线程安全的
    BlockingQueue linkedBlockingQueue=new LinkedBlockingQueue<>(3);//默认为 Integer.MAX_VALUE 链表构成
    BlockingQueue blockingQueue=new LinkedBlockingDeque();//链表构成的无极界限 阻塞队列
    BlockingQueue blockingQueue1=new LinkedBlockingQueue();//双向链表的 阻塞队列
    //非阻塞队列
    ConcurrentLinkedQueue concurrentLinkedQueue=new ConcurrentLinkedQueue();//非阻塞 队列  CSA  保证线程安全
    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<String> blockingQueue=new ArrayBlockingQueue<>(3);//队列  参数表示 有界的 界限大小 3
        System.out.println(blockingQueue.offer("a", 2, TimeUnit.SECONDS));//返回Boolean
        blockingQueue.offer("b",2,TimeUnit.SECONDS);
        blockingQueue.offer("c",2,TimeUnit.SECONDS);
        blockingQueue.offer("d",2,TimeUnit.SECONDS);//阻塞两秒 插入不进 则跳过
        System.out.println("-------------->");
        System.out.println(blockingQueue.poll(2, TimeUnit.SECONDS));
        System.out.println(blockingQueue.poll(2, TimeUnit.SECONDS));
        System.out.println(blockingQueue.poll(2, TimeUnit.SECONDS));
        System.out.println(blockingQueue.poll(2, TimeUnit.SECONDS));//阻塞时间  一到 则 继续执行
        System.out.println("-------------->");


    }
    public static  void show2() throws InterruptedException { //
        BlockingQueue<String> blockingQueue=new ArrayBlockingQueue<>(3);//队列  参数表示 有界的 界限大小 3
        blockingQueue.put("a");//阻塞
        blockingQueue.put("b");
        blockingQueue.put("c");

        System.out.println("========================");
        System.out.println(blockingQueue.take());//返回  队列中的元素
        System.out.println(blockingQueue.take());
        blockingQueue.put("d");
        System.out.println(blockingQueue.take());

    }
    public static  void show1(){   //offer 与 poll
        BlockingQueue<String> blockingQueue=new ArrayBlockingQueue<>(3);//队列  参数表示 有界的 界限大小 3
        System.out.println(blockingQueue.offer("a"));//插入失败  返回false
        System.out.println(blockingQueue.offer("b"));
        System.out.println(blockingQueue.offer("c"));
        System.out.println(blockingQueue.offer("d"));
        System.out.println(blockingQueue.peek());//获取队首元素
        System.out.println(blockingQueue.poll());//获取元素
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());//越界 则返回空值

    }

    public  static  void show(){//add remove 都会报异常
        BlockingQueue<String> blockingQueue=new ArrayBlockingQueue<>(3);//队列  参数表示 有界的 界限大小 3
        System.out.println(blockingQueue.add("a"));  //操作界限个数  直接报异常
        System.out.println(blockingQueue.add("b"));
        System.out.println(blockingQueue.add("c"));
//        System.out.println(blockingQueue.add("d"));
        System.out.println(blockingQueue.element());//取出队首元素
        System.out.println(blockingQueue.remove());//移除一个元素
        System.out.println(blockingQueue.add("d"));
        System.out.println(blockingQueue.remove());
    }
}
