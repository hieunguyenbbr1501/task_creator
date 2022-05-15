package com.example.demo.cache;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@Configuration
@EnableScheduling
public class TaskCache {
    private Cache<Integer, Integer> dailyTaskCount;

    @PostConstruct
    public void init() {
        dailyTaskCount = Caffeine.newBuilder().maximumSize(Long.MAX_VALUE).build();
    }

    public void increaseTaskCount(int userId) {
        Integer currentCount = dailyTaskCount.getIfPresent(userId);
        if (currentCount == null) {
            currentCount = 0;
        }
        dailyTaskCount.put(userId, currentCount + 1);
    }

    public boolean validateMaximum(int userId, int maximum) {
        Integer currentCount = dailyTaskCount.getIfPresent(userId);
        return currentCount == null || currentCount < maximum;
    }

    // The cache is reset after 0 A.M each day
    @Scheduled(cron = "0 0 0 * * *")
    public void resetCache() {
        dailyTaskCount.invalidateAll();
    }

}
