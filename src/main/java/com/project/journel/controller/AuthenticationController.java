package com.project.journel.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.journel.requestdata.AuthenticationRequest;
import com.project.journel.requestdata.AuthenticationResponse;
import com.project.journel.requestdata.PasswordRequest;
import com.project.journel.requestdata.RegisterRequest;
import com.project.journel.service.implementation.AuthenticationService;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

  private final AuthenticationService service;

  @PostMapping("/register")
  public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request,
          HttpServletResponse response) {
    ResponseEntity<AuthenticationResponse> result = service.register(request);
    if (result.getStatusCode().equals(HttpStatus.OK) && result.hasBody()) {
      String token = result.getBody().getToken();
      if (token != null && !token.isEmpty()) {
        response.setHeader(HttpHeaders.SET_COOKIE,
        "token=" + token + "; HttpOnly; Secure; SameSite=None; Path=/; Max-Age=604800");
      } else {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
      }
    }

    return result;
  }

  @PostMapping("/authenticate")
  public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request,
          HttpServletResponse response) {
    ResponseEntity<AuthenticationResponse> result = service.authenticate(request);

    if (result.getStatusCode().equals(HttpStatus.OK) && result.hasBody()) {
      String token = result.getBody().getToken();
      if (token != null && !token.isEmpty()) {
        response.setHeader(HttpHeaders.SET_COOKIE,
        "token=" + token + "; HttpOnly; Secure; SameSite=None; Path=/; Max-Age=604800");
      } else {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
      }
    }

    return result;
  }

  @PutMapping("/password")
  public ResponseEntity<AuthenticationResponse> updatePassword(@RequestBody PasswordRequest req, HttpServletResponse response) {
    ResponseEntity<AuthenticationResponse> result = service.updatePassword(req);
    if (result.getStatusCode().equals(HttpStatus.OK) && result.hasBody()) {
      String token = result.getBody().getToken();
      if (token != null && !token.isEmpty()) {
        response.setHeader(HttpHeaders.SET_COOKIE,
        "token=" + token + "; HttpOnly; Secure; SameSite=None; Path=/; Max-Age=604800");
      } else {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
      }
    }

    return result;
  }

  @PostMapping("/logout")
  public ResponseEntity<String> logout(HttpServletResponse response) {
    ResponseCookie cookie = ResponseCookie.from("token", "")
        .httpOnly(true)
        .secure(true)
        .path("/") 
        .maxAge(0)
        .build();

    response.setHeader(HttpHeaders.SET_COOKIE, cookie.toString());

    return ResponseEntity.ok().build();
  }
}
