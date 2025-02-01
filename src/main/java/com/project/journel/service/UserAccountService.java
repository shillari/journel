package com.project.journel.service;

import org.springframework.http.ResponseEntity;

import com.project.journel.entity.UserAccountJson;
import com.project.journel.requestdata.RegisterRequest;

public interface UserAccountService {

  public ResponseEntity<UserAccountJson> getUserByEmail(String email);

  public ResponseEntity<String> deleteUser(RegisterRequest req);
}
