package com.project.journel.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.project.journel.entity.CategoryJson;

public interface CategoryService {
  ResponseEntity<List<CategoryJson>> getAllCategories(Long userId);
}
