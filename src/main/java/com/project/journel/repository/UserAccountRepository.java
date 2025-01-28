package com.project.journel.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.journel.entity.database.UserAccount;

public interface UserAccountRepository extends JpaRepository<UserAccount, Long> {

  Optional<UserAccount> findByEmail(String email);
}
