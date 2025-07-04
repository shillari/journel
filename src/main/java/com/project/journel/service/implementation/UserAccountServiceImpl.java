package com.project.journel.service.implementation;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.project.journel.entity.UserAccountJson;
import com.project.journel.entity.database.UserAccount;
import com.project.journel.entity.mapper.UserAccountMapper;
import com.project.journel.repository.UserAccountRepository;
import com.project.journel.requestdata.PasswordRequest;
import com.project.journel.requestdata.RegisterRequest;
import com.project.journel.service.UserAccountService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserAccountServiceImpl implements UserAccountService {

  private final UserAccountRepository userAccountRepository;
  private final AuthenticationManager authenticationManager;

  @Override
  public ResponseEntity<UserAccountJson> getUserByEmail(String email) {
    Optional<UserAccount> user = userAccountRepository.findByEmail(email);
    if (user == null || !user.isPresent()) {
      return ResponseEntity
          .status(HttpStatus.NO_CONTENT).build();
    }

    return ResponseEntity.ok(UserAccountMapper.mapToUserJson(user.get()));
  }

  @Override
  public ResponseEntity<String> deleteUser(RegisterRequest req) {
    authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
        req.getEmail(), req.getPassword()));

    Optional<UserAccount> user = userAccountRepository.findByEmail(req.getEmail());
    if (user == null || !user.isPresent()) {
      return ResponseEntity
          .status(HttpStatus.NO_CONTENT).build();
    }

    userAccountRepository.deleteById(user.get().getId());

    return ResponseEntity.ok("User deleted.");
  }

  @Override
  public ResponseEntity<UserAccountJson> updateUser(RegisterRequest req) {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    String jwtUsername = authentication.getName();
    
    Optional<UserAccount> user = userAccountRepository.findByEmail(req.getEmail());
    if (user == null || !user.isPresent()) {
      return ResponseEntity
          .status(HttpStatus.NO_CONTENT).build();
    }

    if (!user.get().getEmail().equals(jwtUsername)) {
      return ResponseEntity
          .status(HttpStatus.UNAUTHORIZED).build();
    }

    user.get().setUsername(req.getUsername());
    user.get().setBirthday(req.getBirthday());
    user.get().setPhotoUrl(req.getPhotoUrl());
    userAccountRepository.save(user.get());

    return ResponseEntity.ok().body(UserAccountJson.builder()
            .birthday(user.get().getBirthday())
            .email(user.get().getEmail())
            .username(user.get().getName())
            .photoUrl(user.get().getPhotoUrl())
            .build());
  }

}
