package com.xu.juc.deamon;

import lombok.extern.slf4j.Slf4j;

/**
 * @Author 徐志斌
 * @Date: 2023/10/5 10:14
 * @Version 1.0
 * @Description: 守护线程
 */
@Slf4j
public class DaemonTest {
    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(() -> {
            while (true) {
                if (Thread.currentThread().isInterrupted()) {
                    break;
                }
            }
            log.debug("守护线程结束....");
        });
        // t.setDaemon(true); // 设置为守护线程
        t.start();

        // 其他线程结束,守护线程也会销毁
        Thread.sleep(1000);
        log.debug("主线程结束....");
    }
}
