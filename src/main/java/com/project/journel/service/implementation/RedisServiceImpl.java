package com.project.journel.service.implementation;

import java.util.List;
import java.util.Set;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.project.journel.service.RedisService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RedisServiceImpl implements RedisService {

  private final RedisTemplate<String, String> redisTemplate;

  @Override
  public void addEntryToTagForUser(Long userId, String tagName, Long entryId) {
    String key = "user:" + userId + ":tag:" + tagName;
    redisTemplate.opsForList().rightPush(key, String.valueOf(entryId));
  }

  @Override
  public List<String> getEntriesByTagForUser(Long userId, String tagName) {
    String key = "user:" + userId + ":tag:" + tagName;
    return redisTemplate.opsForList().range(key, 0, -1); // Retrieve entries for this user and tag
  }

  @Override
  public void deleteEntry(Long userId, Long entryId) {
    String pattern = "user:" + userId + ":tag:*";

    Set<String> keys = redisTemplate.keys(pattern);

    if (keys != null) {
      for (String key : keys) {
        List<String> values = redisTemplate.opsForList().range(key, 0, -1);
        System.out.println("Key: " + key + ", Values: " + values);

        // Remove all occurrences of entryId from the Redis list for this key
        Long removedCount = redisTemplate.opsForList().remove(key, 0, String.valueOf(entryId));
        System.out.println("Removed " + removedCount + " occurrences of entryId " + entryId + " from key " + key);
      }
    }
  }

}
