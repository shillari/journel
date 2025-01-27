package com.project.journel.entity.mapper;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Component;

import com.project.journel.entity.CategoryJson;
import com.project.journel.entity.EntryJson;
import com.project.journel.entity.TagJson;
import com.project.journel.entity.UserAccountJson;
import com.project.journel.entity.database.Category;
import com.project.journel.entity.database.Entry;
import com.project.journel.entity.database.Tag;
import com.project.journel.entity.database.UserAccount;

@Component
public class EntryMapper {

  public static Entry mapToEntryDb(EntryJson entryjson, UserAccount userAccount) {
    return Entry.builder()
        .id(entryjson.getId())
        .title(entryjson.getTitle())
        .description(entryjson.getDescription())
        .entryDate(entryjson.getEntryDate())
        .category(mapCategory(entryjson.getCategory()))
        .tags(mapTags(entryjson.getTags()))
        .userAccount(userAccount)
        .build();
  }

  public static Category mapCategory(CategoryJson categoryJson) {
    return Category.builder()
        .id(categoryJson.getId())
        .categoryName(categoryJson.getCategoryName())
        .build();
  }

  public static Set<Tag> mapTags(List<TagJson> tagsJson) {
    Set<Tag> tags = new HashSet<>();
    for (TagJson t : tagsJson) {
      tags.add(Tag.builder()
          .id(t.getId())
          .tagName(t.getTagName())
          .build());
    }
    return tags;
  }

  public static UserAccount mapUserAccount(UserAccountJson userAccountJson) {
    return UserAccount.builder()
        .id(userAccountJson.getId())
        .username(userAccountJson.getUsername())
        .email(userAccountJson.getEmail())
        .birthday(userAccountJson.getBirthday())
        .build();
  }

}
