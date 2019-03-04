package com.michael.demo.io.bio;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.Charset;

/**
 * BIO 模拟客户端
 *
 * @author Michael
 */
public class BioClient {

    private static String host = "localhost";
    private static int port = 9090;

    private static Charset charset = Charset.forName("UTF-8");

    public static void main(String[] args) {

        long start = System.currentTimeMillis();
        for (int i = 0; i < 20000; i++) {
            new Thread(new SocketProcess()).start();
        }
        long end = System.currentTimeMillis();

        System.out.println(end - start);
    }

    static class SocketProcess implements Runnable {

        @Override
        public void run() {
            try (
                    Socket socket = new Socket(host, port);
                    // 客户端发送的信息
                    OutputStream out = socket.getOutputStream()
            ) {
                String line = "当前为" + Thread.currentThread().getId();

                out.write(line.getBytes(charset));

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
