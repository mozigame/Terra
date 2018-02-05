package com.babel.terra.util;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * User: joey
 * Date: 2018/2/2
 * Time: 2:01
 */
public class ThreadPoolFactory {

    public static ExecutorService THREAD_POOL = new ThreadPoolExecutor(7, 20, 10, TimeUnit.SECONDS,
            new ArrayBlockingQueue<>(100), new ThreadPoolExecutor.CallerRunsPolicy());
}
