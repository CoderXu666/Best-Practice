package com.xu.juc.method;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.LockSupport;

/**
 * @Author 徐志斌
 * @Date: 2023/10/2 17:05
 * @Version 1.0
 * ---------------------------------------------------
 * interrupt方法可以打断park状态的线程，使其继续向下运行
 */
@Slf4j
public class MethodTest8 {
    public static void main(String[] args) throws Exception {
        test();
    }

    private static void test() throws InterruptedException {
        Thread t1 = new Thread(() -> {
            LockSupport.park();
            log.debug("打断状态：{}", Thread.currentThread().isInterrupted());
//            Thread.interrupted(); // 这样打断标记为false,又可以park住了
//            LockSupport.park(); // 打断标记为真,park操作会失效
        });
        t1.start();

        Thread.sleep(1000);
        t1.interrupt(); // 打断park住的线程
    }
}
