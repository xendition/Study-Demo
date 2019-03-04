package com.michael.demo.io.bio;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.Charset;

/**
 * BIO 服务端程序 - 单线程
 *
 * @author Michael
 */
public class BioServerSingleThread {

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

                    // 接收客户端发送过来的信息
                    BufferedReader bufferedReader = new BufferedReader(
                            new InputStreamReader(socket.getInputStream(), charset)
                    );
//                    System.out.println("有客户端来连接服务器了,服务器地址为 " + socket.getInetAddress().getHostAddress());

                    String line;
                    while ((line = bufferedReader.readLine()) != null) {
                        System.out.println(line);
                        System.out.println(System.currentTimeMillis());
                    }
                    socket.close();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
