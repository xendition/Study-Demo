package com.michael.demo.thread;

import java.io.IOException;
import java.util.concurrent.*;

/**
 * @author Michael
 */
public class CallableDemo {

    public static void main(String[] args) throws IOException, ExecutionException, InterruptedException {

        MyThread myThread = new MyThread();

        MyThreadFactory factory = new MyThreadFactory();

        MyIgnorePolicy ignorePolicy = new MyIgnorePolicy();

        ThreadPoolExecutor executor = new ThreadPoolExecutor(1, 10,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>(2), factory, ignorePolicy);

        executor.prestartAllCoreThreads(); // 预启动所有核心线程

        for (int i = 0; i < 10; i++) {
            FutureTask<User> futureTask = new FutureTask<>(myThread);
            executor.execute(futureTask);

            User user = futureTask.get();

            System.out.println("xx-" + Thread.currentThread().getId() + user);
        }

        System.in.read();
        executor.shutdown();
        System.out.println("线程池关闭");

//        User result = futureTask.get();

//        System.out.println("xxxxxxxxx\n" + result);

    }

    public static class MyThreadFactory implements ThreadFactory {
        @Override
        public Thread newThread(Runnable r) {

            Thread thread = new Thread(r, "我的线程");
            System.out.println(thread.getName() + thread.getId() + " has bean created");
            return thread;
        }
    }

    public static class MyIgnorePolicy implements RejectedExecutionHandler {
        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {

            doLog(r, executor);
        }

        /**
         * 拒绝日志
         */
        private void doLog(Runnable r, ThreadPoolExecutor executor) {
            System.out.println(r.toString() + "rejected");
            System.out.println("completedcount : " + executor.getCompletedTaskCount());
        }
    }

    public static class MyThread implements Callable<User> {

        @Override
        public User call() throws Exception {

            for (int i = 0; i < 10; i++) {
                Thread.sleep(100L);
                System.out.println(Thread.currentThread().getId() + " 程序运行中 i = " + i);
            }
            return new User("执行完毕", (int) Thread.currentThread().getId());
        }
    }

    public static class User {

        private String name;
        private Integer age;

        public User(String name, Integer age) {
            this.name = name;
            this.age = age;
        }

        @Override
        public String toString() {
            return "{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    '}';
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getAge() {
            return age;
        }

        public void setAge(Integer age) {
            this.age = age;
        }
    }
}
