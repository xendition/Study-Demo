package com.michael.demo.io.bio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.Charset;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * BIO 服务端程序 - 多线程方式
 *
 * @author Michael
 */
public class BioServerMuitiThread {

    private static int port = 9090;
    private static Charset charset = Charset.forName("UTF-8");

    public static void main(String[] args) {

        try (
                ServerSocket serverSocket = new ServerSocket(port)
        ) {
            System.out.println("服务端已开启,端口号为 " + port);

            while (true) {

                try {
                    // 阻塞式等待客户端连接,打断点调试可以发现,如果没有客户端连接,主线程会被阻塞,不往下进行
                    Socket socket = serverSocket.accept();

                    new Thread(new SocketProcess(socket)).start();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

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
