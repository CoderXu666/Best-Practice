package com.xu.juc.createthread;

import lombok.extern.slf4j.Slf4j;

/**
 * @Author 徐志斌
 * @Date: 2023/10/2 12:13
 * @Version 1.0
 * @Description: 实现Runnable接口方式（任务和线程分离，并且可以配合线程池使用。推荐）
 */
@Slf4j
public class CreateWay2 {
    /**
     * 12:38:11.601 [t2] DEBUG com.xu.juc.createthread.CreateWay2 - running
     * 12:38:11.601 [lambda t2] DEBUG com.xu.juc.createthread.CreateWay2 - lambda thread running
     */
    public static void main(String[] args) {
        Runnable r = new Runnable() {
            @Override
            public void run() {
                log.debug("running");
            }
        };
        Thread t = new Thread(r, "t2");
        t.start();

        // 针对Runnable函数式接口，推荐使用Lambda
        Runnable r1 = () -> log.debug("lambda thread running");
        Thread t2 = new Thread(r1, "lambda t2");
        t2.start();
    }
}
