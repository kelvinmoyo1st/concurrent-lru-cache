package com.kelvinmoyo1st.concurrent_lru_cache;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cache")
class CacheController {

    private final LruCache<String, String> cache;

    CacheController(LruCache<String, String> cache) {
        this.cache = cache;
    }

    @GetMapping("/{key}")
    ResponseEntity<String> get(@PathVariable String key) {
        String value = cache.get(key);
        if (value == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(value);
    }

    @PutMapping("/{key}")
    ResponseEntity<Void> put(@PathVariable String key, @RequestBody String value) {
        cache.put(key, value);
        return ResponseEntity.ok().build();
    }
}
