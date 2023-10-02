package com.xu.juc.createthread;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @Author 徐志斌
 * @Date: 2023/10/2 12:27
 * @Version 1.0
 * @Description: 实现Callable接口方式（可以拿到返回值）
 */
@Slf4j
public class CreateWay3 {
    /**
     * 12:35:43.260 [t1] DEBUG com.xu.juc.createthread.CreateWay3 - running...
     * 12:35:44.274 [main] DEBUG com.xu.juc.createthread.CreateWay3 - 100
     */
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask<Integer> task = new FutureTask<>(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                log.debug("running...");
                Thread.sleep(1000); // 休息1s
                return 100;
            }
        });
        Thread t1 = new Thread(task, "t1");
        t1.start();

        // 主线程获取线程执行结果（阻塞等待1s）
        log.debug("{}", task.get());
    }
}
