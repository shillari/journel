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

import com.project.journel.config.firebase.FirebaseInitializer;
import com.project.journel.config.security.JwtService;
import com.project.journel.entity.Role;
import com.project.journel.entity.database.UserAccount;
import com.project.journel.repository.UserAccountRepository;
import com.project.journel.requestdata.AuthenticationRequest;
import com.project.journel.requestdata.AuthenticationResponse;
import com.project.journel.requestdata.PasswordRequest;
import com.project.journel.requestdata.RegisterRequest;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

  private final UserAccountRepository repository;
  private final PasswordEncoder passwordEncoder;
  private final JwtService jwtService;
  private final AuthenticationManager authenticationManager;
  private final FirebaseInitializer firebaseInitializer;

  public ResponseEntity<AuthenticationResponse> register(RegisterRequest request) {
    UserAccount user = UserAccount.builder()
        .username(request.getUsername())
        .email(request.getEmail())
        .password(passwordEncoder.encode(request.getPassword()))
        .role(Role.USER)
        .build();

    Optional<UserAccount> usr = repository.findByEmail(request.getEmail());
    if (usr != null && usr.isPresent()) {
      return ResponseEntity.status(HttpStatus.CONFLICT).build();
    }

    repository.save(user);
    String token = jwtService.generateToken(user);

    return ResponseEntity.ok(AuthenticationResponse.builder()
        .id(user.getId())
        .token(token)
        .username(user.getName())
        .email(user.getEmail())
        .build());
  }

  public ResponseEntity<AuthenticationResponse> authenticate(AuthenticationRequest request) {
    authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
        request.getEmail(), request.getPassword()));

    Optional<UserAccount> user = repository.findByEmail(request.getEmail());
    if (user == null || !user.isPresent()) {
      return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    UserAccount usr = user.get();
    String token = jwtService.generateToken(usr);
    String firebaseToken = firebaseInitializer.createFirebaseCustomToken(String.valueOf(usr.getId()));
    return ResponseEntity.ok(AuthenticationResponse.builder()
        .token(token)
        .firebaseToken(firebaseToken)
        .username(usr.getName())
        .email(usr.getEmail())
        .id(usr.getId())
        .photoUrl(usr.getPhotoUrl())
        .build());
  }

  public ResponseEntity<AuthenticationResponse> updatePassword(PasswordRequest req) {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    String jwtUsername = authentication.getName(); 
    
    Optional<UserAccount> user = repository.findByEmail(req.getEmail());
    if (user == null || !user.isPresent()) {
      return ResponseEntity
          .status(HttpStatus.NO_CONTENT).build();
    }

    if (!user.get().getEmail().equals(jwtUsername) || !passwordEncoder.matches(req.getCurrPass(), user.get().getPassword())) {
      return ResponseEntity
          .status(HttpStatus.UNAUTHORIZED).build();
    }

    user.get().setPassword(passwordEncoder.encode(req.getNewPass()));
    repository.save(user.get());

    UserAccount usr = user.get();
    String token = jwtService.generateToken(usr);
    return ResponseEntity.ok(AuthenticationResponse.builder()
        .token(token)
        .username(usr.getName())
        .email(usr.getEmail())
        .id(usr.getId())
        .photoUrl(usr.getPhotoUrl())
        .build());
  }

}
