package com.project.journel.service;

import org.springframework.http.ResponseEntity;

import com.project.journel.entity.EntryJson;

public interface EntryService {

  ResponseEntity<EntryJson> createEntry(int userId, EntryJson entryJson);
}
