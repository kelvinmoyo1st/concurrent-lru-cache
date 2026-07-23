package com.kelvinmoyo1st.concurrent_lru_cache;

import org.springframework.stereotype.Service;

@Service
class CacheService {

    private final LruCache<String, String> cache;
    private final CacheEntryRepository repository;

    CacheService(LruCache<String, String> cache, CacheEntryRepository repository) {
        this.cache = cache;
        this.repository = repository;
    }

    String get(String key) {
        String cached = cache.get(key);
        if (cached != null) {
            return cached;
        }

        return repository.findById(key)
            .map(entry -> {
                cache.put(key, entry.getValue());
                return entry.getValue();
            })
            .orElse(null);
    }

    void put(String key, String value) {
        repository.save(new CacheEntry(key, value));
        cache.put(key, value);
    }
}
