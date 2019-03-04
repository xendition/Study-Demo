package com.michael.demo.thread;

import java.util.concurrent.locks.ReentrantLock;

public class LockDemo {

    private ReentrantLock lock = new ReentrantLock();//乐观锁

    public static void main(String[] argv) {
        LockDemo syn1 = new LockDemo();
        new TestThread3(syn1).start();
        new TestThread3(syn1).start();
    }

    // 循环方法
    public void loop() {
        System.out.println("thread name：" + Thread.currentThread().getName());
        lock.lock(); // 加锁
        System.out.println("thread name：" + Thread.currentThread().getName()
                + " 开始执行循环");
        for (int i = 0; i < 10; i++) {
            System.out.println("thread name："
                    + Thread.currentThread().getName() + " i=" + i);
        }
        System.out.println("thread name：" + Thread.currentThread().getName()
                + " 执行循环结束");

        lock.unlock();//执行完成释放锁

    }
}

// 测试线程
class TestThread3 extends Thread {
    private LockDemo syn;

    public TestThread3(LockDemo syn) {
        super();
        this.syn = syn;
    }

    @Override
    public void run() {
        syn.loop();
    }
}

