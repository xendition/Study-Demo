package com.michael.demo.thread;

/**
 * 线程练习1 - 多线程加减平衡
 *
 * @author Michael
 */
public class ThreadCombatDemo1 {

    public static void main(String[] args) {

        Resource resource = new Resource();
        ThreadAdd add = new ThreadAdd(resource);
        ThreadSub sub = new ThreadSub(resource);

        new Thread(add, "add1").start();
        new Thread(add, "add2").start();
        new Thread(add, "add3").start();
        new Thread(add, "add4").start();

        new Thread(sub, "sub1").start();
        new Thread(sub, "sub2").start();
        new Thread(sub, "sub3").start();
        new Thread(sub, "sub4").start();

    }

    public static class Resource {
        private int num = 0;
        private boolean flag = true;

        public synchronized void add() throws InterruptedException {

            // TODO  多线程wait() 必需在 while 里面处理
            while (!this.flag) {
                super.wait();
            }
            Thread.sleep(10L);
            this.num++;
            System.out.println(" + " + Thread.currentThread().getName() + " num = " + num);
            this.flag = false;
            super.notifyAll();
        }

        public synchronized void sub() throws InterruptedException {
            while (this.flag) {
                super.wait();
            }
            Thread.sleep(100L);
            this.num--;
            System.out.println(" - " + Thread.currentThread().getName() + " num = " + num);
            this.flag = true;
            super.notifyAll();
        }
    }

    public static class ThreadAdd implements Runnable {

        private Resource resource;

        public ThreadAdd(Resource resource) {
            this.resource = resource;
        }

        @Override
        public void run() {

            for (int i = 0; i < 100; i++) {
                try {
                    this.resource.add();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static class ThreadSub implements Runnable {

        private Resource resource;

        public ThreadSub(Resource resource) {
            this.resource = resource;
        }

        @Override
        public void run() {

            for (int i = 0; i < 100; i++) {
                try {
                    this.resource.sub();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}






