package com.kelvinmoyo1st.concurrent_lru_cache;

import org.springframework.data.jpa.repository.JpaRepository;

interface CacheEntryRepository extends JpaRepository<CacheEntry, String> {
}
