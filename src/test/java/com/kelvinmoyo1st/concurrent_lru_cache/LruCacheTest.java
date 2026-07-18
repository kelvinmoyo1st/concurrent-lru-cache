package com.kelvinmoyo1st.concurrent_lru_cache;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class LruCacheTest {

    @Test
    void evictsLeastRecentlyUsedWhenFull() {
        LruCache<String, String> cache = new LruCache<>(2);

        cache.put("A", "1");
        cache.put("B", "2");
        cache.get("A");           // A is now most-recently-used, B is LRU
        cache.put("C", "3");      // should evict B, not A

        assertEquals("1", cache.get("A"));   // A survived
        assertNull(cache.get("B"));          // B was evicted
        assertEquals("3", cache.get("C"));   // C is present
    }
}
