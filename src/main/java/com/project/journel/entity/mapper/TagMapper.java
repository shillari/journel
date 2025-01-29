package com.project.journel.entity.mapper;

import org.springframework.stereotype.Component;

import com.project.journel.entity.TagJson;
import com.project.journel.entity.database.Tag;

@Component
public class TagMapper {

  public static Tag mapToTagDb(TagJson tagJson) {
    return Tag.builder()
        .tagName(tagJson.getTagName())
        .build();
  }
}
