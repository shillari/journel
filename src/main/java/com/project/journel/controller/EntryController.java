package com.project.journel.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.journel.entity.EntryJson;
import com.project.journel.service.EntryService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/entry")
@RequiredArgsConstructor
public class EntryController {

  private final EntryService entryService;

  @PostMapping("")
  public ResponseEntity<EntryJson> createEntry(@RequestParam Long userId, @RequestBody EntryJson entryJson) {
    return entryService.createEntry(userId, entryJson);
  }

  @GetMapping("")
  public ResponseEntity<EntryJson> getEntry(@RequestParam Long userId, @RequestParam Long entryId) {
    return entryService.getEntry(userId, entryId);
  }

  @GetMapping("/tag")
  public ResponseEntity<List<EntryJson>> getEntriesByTag(@RequestParam Long userId, @RequestParam String tagName) {
    return entryService.getEntriesByTag(userId, tagName);
  }

  @DeleteMapping("")
  public ResponseEntity deleteEntry(@RequestParam Long userId, @RequestParam Long entryId) {
    entryService.deleteEntry(userId, entryId);
    return ResponseEntity.ok().build();
  }

  @GetMapping("/entries")
  public ResponseEntity<List<EntryJson>> getAllEntriesByUser(@RequestParam Long userId) {
    return entryService.getAllEntriesByUser(userId);
  }

  @PutMapping("")
  public ResponseEntity<EntryJson> updateEntry(@RequestParam Long userId, @RequestBody EntryJson entryJson) {
    return entryService.updateEntry(userId, entryJson);
  }

}
