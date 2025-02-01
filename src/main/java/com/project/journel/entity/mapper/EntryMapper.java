package com.project.journel.entity.mapper;

import java.util.HashSet;
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
        .userAccount(userAccount)
        .build();
  }

  public static EntryJson matToEntryJson(Entry entry) {
    return EntryJson.builder()
        .id(entry.getId())
        .title(entry.getTitle())
        .description(entry.getDescription())
        .entryDate(entry.getEntryDate())
        .category(getCategoryJson(entry.getCategory()))
        .tags(getTagsJson(entry.getTags()))
        .userAccount(getUserAccountJson(entry.getUserAccount()))
        .build();
  }

  private static UserAccountJson getUserAccountJson(UserAccount userAccount) {
    return UserAccountJson.builder()
        .id(userAccount.getId())
        .username(userAccount.getName())
        .email(userAccount.getEmail())
        .birthday(userAccount.getBirthday())
        .build();
  }

  private static CategoryJson getCategoryJson(Category category) {
    return CategoryJson.builder()
        .id(category.getId())
        .categoryName(category.getCategoryName())
        .build();
  }

  private static Set<TagJson> getTagsJson(Set<Tag> tags) {
    Set<TagJson> tagsJson = new HashSet<>();
    for (Tag tag : tags) {
      TagJson tagJson = TagJson.builder()
          .id(tag.getId())
          .tagName(tag.getTagName())
          .build();
      tagsJson.add(tagJson);
    }
    return tagsJson;
  }

}
