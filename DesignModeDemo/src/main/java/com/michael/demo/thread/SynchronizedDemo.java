package com.michael.demo.thread;

/**
 * @author Michael
 */
public class SynchronizedDemo {

    public static void main(String[] args) {

        System.out.println("6 start");

        SynchronizedDemo tc = new SynchronizedDemo();

        for (int i = 0; i < 10; i++) {
            Thread1 t1 = tc.new Thread1(tc, i);
            t1.start();
        }

    }

    class Thread1 extends Thread {
        SynchronizedDemo tc = null;
        int i = 0;

        public Thread1(SynchronizedDemo tc, int i) {
            this.tc = tc;
            this.i = i;
        }

        @Override
        public void run() {
            tc.method1(i);
        }
    }

    public synchronized void method1(int i) {
        System.out.println("method:" + i);
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
