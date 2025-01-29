package com.project.journel.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.project.journel.entity.EntryJson;

public interface EntryService {

  ResponseEntity<EntryJson> createEntry(Long userId, EntryJson entryJson);

  ResponseEntity<EntryJson> getEntry(Long userId, Long entryId);

  ResponseEntity<String> deleteEntry(Long userId, Long entryId);

  ResponseEntity<List<EntryJson>> getEntriesByTag(Long userId, String tagName);

  ResponseEntity<List<EntryJson>> getAllEntriesByUser(Long userId);
}
