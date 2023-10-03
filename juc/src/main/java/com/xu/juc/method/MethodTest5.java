package com.xu.juc.method;

import lombok.extern.slf4j.Slf4j;

/**
 * @Author 徐志斌
 * @Date: 2023/10/2 17:05
 * @Version 1.0
 * yield：让出CPU使用权（如果没有其他线程使用CPU，则不让），线程从Running ——> Runnable就绪状态
 * 线程优先级：提示调度器优先分配给哪个线程执行，CPU空闲则几乎无用
 */
@Slf4j
public class MethodTest5 {
    /**
     * 测试结果不明显原因：电脑本身就是多核CPU，本质还是需要任务调度器决定CPU使用权
     */
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread("t1") {
            @Override
            public void run() {
                int count = 0;
                while (true) {
                    System.out.println("线程1：" + count++);
                }
            }
        };
        Thread t2 = new Thread("t2") {
            @Override
            public void run() {
                int count = 0;
                // Thread.yield(); // 线程2让出CPU执行权
                while (true) {
                    System.out.println("                         线程2：" + count++);
                }
            }
        };

        // 设置优先级
        t1.setPriority(Thread.MIN_PRIORITY);
        t2.setPriority(Thread.MAX_PRIORITY);

        // 开启线程
        t1.start();
        t2.start();
    }
}
