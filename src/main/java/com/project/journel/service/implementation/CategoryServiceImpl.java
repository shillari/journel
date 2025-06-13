package com.project.journel.service.implementation;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.project.journel.entity.CategoryJson;
import com.project.journel.entity.database.Category;
import com.project.journel.entity.database.UserAccount;
import com.project.journel.entity.mapper.CategoryMapper;
import com.project.journel.repository.CategoryRepository;
import com.project.journel.repository.UserAccountRepository;
import com.project.journel.service.CategoryService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

  private final CategoryRepository categoryRepository;
  private final UserAccountRepository userRepository;

  @Override
  public ResponseEntity<List<CategoryJson>> getAllCategories(Long userId) {
    List<Category> categories = new ArrayList<>();

    // Verify if the user exists
    Optional<UserAccount> user = userRepository.findById(userId);
    if (user == null || !user.isPresent()) {
      return ResponseEntity
          .status(HttpStatus.NO_CONTENT).build();
    }

    categories = categoryRepository.findAll();

    return ResponseEntity.ok(CategoryMapper.getAllCategoriesJson(categories));
  }

}
