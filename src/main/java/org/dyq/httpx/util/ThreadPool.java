package org.dyq.httpx.util;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.function.Supplier;

public final class ThreadPool {
    private static final ThreadFactory threadFactory = Thread.ofVirtual()
            .name("httpx-", 0).factory();
    private static final ExecutorService global = Executors.newThreadPerTaskExecutor(threadFactory);

    public static ExecutorService global() {
        return global;
    }

    public static <U> CompletableFuture<U> supplyAsync(Supplier<U> supplier) {
        return CompletableFuture.supplyAsync(supplier, global());
    }
}
