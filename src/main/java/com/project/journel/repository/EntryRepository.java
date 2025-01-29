package com.project.journel.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.NonNull;

import com.project.journel.entity.database.Entry;
import com.project.journel.entity.database.UserAccount;

public interface EntryRepository extends JpaRepository<Entry, Long> {

  Optional<Entry> findByIdAndUserAccount(Long id, UserAccount userAccount);

  @Modifying
  @Query("DELETE FROM Entry e WHERE e.id = :entryId")
  void deleteById(@NonNull Long entryId);

  @Query("SELECT e FROM Entry e WHERE e.id IN :ids")
  List<Entry> findEntriesByIds(List<Long> ids);

  @Query("SELECT e FROM Entry e WHERE e.userAccount.id = :userId")
  List<Entry> findAllByUser(Long userId);
}
