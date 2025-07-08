package com.project.journel.service.implementation;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.project.journel.entity.EntryJson;
import com.project.journel.entity.TagJson;
import com.project.journel.entity.database.Category;
import com.project.journel.entity.database.Entry;
import com.project.journel.entity.database.Tag;
import com.project.journel.entity.database.UserAccount;
import com.project.journel.entity.mapper.EntryMapper;
import com.project.journel.entity.mapper.TagMapper;
import com.project.journel.entity.pagination.EntryPageResponse;
import com.project.journel.repository.CategoryRepository;
import com.project.journel.repository.EntryRepository;
import com.project.journel.repository.TagRepository;
import com.project.journel.repository.UserAccountRepository;
import com.project.journel.service.EntryService;
import com.project.journel.service.RedisService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EntryServiceImpl implements EntryService {

  private final UserAccountRepository userRepository;
  private final EntryRepository entryRepository;
  private final TagRepository tagRepository;
  private final CategoryRepository categoryRepository;
  private final RedisService redisService;

  @Override
  public ResponseEntity<EntryJson> createEntry(Long userId, EntryJson entryJson) {

    // Verify if the user exists
    Optional<UserAccount> user = userRepository.findById(userId);
    if (user == null || !user.isPresent()) {
      return ResponseEntity
          .status(HttpStatus.NO_CONTENT).build();
    }

    // Save the entry
    Entry entry = EntryMapper.mapToEntryDb(entryJson, user.get());
    entryRepository.save(entry);
    entryJson.setId(entry.getId());

    // Save all entry tags
    Set<Tag> tags = new HashSet<>();
    for (TagJson tagJson : entryJson.getTags()) {
      Tag tag = tagRepository.findByTagName(tagJson.getTagName())
          .orElseGet(() -> tagRepository.save(TagMapper.mapToTagDb(tagJson)));
      tags.add(tag);

      // Save tag by user for entry in Redis.
      redisService.addEntryToTagForUser(user.get().getId(), tag.getTagName(), entry.getId());
    }

    Optional<Category> category = categoryRepository.findByCategoryName(entryJson.getCategory().getCategoryName());

    entry.setCategory(category.get());
    entry.setTags(tags);
    entryRepository.save(entry);

    return ResponseEntity.ok(entryJson);
  }

  @Override
  public ResponseEntity<EntryJson> getEntry(Long userId, Long entryId) {
    // Verify if the user exists
    Optional<UserAccount> user = userRepository.findById(userId);
    if (user == null || !user.isPresent()) {
      return ResponseEntity
          .status(HttpStatus.NO_CONTENT).build();
    }

    Optional<Entry> entry = entryRepository.findByIdAndUserAccount(entryId, user.get());
    if (entry == null || !entry.isPresent()) {
      return ResponseEntity
          .status(HttpStatus.NO_CONTENT).build();
    }

    return ResponseEntity.ok(EntryMapper.matToEntryJson(entry.get()));
  }

  @Override
  public ResponseEntity<String> deleteEntry(Long userId, Long entryId) {
    // Verify if the user exists
    Optional<UserAccount> user = userRepository.findById(userId);
    if (user == null || !user.isPresent()) {
      return ResponseEntity
          .status(HttpStatus.NO_CONTENT).build();
    }

    Optional<Entry> entry = entryRepository.findById(entryId);
    if (entry == null || !entry.isPresent()) {
      return ResponseEntity
          .status(HttpStatus.NO_CONTENT).build();
    }

    entryRepository.deleteById(entryId);
    redisService.deleteEntry(userId, entryId);

    return ResponseEntity.ok().body("Entry deleted.");
  }

  @Override
  public ResponseEntity<List<EntryJson>> getEntriesByTag(Long userId, String tagName) {
    // Verify if the user exists
    Optional<UserAccount> user = userRepository.findById(userId);
    if (user == null || !user.isPresent()) {
      return ResponseEntity
          .status(HttpStatus.NO_CONTENT).build();
    }

    List<Long> entriesIds = redisService.getEntriesByTagForUser(userId, tagName).stream()
        .map((id) -> Long.valueOf(id))
        .collect(Collectors.toList());
    List<Entry> entries = entryRepository.findEntriesByIds(entriesIds);

    List<EntryJson> entriesJson = new ArrayList<>();
    for (Entry entry : entries) {
      entriesJson.add(EntryMapper.matToEntryJson(entry));
    }

    return ResponseEntity.ok(entriesJson);
  }

  public ResponseEntity<EntryPageResponse> getAllEntries(Long userId, int page, int size) {

    // Verify if the user exists
    Optional<UserAccount> user = userRepository.findById(userId);
    if (user == null || !user.isPresent()) {
      return ResponseEntity
          .status(HttpStatus.NO_CONTENT).build();
    }

    Pageable pageable = PageRequest.of(page, size);
    Page<Entry> result = entryRepository.findAllByUserAccountId(userId, pageable);
    List<EntryJson> entriesJson = new ArrayList<>();
    for (Entry entry : result.getContent()) {
      entriesJson.add(EntryMapper.matToEntryJson(entry));
    }
    EntryPageResponse response = new EntryPageResponse(
      entriesJson,
      result.getTotalPages(),
      result.getTotalElements()
    );

    return ResponseEntity.ok(response);
  }

  @Override
  public ResponseEntity<List<EntryJson>> getAllEntriesByUser(Long userId) {
    // Verify if the user exists
    Optional<UserAccount> user = userRepository.findById(userId);
    if (user == null || !user.isPresent()) {
      return ResponseEntity
          .status(HttpStatus.NO_CONTENT).build();
    }

    List<Entry> entries = entryRepository.findAllByUser(userId);

    List<EntryJson> entriesJson = new ArrayList<>();
    for (Entry entry : entries) {
      entriesJson.add(EntryMapper.matToEntryJson(entry));
    }

    return ResponseEntity.ok(entriesJson);
  }

  @Override
  @Transactional
  public ResponseEntity<EntryJson> updateEntry(Long userId, EntryJson entryJson) {
    // Verify if the user exists
    Optional<UserAccount> user = userRepository.findById(userId);
    if (user == null || !user.isPresent()) {
      return ResponseEntity
          .status(HttpStatus.NO_CONTENT).build();
    }

    // Save the entry
    Optional<Entry> opEntry = entryRepository.findById(entryJson.getId());
    // Entry entry = EntryMapper.mapToEntryDb(entryJson, user.get());
    if (opEntry == null || !opEntry.isPresent()) {
      return ResponseEntity
          .status(HttpStatus.NO_CONTENT).build();
    }

    Entry entry = opEntry.get();

    redisService.deleteEntry(userId, entry.getId());

    entry.setTitle(entryJson.getTitle());
    entry.setDescription(entryJson.getDescription());
    entry.setEntryDate(entryJson.getEntryDate());

    // Save all entry tags
    Set<Tag> tags = new HashSet<>();
    for (TagJson tagJson : entryJson.getTags()) {
      Tag tag = tagRepository.findByTagName(tagJson.getTagName())
          .orElseGet(() -> tagRepository.save(TagMapper.mapToTagDb(tagJson)));
      tags.add(tag);

      // Save tag by user for entry in Redis.
      redisService.addEntryToTagForUser(user.get().getId(), tag.getTagName(), entry.getId());
    }

    Optional<Category> category = categoryRepository.findByCategoryName(entryJson.getCategory().getCategoryName());

    entry.setCategory(category.get());
    entry.setTags(tags);
    entryRepository.save(entry);

    return ResponseEntity.ok(EntryMapper.matToEntryJson(entry));
  }

}
