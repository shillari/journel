package com.project.journel.entity;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EntryJson {

  @JsonProperty("id")
  private int id;
  @JsonProperty("title")
  private String title;
  @JsonProperty("description")
  private String description;
  @JsonProperty("entry_date")
  private Date entryDate;
  @JsonProperty("category")
  private CategoryJson category;
  @JsonProperty("tags")
  private List<TagJson> tags;
  @JsonProperty("user_account")
  private UserAccountJson userAccount;
}
