package com.xu.juc.method;

import lombok.extern.slf4j.Slf4j;

/**
 * @Author 徐志斌
 * @Date: 2023/10/2 17:05
 * @Version 1.0
 * @Description: start和run区别
 */
@Slf4j
public class MethodTest1 {
    /**
     * 17:06:54.114 [main] DEBUG com.xu.juc.method.MethodTest1 - running......
     */
    public static void main(String[] args) {
        /**
         * 直接调用run方法，那就不是 t 线程调用，那是主线程调用了
         * 启动线程，一定要用start
         */
        Thread t = new Thread() {
            @Override
            public void run() {
                log.debug("running......");
            }
        };
        t.run();
    }
}
