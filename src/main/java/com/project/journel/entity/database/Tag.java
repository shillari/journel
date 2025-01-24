package com.project.journel.entity.database;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tag")
public class Tag {

  @Id
  @GeneratedValue
  @Column(name = "id")
  private Integer id;

  @Column(name = "tag_name", nullable = false)
  private String tagName;

  @ManyToOne
  @JoinColumn(name = "entry_id", referencedColumnName = "id", nullable = false)
  private Entry entry;
}
