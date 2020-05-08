package com.jaba.code.urlshortening.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
public class Shorten {

  @Id @Column private String token;

  @Column(name = "long_url", unique = true)
  private String longUrl;

  @Column(name = "created_at", columnDefinition = "TIMESTAMP")
  private LocalDateTime createdAt;

  @Column(name = "expire_at", columnDefinition = "TIMESTAMP")
  private LocalDateTime expireAt;

  protected Shorten() {}

  public Shorten(String token, String longUrl) {
    this.token = token;
    this.longUrl = longUrl;
    createdAt = LocalDateTime.now();
    expireAt = LocalDateTime.now().plusDays(100);
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
