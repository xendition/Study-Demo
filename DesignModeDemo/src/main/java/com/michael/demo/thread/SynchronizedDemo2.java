package com.michael.demo.thread;

/**
 * @author Michael
 */
public class SynchronizedDemo2 {
    public static void main(String[] argv) {
        SynchronizedDemo2 syn1 = new SynchronizedDemo2();
        new TestThread1(syn1).start();
        new TestThread(syn1).start();
    }

    //循环方法
    public void loop() {
        System.out.println("thread name：" + Thread.currentThread().getName());
        synchronized (this) {
            System.out.println("thread name："
                    + Thread.currentThread().getName() + " 开始执行循环");
            for (int i = 0; i < 10; i++) {
                try {
                    Thread.sleep(100L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("thread name："
                        + Thread.currentThread().getName() + " i=" + i);
            }
            System.out.println("thread name："
                    + Thread.currentThread().getName() + " 执行循环结束");
        }
    }

    //循环方法1
    public synchronized void loop1() {
        System.out.println("thread name：" + Thread.currentThread().getName());

        System.out.println("thread name：" + Thread.currentThread().getName()
                + " 开始执行循环");
        for (int i = 0; i < 10; i++) {

            try {
                Thread.sleep(100L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("thread name："
                    + Thread.currentThread().getName() + " i=" + i);
        }
        System.out.println("thread name：" + Thread.currentThread().getName()
                + " 执行循环结束");
    }
}

//测试线程
class TestThread extends Thread {
    private SynchronizedDemo2 syn;

    public TestThread(SynchronizedDemo2 syn) {
        super();
        this.syn = syn;
    }

    @Override
    public void run() {
        syn.loop();
    }

}

//测试线程1
class TestThread1 extends Thread {
    private SynchronizedDemo2 syn;

    public TestThread1(SynchronizedDemo2 syn) {
        super();
        this.syn = syn;
    }

    @Override
    public void run() {

        syn.loop1();
    }

}

