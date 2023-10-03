package com.xu.juc.demo;

/**
 * @Author 徐志斌
 * @Date: 2023/10/3 11:58
 * @Version 1.0
 * ----------------------------------------------------------------------
 * 使用while true的小技巧：
 * 针对单核CPU的服务器，如果直接用while true，几乎把服务器的CPU能跑满100%了。
 * 通过Thread.sleep方式，可以将CPU执行权放出来，省的一直while true
 * -----------------------------------------------------------------------
 * windows本地测试效果：CPU 43% ——> 7%
 * ----------------------------------------------------------------------
 * wait notify同理，不过那是同步场景。sleep可以用于无需同步场景
 */
public class NoCpu100Percent {
    public static void main(String[] args) throws InterruptedException {
        while (true) {
            // 线程睡眠，让出CPU执行权，睡眠结束回到【就绪】状态
            Thread.sleep(50);
        }
    }
}
