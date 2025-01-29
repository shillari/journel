package com.project.journel.service.implementation;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.project.journel.entity.UserAccountJson;
import com.project.journel.entity.database.UserAccount;
import com.project.journel.entity.mapper.UserAccountMapper;
import com.project.journel.repository.UserAccountRepository;
import com.project.journel.service.UserAccountService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserAccountServiceImpl implements UserAccountService {

  private final UserAccountRepository userAccountRepository;

  @Override
  public ResponseEntity<UserAccountJson> getUserByEmail(String email) {
    Optional<UserAccount> user = userAccountRepository.findByEmail(email);
    if (user == null || !user.isPresent()) {
      return ResponseEntity
          .status(HttpStatus.NO_CONTENT).build();
    }

    return ResponseEntity.ok(UserAccountMapper.mapToUserJson(user.get()));
  }

}
