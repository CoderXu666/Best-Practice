package com.xu.juc.createthread;

import lombok.extern.slf4j.Slf4j;

/**
 * @Author 徐志斌
 * @Date: 2023/10/2 12:13
 * @Version 1.0
 * @Description: 实现Runnable接口方式
 */
@Slf4j
public class CreateWay2 {
    public static void main(String[] args) {
        /**
         * [t2] DEBUG com.xu.juc.createthread.CreateWay2 - running
         */
        Runnable r = new Runnable() {
            @Override
            public void run() {
                log.debug("running");
            }
        };
        Thread t = new Thread(r, "t2");
        t.start();
    }
}
