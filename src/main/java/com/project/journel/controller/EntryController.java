package com.project.journel.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
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
  public ResponseEntity<EntryJson> createEntry(@RequestParam int userId, @RequestBody EntryJson entryJson) {
    return entryService.createEntry(userId, entryJson);
  }
}
