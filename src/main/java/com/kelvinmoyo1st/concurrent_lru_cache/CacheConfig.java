package com.kelvinmoyo1st.concurrent_lru_cache;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class CacheConfig {

    @Bean
    LruCache<String, String> lruCache(@Value("${cache.capacity:100}") int capacity) {
        return new LruCache<>(capacity);
    }
}
