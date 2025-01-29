package com.project.journel.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CategoryJson {

  @JsonProperty("id")
  private Long id;
  @JsonProperty("category_name")
  private String categoryName;
  @JsonIgnore
  @JsonProperty("entries")
  private List<EntryJson> entries;
}
