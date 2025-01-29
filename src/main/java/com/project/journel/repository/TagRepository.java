package com.project.journel.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.journel.entity.database.Tag;

public interface TagRepository extends JpaRepository<Tag, String> {

  Optional<Tag> findByTagName(String tagName);
}
