package com.xu.juc.method;

import lombok.extern.slf4j.Slf4j;

/**
 * @Author 徐志斌
 * @Date: 2023/10/2 17:05
 * @Version 1.0
 * @Description: 查看Thread状态
 */
@Slf4j
public class MethodTest2 {
    /**
     * NEW
     * RUNNABLE
     * 17:12:53.343 [Thread-0] DEBUG com.xu.juc.method.MethodTest2 - running......
     */
    public static void main(String[] args) {
        /**
         *
         */
        Thread t = new Thread() {
            @Override
            public void run() {
                log.debug("running......");
            }
        };
        System.out.println(t.getState());
        t.start();
        // RUNNABLE状态不能调用start：Exception in thread "main" java.lang.IllegalThreadStateException
        // t.start();
        System.out.println(t.getState());
    }
}
