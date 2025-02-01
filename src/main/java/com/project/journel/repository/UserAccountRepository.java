package com.project.journel.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.project.journel.entity.database.UserAccount;

public interface UserAccountRepository extends JpaRepository<UserAccount, Long> {

  Optional<UserAccount> findByEmail(String email);

  @Modifying
  @Query("DELETE FROM UserAccount u WHERE u.id = :userId")
  void deleteUserByid(Long userId);
}
