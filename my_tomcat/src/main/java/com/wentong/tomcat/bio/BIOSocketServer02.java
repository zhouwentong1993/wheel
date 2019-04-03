package com.wentong.tomcat.bio;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 阻塞 io，每一个连接对应着一个线程，容易出现瓶颈，通过线程池实现。
 */
public class BIOSocketServer02 {

    private static ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(10, 20, 40, TimeUnit.SECONDS, new ArrayBlockingQueue<>(10), new ThreadFactoryBuilder()
            .setNameFormat("server-handle-pool-%d").build());

    public static void main(String[] args) throws Exception {

//        printThreadPoolState();
        ServerSocket serverSocket = new ServerSocket(8888);
        while (true) {
            Socket socket = serverSocket.accept();
            threadPoolExecutor.submit(new ServerHandler(socket));
        }
    }

    private static void printThreadPoolState() {
        new Thread(() -> {
            while (true) {
                System.out.println("corePoolSize: " + threadPoolExecutor.getCorePoolSize());
                System.out.println("poolSize: " + threadPoolExecutor.getPoolSize());
                System.out.println("taskCount: " + threadPoolExecutor.getTaskCount());
                System.out.println("queueSize: " + threadPoolExecutor.getQueue().size());
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
