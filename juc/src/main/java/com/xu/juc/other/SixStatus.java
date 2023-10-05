package com.xu.juc.other;

import lombok.extern.slf4j.Slf4j;

/**
 * @Author 徐志斌
 * @Date: 2023/10/5 10:40
 * @Version 1.0
 * @Description: Java层面:6种线程状态
 */
@Slf4j
public class SixStatus {
    /**
     * 14:17:58.379 [main] DEBUG com.xu.juc.other.SixStatus - t1线程状态:-------------------NEW
     * 14:17:58.384 [main] DEBUG com.xu.juc.other.SixStatus - t2线程状态:-------------------RUNNABLE
     * 14:17:58.385 [main] DEBUG com.xu.juc.other.SixStatus - t3线程状态:-------------------TERMINATED
     * 14:17:58.385 [main] DEBUG com.xu.juc.other.SixStatus - t4线程状态:-------------------TIMED_WAITING
     * 14:17:58.385 [main] DEBUG com.xu.juc.other.SixStatus - t5线程状态:-------------------WAITING
     * 14:17:58.386 [main] DEBUG com.xu.juc.other.SixStatus - t6线程状态:-------------------BLOCKED
     */
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread("t1") {
            @Override
            public void run() {
                log.debug("running....");
            }
        };

        Thread t2 = new Thread("t2") {
            @Override
            public void run() {
                while (true) {
                }
            }
        };
        t2.start();

        Thread t3 = new Thread("t3") {
            @Override
            public void run() {
            }
        };
        t3.start();

        Thread t4 = new Thread("t4") {
            @Override
            public void run() {
                synchronized (SixStatus.class) {
                    try {
                        Thread.sleep(1000000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        };
        t4.start();

        Thread t5 = new Thread("t5") {
            @Override
            public void run() {
                try {
                    t2.join();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        };
        t5.start();

        Thread t6 = new Thread("t6") {
            @Override
            public void run() {
                // t4先拿锁,t6拿不到了
                synchronized (SixStatus.class) {
                    try {
                        Thread.sleep(1000000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        };
        t6.start();

        Thread.sleep(500);
        log.debug("t1线程状态:{}", "-------------------" + t1.getState()); // NEW
        log.debug("t2线程状态:{}", "-------------------" + t2.getState()); // RUNNABLE
        log.debug("t3线程状态:{}", "-------------------" + t3.getState()); // TERMINATED
        log.debug("t4线程状态:{}", "-------------------" + t4.getState()); // TIMED_WAITING
        log.debug("t5线程状态:{}", "-------------------" + t5.getState()); // WAITING
        log.debug("t6线程状态:{}", "-------------------" + t6.getState()); // BLOCKED
    }
}
