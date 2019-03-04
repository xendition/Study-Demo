package com.michael.demo.io.bio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.Charset;
import java.util.concurrent.*;

/**
 * BIO 服务端程序 - 使用线程池(BIO效率最高的方式)
 *
 * @author Michael
 */
public class BioServerThreadPool {

    public static void main(String[] args) {
        int port = 9090;
        int threadPoolSize = 100;

        //构造一个线程池
        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(threadPoolSize, threadPoolSize,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>(), new ThreadPoolExecutor.DiscardOldestPolicy());

        try (
                ServerSocket serverSocket = new ServerSocket(port)
        ) {
            while (true) {
                Socket socket = serverSocket.accept();
                threadPool.execute(new SocketProcess(socket));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        threadPool.shutdown();
    }

    private static Charset charset = Charset.forName("UTF-8");

    static class SocketProcess implements Runnable {

        Socket socket;

        public SocketProcess(Socket socket) {
            super();
            this.socket = socket;
        }

        @Override
        public void run() {

            try (
                    BufferedReader bufferedReader = new BufferedReader(
                            new InputStreamReader(socket.getInputStream(), charset)
                    )
            ) {
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    System.out.println("当前处理的线程为" + Thread.currentThread().getId());
                    System.out.println(line);
                    System.out.println(System.currentTimeMillis());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
