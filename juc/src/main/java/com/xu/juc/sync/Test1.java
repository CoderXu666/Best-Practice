package com.xu.juc.sync;

import lombok.extern.slf4j.Slf4j;

/**
 * @Author 徐志斌
 * @Date: 2023/10/5 14:48
 * @Version 1.0
 * @Description: Test1
 */
@Slf4j
public class Test1 {
    static int counter = 0;

    /**
     * 执行结果不一定是0
     * 多线程操作共享资源,会出现 线程安全 问题!
     *
     * 分析:
     * i++和i--翻译成JVM字节码指令其实是4条
     * 由于时间片发生线程上下文切换,导致指令交错,所以结果不对
     *
     */
    public static void main(String[] args) throws InterruptedException {
        // 线程 t1 自增 5000
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 5000; i++) {
                counter++;
            }
        }, "t1");

        // 线程 t2 自减 5000
        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 5000; i++) {
                counter--;
            }
        }, "t2");

        t1.start();
        t2.start();
        t1.join();
        t2.join();
        log.debug("{}", counter);
    }
}
