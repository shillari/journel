package com.project.journel.service.implementation;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.project.journel.entity.EntryJson;
import com.project.journel.entity.database.Entry;
import com.project.journel.entity.database.UserAccount;
import com.project.journel.entity.mapper.EntryMapper;
import com.project.journel.repository.EntryRepository;
import com.project.journel.repository.UserAccountRepository;
import com.project.journel.service.EntryService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EntryServiceImpl implements EntryService {

  private final UserAccountRepository userRepository;
  private final EntryRepository entryRepository;

  @Override
  public ResponseEntity<EntryJson> createEntry(int userId, EntryJson entryJson) {

    Optional<UserAccount> user = userRepository.findById(userId);
    if (user == null || !user.isPresent()) {
      return ResponseEntity
          .status(HttpStatus.NO_CONTENT).build();
    }

    Entry entry = EntryMapper.mapToEntryDb(entryJson, user.get());
    entryRepository.save(entry);
    entryJson.setId(entry.getId());
    return ResponseEntity.ok(entryJson);
  }

  // private boolean addCategory(int entryId, String categoryName)
}
