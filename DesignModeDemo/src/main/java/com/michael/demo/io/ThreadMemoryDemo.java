package com.michael.demo.io;

import java.util.concurrent.CountDownLatch;

/**
 * 中
 * 线程占用内存大小演示代码
 *
 * <pre>
 *     1. 32位系统中一个线程对象默认最大需要320KB内存，64位最大1M
 *     2. 除了 Thread 对象本身会占用堆内存空间外，还会占用额外的系统内存用于业务对象
 *     3. 过多的线程需要OS频繁切换,大大影响性能,考虑引入线程池(减少线程创建/销毁的时间消费)
 * </pre>
 *
 * @author Michael
 */
public class ThreadMemoryDemo {

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch count = new CountDownLatch(1);

        Thread.sleep(20000L);

        for (int i = 0; i < 5000; i++) {
            new Thread(() -> {
                try {
                    count.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
            System.out.println(i);
        }
    }
}
