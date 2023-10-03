package com.xu.juc.method;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * @Author 徐志斌
 * @Date: 2023/10/2 17:05
 * @Version 1.0
 */
@Slf4j
public class MethodTest6 {
    /**
     * 不使用join就是0，使用join可以同步等待到结果
     */
    static int count = 0;

    public static void main(String[] args) throws Exception {
        Thread t1 = new Thread("t1") {
            @Override
            public void run() {
                log.debug("睡眠开始......");
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                log.debug("睡眠结束......");
                count = 10;
            }
        };
        t1.start();
        // t1.join(1000); // 等待线程返回结果，join可以设置最大同步等待时间（1000毫秒是0，1050毫秒是10）
        log.debug("结果为：{}", count);
    }
}
