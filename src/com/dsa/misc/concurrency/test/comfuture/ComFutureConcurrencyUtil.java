package com.dsa.misc.concurrency.test.comfuture;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class ComFutureConcurrencyUtil {

    public static final ExecutorService EXECUTOR_SERVICE_FOR_X;

    private static final int BUFFER_QUEUE_CAPACITY = 100;

    private static final int MAX_NUMBER_OF_THREAD_FOR_X_ACTIVITIES_CONCURRENTLY = Math.max(Runtime.getRuntime().availableProcessors(), 4);

    static {

        //We can't use CallerRunsPolicy (setting queue to a low number) with Completable Future unless we alternate to other libraries like Observable
        //Completable Future works well with daemon threads.
        int noOfCores = MAX_NUMBER_OF_THREAD_FOR_X_ACTIVITIES_CONCURRENTLY;
        EXECUTOR_SERVICE_FOR_X = new ThreadPoolExecutor(
                1,//noOfCores,
                1,//noOfCores + 5,
                50,
                TimeUnit.SECONDS,
                new SynchronousQueue<>(true),
                new DefaultThreadFactory(),
                new ThreadPoolExecutor.CallerRunsPolicy()
        );
    }

    private ComFutureConcurrencyUtil() {
    }

    private static class DefaultThreadFactory implements ThreadFactory {
        private static final AtomicInteger poolNumber = new AtomicInteger(1);
        private final ThreadGroup group;
        private final AtomicInteger threadNumber = new AtomicInteger(1);
        private final String namePrefix;

        DefaultThreadFactory() {
            group = Thread.currentThread().getThreadGroup();
            namePrefix = "X_ACTIVITIES_POOL-" + poolNumber.getAndIncrement() + "-thread-";
        }

        public Thread newThread(Runnable r) {
            Thread t = new Thread(group, r, namePrefix + threadNumber.getAndIncrement(), 0);

            //It seems Completable Future works well with daemon threads.
            t.setDaemon(true);

            if (t.getPriority() != Thread.NORM_PRIORITY)
                t.setPriority(Thread.NORM_PRIORITY);

            return t;
        }
    }
}
