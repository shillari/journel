package com.project.journel.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TagJson {

  @JsonProperty("id")
  private int id;
  @JsonProperty("tag_name")
  private String tagName;
  @JsonProperty("entry")
  private EntryJson entry;
}
