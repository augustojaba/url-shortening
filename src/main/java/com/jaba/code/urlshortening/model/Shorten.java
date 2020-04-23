package com.jaba.code.urlshortening.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
public class Shorten {

  @Id
  @GeneratedValue(generator = "uuid2")
  @GenericGenerator(name = "uuid2", strategy = "uuid2")
  @Column(columnDefinition = "CHAR(36)")
  private String id;

  @Column(unique = true)
  private String token;

  @Column(name = "long_url", unique = true)
  private String longUrl;

  @Column(name = "created_at", columnDefinition = "TIMESTAMP")
  private LocalDateTime createdAt;

  @Column(name = "expire_at", columnDefinition = "TIMESTAMP")
  private LocalDateTime expireAt;

  protected Shorten() {}

  public Shorten(String token, String longUrl, LocalDateTime createdAt, LocalDateTime expireAt) {
    this.token = token;
    this.longUrl = longUrl;
    this.createdAt = createdAt;
    this.expireAt = expireAt;
  }

  public String getId() {
    return id;
  }

  public String getToken() {
    return token;
  }

  public String getLongUrl() {
    return longUrl;
  }

  public LocalDateTime getCreatedAt() {
    return createdAt;
  }

  public LocalDateTime getExpireAt() {
    return expireAt;
  }
}
