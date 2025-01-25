package com.project.journel.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.journel.entity.database.Entry;

public interface EntryRepository extends JpaRepository<Entry, Integer> {

}
