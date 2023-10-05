package com.xu.juc.status;

import lombok.extern.slf4j.Slf4j;

/**
 * @Author 徐志斌
 * @Date: 2023/10/5 10:40
 * @Version 1.0
 * @Description: Java层面6种线程状态
 */
@Slf4j
public class SixStatus {
    public static void main(String[] args) {
        /**
         *
         */
        Thread t1 = new Thread("t1") {
            @Override
            public void run() {
                log.debug("running....");
            }
        };
        /**
         *
         */
        Thread t2 = new Thread("t2") {
            @Override
            public void run() {
                log.debug("running....");
            }
        };
        /**
         *
         */
        Thread t3 = new Thread("t3") {
            @Override
            public void run() {
                log.debug("running....");
            }
        };
        /**
         *
         */
        Thread t4 = new Thread("t4") {
            @Override
            public void run() {
                log.debug("running....");
            }
        };
        /**
         *
         */
        Thread t5 = new Thread("t5") {
            @Override
            public void run() {
                log.debug("running....");
            }
        };
        /**
         *
         */
        Thread t6 = new Thread("t6") {
            @Override
            public void run() {
                log.debug("running....");
            }
        };
    }
}
