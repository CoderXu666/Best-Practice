package com.xu.juc.method;

import lombok.extern.slf4j.Slf4j;

/**
 * @Author 徐志斌
 * @Date: 2023/10/2 17:05
 * @Version 1.0
 * ------------------------------------
 * 打断正常运行线程：运行中的线程被打断后，isInterrupted = true
 */
@Slf4j
public class MethodTest7 {
    public static void main(String[] args) throws Exception {
        Thread t1 = new Thread(() -> {
            while (true) {
                boolean interrupted = Thread.currentThread().isInterrupted();
                if (interrupted) {
                    log.debug("线程被打断了....退出循环");
                    break;
                }
            }
        });
        t1.start();

        Thread.sleep(500);
        t1.interrupt();
    }
}
