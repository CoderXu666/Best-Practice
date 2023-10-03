package com.xu.juc.method;

import lombok.extern.slf4j.Slf4j;

/**
 * @Author 徐志斌
 * @Date: 2023/10/2 17:05
 * @Version 1.0
 * @Description: interrupt打断睡眠线程，打断后醒来的线程未必会立即执行，取决于任务调度器的时间片
 */
@Slf4j
public class MethodTest4 {
    /**
     * 10:57:58.151 [t1] DEBUG com.xu.juc.method.MethodTest4 - t1线程进入睡眠状态....
     * 10:57:59.150 [main] DEBUG com.xu.juc.method.MethodTest4 - t1的打断操作开始.....
     * 10:57:59.150 [t1] DEBUG com.xu.juc.method.MethodTest4 - t1醒来....
     * java.lang.InterruptedException: sleep interrupted
     * at java.base/java.lang.Thread.sleep(Native Method)
     * at com.xu.juc.method.MethodTest4$1.run(MethodTest4.java:24)
     */
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread("t1") {
            @Override
            public void run() {
                log.debug("t1线程进入睡眠状态....");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    log.debug("t1醒来....");
                    e.printStackTrace();
                }
            }
        };
        t1.start();

        // 主线程睡眠1s后，打断唤醒t1线程
        // TimeUnit.SECONDS.sleep(1);
        Thread.sleep(1000);
        log.debug("t1的打断操作开始.....");
        t1.interrupt();
    }
}
