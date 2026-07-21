package com.kelvinmoyo1st.concurrent_lru_cache;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.*;

import static org.junit.jupiter.api.Assertions.*;

class ConcurrentLoadTest {

    @Test
    @Timeout(30)
    void concurrentPutsAndGetsCorruptTheCache() throws InterruptedException {
        LruCache<Integer, Integer> cache = new LruCache<>(16);
        int threadCount = 20;
        int opsPerThread = 2000;

        ExecutorService pool = Executors.newFixedThreadPool(threadCount);
        CountDownLatch startLatch = new CountDownLatch(1);
        List<Throwable> errors = Collections.synchronizedList(new ArrayList<>());

        for (int t = 0; t < threadCount; t++) {
            pool.submit(() -> {
                try {
                    startLatch.await();
                    for (int i = 0; i < opsPerThread; i++) {
                        int key = ThreadLocalRandom.current().nextInt(50);
                        cache.put(key, key);
                        cache.get(key);
                    }
                } catch (Throwable e) {
                    errors.add(e);
                }
            });
        }

        startLatch.countDown();
        pool.shutdown();
        pool.awaitTermination(25, TimeUnit.SECONDS);

        assertTrue(errors.isEmpty(), "Exceptions occurred during concurrent access: " + errors);
        assertEquals(cache.size(), cache.listLength(),
            "Map size and linked-list length disagree — the list is corrupted");
    }
}
