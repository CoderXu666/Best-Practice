package com.xu.juc.createthread;

import lombok.extern.slf4j.Slf4j;

/**
 * @Author 徐志斌
 * @Date: 2023/10/2 11:58
 * @Version 1.0
 * @Description: 继承Thread类方式
 */
@Slf4j
public class CreateWay1 {
    public static void main(String[] args) {
        /**
         * [t1] DEBUG com.xu.juc.createThread.CreateThread - t1 running
         * [main] DEBUG com.xu.juc.createThread.CreateThread - main running
         */
        Thread t1 = new Thread("t1") {
            @Override
            public void run() {
                log.debug("t1 running");
            }
        };
        // t1.setName("t1"); // 设置线程名称
        t1.start(); // 启动t1线程
        log.debug("main running"); // 主线程打印日志
    }
}
