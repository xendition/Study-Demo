package com.michael.demo.thread;

/**
 * 优雅停止线程的方式
 *
 * @author Michael
 */
public class ThreadStopDemo {

    public static boolean flag = true;

    public static void main(String[] args) {

        new Thread(() -> {

            long num = 0;
            while (flag) {
                try {
                    Thread.sleep(10L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + "正在运行，num = " + num++);
            }

        }, "执行线程").start();

        try {
            Thread.sleep(50L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 修改 flag 优雅停止
        flag = false;
    }
}
