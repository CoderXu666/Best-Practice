package com.xu.juc.sync;

import lombok.extern.slf4j.Slf4j;

/**
 * @Author 徐志斌
 * @Date: 2023/10/5 14:48
 * @Version 1.0
 * @Description: 使用Synchronized解决并发问题
 * ----------------------------------------------
 * 锁对象用this / 类名.class都可以
 * 也可以用synchronized方法,成员方法相当于锁this,静态方法相当于锁Class
 */
@Slf4j
public class Test2 {
    static int counter = 0;
    static Object lock = new Object();

    public static void main(String[] args) throws InterruptedException {
        // 线程 t1 自增 5000
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 5000; i++) {
                synchronized (lock) {
                    counter++;
                }
            }
        }, "t1");

        // 线程 t2 自减 5000
        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 5000; i++) {
                synchronized (lock) {
                    counter--;
                }
            }
        }, "t2");

        t1.start();
        t2.start();
        t1.join();
        t2.join();
        log.debug("{}", counter);
    }
}
