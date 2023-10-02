package com.xu.juc.frame;

/**
 * @Author 徐志斌
 * @Date: 2023/10/2 15:43
 * @Version 1.0
 * -------------------------------------------
 * 栈：线程启动后，虚拟机为其分配的内存空间
 * 栈帧：线程调用方法时所占用的内存
 * 活动栈帧：当前正在执行那个方法占用的内存
 * ------------------------------------------
 * 该案例3个栈帧：主方法，method1，method2
 * 通过Debug方式可以看看
 */
public class FrameTest {
    public static void main(String[] args) {
        method1(10);
    }

    public static void method1(int x) {
        int y = x + 1;
        Object m = method2();
    }

    private static Object method2() {
        return new Object();
    }
}
