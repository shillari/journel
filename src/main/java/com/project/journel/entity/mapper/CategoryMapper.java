package com.project.journel.entity.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.project.journel.entity.CategoryJson;
import com.project.journel.entity.database.Category;

@Component
public class CategoryMapper {

  public static List<CategoryJson> getAllCategoriesJson(List<Category> categories) {

    List<CategoryJson> catJson = new ArrayList<>();
    for (Category cat : categories) {
      catJson.add(CategoryJson.builder()
          .id(cat.getId())
          .categoryName(cat.getCategoryName())
          .build());
    }

    return catJson;
  }
}
