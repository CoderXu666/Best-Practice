package com.xu.juc.method;

import lombok.extern.slf4j.Slf4j;

/**
 * @Author 徐志斌
 * @Date: 2023/10/2 17:05
 * @Version 1.0
 * @Description: sleep
 */
@Slf4j
public class MethodTest3 {
    /**
     * 17:22:00.723 [main] DEBUG com.xu.juc.method.MethodTest3 - t1线程状态：RUNNABLE
     * 17:22:01.243 [main] DEBUG com.xu.juc.method.MethodTest3 - t1线程状态：TIMED_WAITING
     */
    public static void main(String[] args) throws InterruptedException {
        /**
         * sleep方法
         */
        Thread t1 = new Thread() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        };
        t1.start();
        log.debug("t1线程状态：{}", t1.getState());
        Thread.sleep(500); // 不加这行，线程状态为Runnable
        log.debug("t1线程状态：{}", t1.getState());
    }
}
