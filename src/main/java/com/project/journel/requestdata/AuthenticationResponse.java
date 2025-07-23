package com.project.journel.requestdata;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponse {

  private Long id;
  @JsonIgnore
  private String token;
  private String username;
  private String email;
  private String photoUrl;
  private String firebaseToken;
}
