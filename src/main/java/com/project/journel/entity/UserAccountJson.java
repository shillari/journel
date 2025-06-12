package com.project.journel.entity;

import java.time.LocalDate;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserAccountJson {

  @JsonProperty("id")
  private Long id;
  @JsonProperty("username")
  private String username;
  @JsonProperty("email")
  private String email;
  @JsonProperty("birthday")
  private LocalDate birthday;
  @JsonProperty("entries")
  private Set<EntryJson> entries;

}
