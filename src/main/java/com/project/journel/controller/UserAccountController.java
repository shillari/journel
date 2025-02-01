package com.project.journel.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.journel.entity.UserAccountJson;
import com.project.journel.requestdata.RegisterRequest;
import com.project.journel.service.UserAccountService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserAccountController {

  private final UserAccountService userAccountService;

  @GetMapping("")
  public ResponseEntity<UserAccountJson> getUserByEmail(@RequestParam String email) {
    return userAccountService.getUserByEmail(email);
  }

  @DeleteMapping("")
  public ResponseEntity<String> deleteUser(@RequestBody RegisterRequest req) {
    return userAccountService.deleteUser(req);
  }

}
