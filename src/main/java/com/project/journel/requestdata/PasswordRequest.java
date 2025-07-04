package com.project.journel.requestdata;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PasswordRequest {
  
  @JsonProperty("email")
  private String email;
  @JsonProperty("curr_pass")
  private String currPass;
  @JsonProperty("new_pass")
  private String newPass;

}
