package com.dsa.misc.concurrency.test.comfuture;

import com.dsa.Main;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import static java.lang.System.out;

public class ComFutureTest {

    private static final ExecutorService EXECUTOR_SERVICE_FOR_X_ACTIVITIES = ComFutureConcurrencyUtil.EXECUTOR_SERVICE_FOR_X;

    private static final Logger LOGGER = Logger.getLogger(Main.class.getSimpleName());

    private static final int KYC_CASE_LIST_RETRIEVAL_SQL_TIMEOUT = 5;
    private static final int KYC_CASE_DETAILS_RETRIEVAL_SQL_TIMEOUT = 5;

    public static void main(String[] args) {

        out.println();
        Runnable sup = () -> {
            Thread thread = Thread.currentThread();
            out.println("Executing on thread: " + thread.getName() + " " + thread.isDaemon());

            try {
                Thread.sleep(40L);
            } catch (InterruptedException in) {
                thread.interrupt();
                out.println(in.getMessage());
            }
        };
        CompletableFuture<Void> task1 = CompletableFuture.runAsync(sup, EXECUTOR_SERVICE_FOR_X_ACTIVITIES);
        CompletableFuture<Void> task2 = CompletableFuture.runAsync(sup, EXECUTOR_SERVICE_FOR_X_ACTIVITIES);
        CompletableFuture<Void> task3 = CompletableFuture.runAsync(sup, EXECUTOR_SERVICE_FOR_X_ACTIVITIES);
        CompletableFuture<Void> task4 = CompletableFuture.runAsync(sup, EXECUTOR_SERVICE_FOR_X_ACTIVITIES);
        CompletableFuture<Void> task5 = CompletableFuture.runAsync(sup, EXECUTOR_SERVICE_FOR_X_ACTIVITIES);
        CompletableFuture<Void> task6 = CompletableFuture.runAsync(sup, EXECUTOR_SERVICE_FOR_X_ACTIVITIES);

        //Barrier
        try {
            CompletableFuture.allOf(
                            task1,
                            task2,
                            task3,
                            task4,
                            task5,
                            task6
                    )
                    .orTimeout(2, TimeUnit.SECONDS)
                    .join();


            //Combining the result into one
            task1.get();
            task2.get();
            task3.get();
            task4.get();
            task5.get();
            task6.get();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            out.println(e.getMessage());
        } catch (ExecutionException e) {
            out.println(e.getMessage());
        }
    }

    public void destroy() {
        try {
            EXECUTOR_SERVICE_FOR_X_ACTIVITIES.shutdown();
            if (!EXECUTOR_SERVICE_FOR_X_ACTIVITIES.isShutdown())
                EXECUTOR_SERVICE_FOR_X_ACTIVITIES.shutdownNow();

        } catch (Exception ex) {
            out.println("Unable to shutdown EXECUTOR_SERVICE_FOR_KYC_LIST_ITEM. Error: " + ex.getMessage());
        }
    }
}
