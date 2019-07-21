package com.chinapex.ms.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author leiyuhua
 * 创建一个可缓存线程池
 */

public class ThreadPoolUtils {

    private static final ExecutorService executor;

    static {
        executor = Executors.newFixedThreadPool(10);

    }

    public static ExecutorService getExecutor() {
        return executor;
    }
}
