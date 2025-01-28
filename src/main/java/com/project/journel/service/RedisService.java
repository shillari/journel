package com.project.journel.service;

import java.util.List;

public interface RedisService {

  public void addEntryToTagForUser(Long userId, String tagName, Long entryId);

  public List<String> getEntriesByTagForUser(Long userId, String tagName);

  public void deleteEntry(Long userId, Long entryId);
}
