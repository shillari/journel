package com.project.journel.service;

import org.springframework.http.ResponseEntity;

import com.project.journel.entity.UserAccountJson;

public interface UserAccountService {

  public ResponseEntity<UserAccountJson> getUserByEmail(String email);
}
