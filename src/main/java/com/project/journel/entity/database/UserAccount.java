package com.project.journel.entity.database;

import java.util.Date;
import java.util.List;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "user_account")
public class UserAccount {

  @Id
  @GeneratedValue
  @Column(name = "id")
  private Integer id;

  @Column(name = "username", nullable = false)
  private String username;

  @Column(name = "email", unique = true, nullable = false)
  private String email;

  @Column(name = "password", nullable = false)
  private String password;

  @Column(name = "birthday")
  private Date birthday;

  @OneToMany(mappedBy = "userAccount", cascade = CascadeType.ALL)
  @Fetch(value = FetchMode.JOIN)
  private List<Entry> entries;
}
