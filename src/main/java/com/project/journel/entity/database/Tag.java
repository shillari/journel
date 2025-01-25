package com.project.journel.entity.database;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
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
