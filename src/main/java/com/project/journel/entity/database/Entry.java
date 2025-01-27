package com.project.journel.entity.database;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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
@Table(name = "entry")
public class Entry {

  @Id
  @GeneratedValue
  @Column(name = "id")
  private Integer id;

  @Column(name = "title", nullable = false)
  private String title;

  @Column(name = "description")
  private String description;

  @Column(name = "entry_date")
  private Date entryDate;

  @ManyToOne
  @JoinColumn(name = "category_id", nullable = false)
  private Category category;

  @ManyToMany
  @JoinTable(name = "entry_tag", joinColumns = @JoinColumn(name = "entry_id"), inverseJoinColumns = @JoinColumn(name = "tag_id"))
  private Set<Tag> tags = new HashSet<>();

  @ManyToOne
  @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
  private UserAccount userAccount;
}
