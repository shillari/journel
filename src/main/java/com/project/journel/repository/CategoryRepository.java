package com.project.journel.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.journel.entity.database.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

  Optional<Category> findByCategoryName(String categoryName);
}
