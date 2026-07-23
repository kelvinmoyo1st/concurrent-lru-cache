package com.kelvinmoyo1st.concurrent_lru_cache;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
class CacheEntry {

    @Id
    private String key;

    private String value;

    protected CacheEntry() {
    }

    CacheEntry(String key, String value) {
        this.key = key;
        this.value = value;
    }

    String getKey() {
        return key;
    }

    String getValue() {
        return value;
    }

    void setValue(String value) {
        this.value = value;
    }
}
